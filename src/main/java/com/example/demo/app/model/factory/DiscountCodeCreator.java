package com.example.demo.app.model.factory;

import com.example.demo.app.model.dto.DiscountCodePersistDto;
import com.example.demo.app.model.entity.DiscountCode;

public class DiscountCodeCreator {

    public static DiscountCode createDiscountCode(DiscountCodePersistDto persistDto) {
        return new DiscountCode(null, persistDto.getCode(), persistDto.getDiscount(), true);
    }

}
