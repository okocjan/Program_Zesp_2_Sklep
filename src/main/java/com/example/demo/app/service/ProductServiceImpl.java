package com.example.demo.app.service;

import com.example.demo.app.model.dto.ProductListDto;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.model.entity.ProductPicture;
import com.example.demo.app.repository.ProductPictureRepository;
import com.example.demo.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ProductPictureRepository productPictureRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductPictureRepository productPictureRepository) {
        this.productRepository = productRepository;
        this.productPictureRepository = productPictureRepository;
    }


    @Override
    public Set<ProductListDto> getAllProductsWithPicture() {
        Set<Product> products = new HashSet<>(productRepository.findAll());
        Map<Long, ProductPicture> productPictures = productPictureRepository.findAll()
                .stream().collect(Collectors.toMap(ProductPicture::getId, Function.identity()));
        Set<ProductListDto> productListDtos = new HashSet<>();

        for (Product product : products) {
            String productImageSource = productPictures.get(product.getId()).getSource();
            productListDtos.add(new ProductListDto(product.getId(),
                    product.getName(),
                    product.getCapacity(),
                    product.getPrice(),
                    product.getType(),
                    productImageSource));
        }

        return productListDtos;
    }

}
