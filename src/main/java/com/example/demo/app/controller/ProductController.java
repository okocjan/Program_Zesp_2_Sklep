package com.example.demo.app.controller;

import com.example.demo.app.model.dto.ProductListDto;
import com.example.demo.app.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products.json")
    public ResponseEntity<Set<ProductListDto>> getProductsToView() {
        return new ResponseEntity<>(productService.getAllProductsWithPicture(), HttpStatus.OK);
    }

}
