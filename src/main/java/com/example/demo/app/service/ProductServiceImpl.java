package com.example.demo.app.service;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.dto.ProductUpdateDto;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.demo.app.model.entity.Product.createProductToPersist;
import static com.example.demo.app.model.entity.Product.createProductToUpdate;

@Service
public class ProductServiceImpl implements IProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Set<Product> getAllProductsWithPicture() {
        try {
            log.info("Getting products with picture.");
            return new HashSet<>(productRepository.findAll());
        } catch (Exception e) {
            log.error("Getting products with picture failed!!!\n{0}", e);
            return Collections.emptySet();
        }

    }

    @Override
    public Optional<Product> getProductPage(Long id) {
        try {
            log.info("Trying to find product with id: {}", id);
            return productRepository.findById(id);
        } catch (NoSuchElementException e) {
            log.warn("Element not found!!!", e);
        } catch (Exception e) {
            log.error("Error while searching for product!!!\n{0}", e);
        }
        return null;
    }

    @Override
    public Product addProduct(ProductPersistDto product) {
        try {
            log.info("Trying to save product.");
            Product toSave = createProductToPersist(product);
            toSave.getProductPicture().setProduct(toSave);
            toSave.getStorage().setProduct(toSave);
            return productRepository.save(toSave);
        } catch (Exception e) {
            log.error("Error while trying to save product!!!\n{0}", e);
            return null;
        }
    }

    @Override
    public Product updateProduct(ProductUpdateDto product) {
        try {
            log.info("Trying to update product.");
            Product toSave = createProductToUpdate(product);
            toSave.getProductPicture().setProduct(toSave);
            toSave.getStorage().setProduct(toSave);
            return productRepository.save(toSave);
        } catch (Exception e) {
            log.error("Error while updating product!!!\n{0}", e);
            return null;
        }
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

}
