package com.example.demo.app.model.responses;

import com.example.demo.app.model.entity.Product;
import com.example.demo.app.tools.GenericResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.BindingResult;

import java.util.*;

public class ProductResponse extends GenericResponse {

    @JsonProperty(value = "product")
    List<Product> products;

    private ProductResponse(boolean success, Product product) {
        super(success);
        this.products = Collections.singletonList(product);
    }

    private ProductResponse(boolean success) {
        super(success);
    }

    private ProductResponse(boolean success, Set<Product> product) {
        super(success);
        this.products = product.stream().toList();
    }

    private ProductResponse(boolean success, List<String> messages) {
        super(success, messages);
    }

    public static ProductResponse success(Product product) {
        return new ProductResponse(true, product);
    }

    public static ProductResponse success(Set<Product> product) {
        return new ProductResponse(true, product);
    }

    public static ProductResponse success() {
        return new ProductResponse(true);
    }

    public static ProductResponse failed(BindingResult result) {
        Set<String> messages = new HashSet<>();
        result.getAllErrors().forEach(e -> messages.add(e.getDefaultMessage()));
        return new ProductResponse(false, messages.stream().toList());
    }

    public static ProductResponse failed(List<String> messages) {
        return new ProductResponse(false, messages);
    }

    public static ProductResponse failed(String message) {
        return new ProductResponse(false, Collections.singletonList(message));
    }

}
