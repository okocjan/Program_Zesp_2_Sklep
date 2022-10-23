package com.example.demo.app.service;

import com.example.demo.app.model.dto.ProductListDto;

import java.util.Set;

public interface IProductService {

    Set<ProductListDto> getAllProductsWithPicture();

}
