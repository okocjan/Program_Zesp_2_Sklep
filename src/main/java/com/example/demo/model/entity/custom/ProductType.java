package com.example.demo.model.entity.custom;

public enum ProductType {

    DOG("D"),
    CAT("C"),
    FISH("F"),
    PARROT("P"),
    HAMSTER("H");

    public final String dbChar;
    private ProductType(String dbChar) {
        this.dbChar = dbChar;
    }
}
