package com.example.demo.app.service;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.model.dto.projection.ProductListDto;
import com.example.demo.app.model.dto.projection.ProductPageDto;

import java.util.Set;

public interface IProductService {

    Set<ProductListDto> getAllProductsWithPicture();

    ProductPageDto getProductPage(Long id);

    Product addProduct(Product product);

    ProductPersistDto updateProduct(ProductPersistDto product);

    Boolean deleteProduct(Product product, Long id);
}
