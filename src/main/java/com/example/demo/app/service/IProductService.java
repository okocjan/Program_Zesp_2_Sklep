package com.example.demo.app.service;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.dto.ProductUpdateDto;
import com.example.demo.app.model.dto.StorageUpdateDto;
import com.example.demo.app.model.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IProductService {

    Set<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product addProduct(ProductPersistDto product);

    Product updateProduct(ProductUpdateDto product);

    Boolean deleteProduct(Long id);

    List<Product> getAllProductsById(List<Long> ids);

    boolean updateProductQuantity(StorageUpdateDto updateDto);
}
