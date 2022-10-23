package com.example.demo.app.model.entity.custom;

public enum ProductType {

    DOG(0),
    CAT(1),
    FISH(2),
    PARROT(3),
    HAMSTER(4);

    private final Integer enumOrdinal;
    ProductType(Integer enumOrdinal) {
        this.enumOrdinal = enumOrdinal;
    }

    public Integer getEnumOrdinal(){
        return enumOrdinal;
    }
}
