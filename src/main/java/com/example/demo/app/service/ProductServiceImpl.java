package com.example.demo.app.service;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.dto.projection.ProductListDto;
import com.example.demo.app.model.dto.projection.ProductPageDto;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.repository.ProductPictureRepository;
import com.example.demo.app.repository.ProductRepository;
import com.example.demo.app.repository.StorageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final ProductPictureRepository productPictureRepository;
    private final StorageRepository storageRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductPictureRepository productPictureRepository,
                              StorageRepository storageRepository) {
        this.productRepository = productRepository;
        this.productPictureRepository = productPictureRepository;
        this.storageRepository = storageRepository;
    }


    @Override
    public Set<ProductListDto> getAllProductsWithPicture() {
        try {
            log.info("Getting products with picture.");
            Set<ProductListDto> productListDtos = productRepository.getProductsListWithPicture();
            return productListDtos;
        } catch (Exception e) {
            log.error("Getting products with picture failed!!!", e);
            return Collections.emptySet();
        }

    }

    @Override
    public ProductPageDto getProductPage(Long id) {
        try {
            log.info("Trying to find product with id: {}", id);
            Optional<ProductPageDto> productPage = productRepository.getProductPage(id);
            return productPage.orElseThrow();
        } catch (NoSuchElementException e) {
            log.warn("Element not found!!!", e);
        } catch (Exception e) {
            log.error("Error while searching for product", e);
        }
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        try {
            log.info("Trying to save product.");
//            ProductPicture productPicture = productPictureRepository.saveAndFlush(product.getProductPicture());
//            Storage storage = storageRepository.saveAndFlush(product.getStorage());
            product.getProductPicture().setProduct(product);
            product.getStorage().setProduct(product);
            return productRepository.saveAndFlush(product);
        } catch (Exception e) {
            log.error("Error while trying to save product.");
            return null;
        }
    }

    @Override
    public ProductPersistDto updateProduct(ProductPersistDto product) {
        try {
            log.info("Trying to update product.");
            productRepository.save(product.getProduct());
            productPictureRepository.save(product.getProductPicture());
            storageRepository.save(product.getStorage());
            return product;
        } catch (Exception e) {
            log.error("Error while updating product!!!", e);
            return null;
        }
    }

    @Override
    public Boolean deleteProduct(Product product, Long id) {
        try {
            log.info("Trying to delete product.");
            if (Objects.isNull(product)) {
                productRepository.deleteById(id);
                return true;
            }
            productRepository.delete(product);
            return true;
        } catch (Exception e) {
            log.error("Error while deleting product!!!", e);
            return false;
        }
    }

}
