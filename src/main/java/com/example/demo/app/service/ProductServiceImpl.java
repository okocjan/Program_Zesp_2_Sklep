package com.example.demo.app.service;

import com.example.demo.app.model.dto.*;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.model.entity.Storage;
import com.example.demo.app.repository.ProductRepository;
import com.example.demo.app.repository.StorageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;

import static com.example.demo.app.model.factory.ProductCreator.createProductToPersist;
import static com.example.demo.app.model.factory.ProductCreator.createProductToUpdate;

@Service
public class ProductServiceImpl implements IProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final IFileHelperService fileHelperService;
    private final StorageRepository storageRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              IFileHelperService fileHelperService,
                              StorageRepository storageRepository) {
        this.productRepository = productRepository;
        this.fileHelperService = fileHelperService;
        this.storageRepository = storageRepository;
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
            ImgurResponse savedFile = fileHelperService.uploadFile(product.getImage(), product.getName());
            ImgurResponseData data = savedFile.getData();
            Product toSave = createProductToPersist(product, data.getLink(), data.getDeleteHash());
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
            deleteOldProductImage(product.getId());
            ImgurResponseData data = uploadImageToImgur(product.getImage(), product.getName());
            Product toSave = createProductToUpdate(product, data.getLink(), data.getDeleteHash());
            toSave.getProductPicture().setProduct(toSave);
            toSave.getStorage().setProduct(toSave);
            return productRepository.save(toSave);
        } catch (Exception e) {
            log.error("Error while updating product!!!\n{0}", e);
            return null;
        }
    }

    private void deleteOldProductImage(Long id) {

    }

    private ImgurResponseData uploadImageToImgur(MultipartFile file, String name) {
        ImgurResponse savedFile = fileHelperService.uploadFile(file, name);
        ImgurResponseData data = savedFile.getData();
        return data;
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



    @Override
    public boolean updateProductQuantity(StorageUpdateDto updateDto) {
        try {
            log.info("Trying to update product quantity. ProductId: {}", updateDto.getProductId());
            Storage toUpdate = new Storage(updateDto.getProductId(), null, updateDto.getQuantity());
            storageRepository.saveAndFlush(toUpdate);
            log.info("Updating product quantity successful");
            return true;
        } catch (Exception e) {
            log.error("Error while trying to update product quantity");
            return false;
        }
    }

    @Transactional
    @Override
    public List<Product> getAllProductsByIdWithDuplicates(List<Long> ids) {
        productRepository.createTmpTableForIds();
        for (Long id : ids) {
            log.info("inserting into tmpoo table");
            productRepository.insertIdIntoTmpTable(id);
        }
        List<Product> productList = productRepository.findAllByIdInWithDuplicates();
        productRepository.dropTmpTableWithIds();
        return productList;
    }

}
