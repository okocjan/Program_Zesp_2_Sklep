package com.example.demo.app.service;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.dto.ProductUpdateDto;
import com.example.demo.app.model.entity.Product;

import java.util.Optional;
import java.util.Set;

public interface IProductService {

    Set<Product> getAllProductsWithPicture();

    Optional<Product> getProductPage(Long id);

    Product addProduct(ProductPersistDto product);

    Product updateProduct(ProductUpdateDto product);

    Boolean deleteProduct(Long id);
}
