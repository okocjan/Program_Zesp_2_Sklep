package com.example.demo.app.model.dto.projection;


import com.example.demo.app.model.entity.custom.ProductType;

public interface ProductListDto {

    public Long getId();
    public String getName();
    public Integer getCapacity();
    public Double getPrice();
    public ProductType getType();
    public String getImageSource();

}
