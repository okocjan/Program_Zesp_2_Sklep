package com.example.demo.app.service;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.dto.ProductUpdateDto;
import com.example.demo.app.model.dto.projection.ProductListDto;
import com.example.demo.app.model.dto.projection.ProductPageDto;
import com.example.demo.app.model.entity.Product;

import java.util.Set;

public interface IProductService {

    Set<ProductListDto> getAllProductsWithPicture();

    ProductPageDto getProductPage(Long id);

    Product addProduct(ProductPersistDto product);

    Product updateProduct(ProductUpdateDto product);

    Boolean deleteProduct(Long id);
}
