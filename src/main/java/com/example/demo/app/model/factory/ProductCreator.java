package com.example.demo.app.model.factory;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.dto.ProductUpdateDto;
import com.example.demo.app.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductCreator {

    public static Product createProductToUpdate(ProductUpdateDto dto) {
        return new Product(dto.getId(), dto.getName(), dto.getCapacity(), dto.getPrice(), dto.getType(),
                dto.getDescription(), dto.getQuantity(), dto.getSource());
    }

    @JsonIgnore
    public static Product createProductToPersist(ProductPersistDto dto) {
        return new Product(dto.getName(), dto.getCapacity(), dto.getPrice(), dto.getType(),
                dto.getDescription(), dto.getQuantity(), dto.getSource());
    }
}
