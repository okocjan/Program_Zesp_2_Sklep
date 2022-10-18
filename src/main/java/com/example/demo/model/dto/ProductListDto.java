package com.example.demo.model.dto;

import com.example.demo.model.entity.custom.ProductType;

public class ProductListDto {

    private Long id;
    private String name;
    private Integer capacity;
    private Double price;
    private ProductType type;
    private String imageSource;

    public ProductListDto(Long id, String name, Integer capacity, Double price, ProductType type, String imageSource) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.type = type;
        this.imageSource = imageSource;
    }

    public ProductListDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}
