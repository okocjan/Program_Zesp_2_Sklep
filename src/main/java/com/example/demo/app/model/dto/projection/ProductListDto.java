package com.example.demo.app.model.dto.projection;


import com.example.demo.app.model.entity.custom.ProductType;

public interface ProductListDto {

    Long getId();
    String getName();
    Integer getCapacity();
    Double getPrice();
    ProductType getType();
    String getDescription();
    String getImageSource();

}
