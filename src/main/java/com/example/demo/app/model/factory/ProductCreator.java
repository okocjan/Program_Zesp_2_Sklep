package com.example.demo.app.model.factory;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.dto.ProductUpdateDto;
import com.example.demo.app.model.dto.StorageUpdateDto;
import com.example.demo.app.model.entity.Product;

public class ProductCreator {

    public static Product createProductToUpdate(ProductUpdateDto dto, String source, String deleteHash) {
        return new Product(dto.getId(), dto.getName(), dto.getPrice(), dto.getType(),
                dto.getDescription(), dto.getQuantity(), source, deleteHash);
    }

    public static Product createProductToPersist(ProductPersistDto dto, String source, String deleteHash) {
        return new Product(dto.getName(), dto.getPrice(), dto.getType(),
                dto.getDescription(), dto.getQuantity(), source, deleteHash);
    }

    public static StorageUpdateDto createStorageToUpdate(Long id, Integer quantity) {
        return new StorageUpdateDto(id, quantity);
    }

}
