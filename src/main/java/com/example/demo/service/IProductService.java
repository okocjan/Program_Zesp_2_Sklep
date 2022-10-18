package com.example.demo.service;

import com.example.demo.model.dto.ProductListDto;

import java.util.Set;

public interface IProductService {

    Set<ProductListDto> getAllProductsWithPicture();

}
