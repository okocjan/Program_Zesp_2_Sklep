package com.example.demo.app.service;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.dto.ProductUpdateDto;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.demo.app.model.factory.ProductCreator.createProductToPersist;
import static com.example.demo.app.model.factory.ProductCreator.createProductToUpdate;

@Service
public class ProductServiceImpl implements IProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Set<Product> getAllProducts() {
        try {
            log.info("Getting products with picture.");
            return new HashSet<>(productRepository.findAll());
        } catch (Exception e) {
            log.error("Getting products with picture failed!!!\n{0}", e);
            return Collections.emptySet();
        }

    }

    @Override
    public Optional<Product> getProductById(Long id) {
        try {
            log.info("Trying to find product with id: {}", id);
            return productRepository.findById(id);
        } catch (NoSuchElementException e) {
            log.warn("Element not found!!!", e);
        } catch (Exception e) {
            log.error("Error while searching for product!!!\n{0}", e);
        }
        return Optional.empty();
    }

    @Override
    public Product addProduct(ProductPersistDto product) {
        try {
            log.info("Trying to save product.");
            // todo: move to gcs
            String path = uploadProductPicture(product.getImage(), product.getName());
            Product toSave = createProductToPersist(product, path);
            toSave.getProductPicture().setProduct(toSave);
            toSave.getStorage().setProduct(toSave);
            return productRepository.save(toSave);
        } catch (Exception e) {
            log.error("Error while trying to save product!!!\n{0}", e);
            return null;
        }
    }

    @Transactional
    @Override
    public Product updateProduct(ProductUpdateDto product) {
        try {
            log.info("Trying to update product.");
            String path = uploadProductPicture(product.getImage(), product.getName());
            Product toSave = createProductToUpdate(product, path);
            toSave.getProductPicture().setProduct(toSave);
            toSave.getStorage().setProduct(toSave);
            return productRepository.save(toSave);
        } catch (Exception e) {
            log.error("Error while updating product!!!\n{0}", e);
            return null;
        }
    }

    private String uploadProductPicture(MultipartFile file, String name) throws Exception {
        String savedFilePath = Files.createFile(prepareFilePath(name)).toString();
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(savedFilePath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();

        return savedFilePath;
    }

    private Path prepareFilePath(String name) {
        return Path.of(System.getProperty("user.dir") + "/uploads/" + name + "_" +
                LocalDateTime.now().toString()
                        .replace("-", "_")
                        .replace(":", "_")
                        .replace(".", "_")
                + ".png");
    }

    @Override
    public Boolean deleteProduct(Long id) {
        try {
            log.info("Trying to delete product.");
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error while deleting product!!!\n {0}", e);
            return false;
        }
    }

    @Override
    public List<Product> getAllProductsById(List<Long> ids) {
        return productRepository.findAllByIdIn(ids);
    }

}
