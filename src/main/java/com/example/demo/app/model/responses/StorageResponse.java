package com.example.demo.app.model.responses;

import com.example.demo.app.model.entity.Product;
import com.example.demo.app.tools.GenericResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StorageResponse extends GenericResponse {

    @JsonProperty(value = "storage")
    List<Product> storage;

    private StorageResponse(boolean success, Product product) {
        super(success);
        this.storage = Collections.singletonList(product);
    }

    private StorageResponse(boolean success) {
        super(success);
    }

    private StorageResponse(boolean success, Set<Product> product) {
        super(success);
        this.storage = product.stream().toList();
    }

    private StorageResponse(boolean success, List<String> messages) {
        super(success, messages);
    }

    public static StorageResponse success(Product product) {
        return new StorageResponse(true, product);
    }

    public static StorageResponse success(Set<Product> product) {
        return new StorageResponse(true, product);
    }

    public static StorageResponse success() {
        return new StorageResponse(true);
    }

    public static StorageResponse failed(BindingResult result) {
        Set<String> messages = new HashSet<>();
        result.getAllErrors().forEach(e -> messages.add(e.getDefaultMessage()));
        return new StorageResponse(false, messages.stream().toList());
    }

    public static StorageResponse failed(List<String> messages) {
        return new StorageResponse(false, messages);
    }

    public static StorageResponse failed(String message) {
        return new StorageResponse(false, Collections.singletonList(message));
    }
    
}
