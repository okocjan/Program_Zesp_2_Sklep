package com.example.demo.app.model.dto.projection;

import com.example.demo.app.model.entity.custom.ProductType;

public interface ProductPageDto {

    Long getId();
    String getName();
    Integer getCapacity();
    Double getPrice();
    ProductType getType();
    String getSource();
    Integer getQuantity();

}
