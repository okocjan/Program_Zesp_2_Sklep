package com.example.demo.app.model.factory;

import com.example.demo.app.model.dto.DiscountCodeDto;
import com.example.demo.app.model.entity.DiscountCode;

public class DiscountCodeCreator {

    public static DiscountCode createDiscountCode(DiscountCodeDto discountCodeDto) {
        return new DiscountCode(discountCodeDto.getId(), discountCodeDto.getCode(), discountCodeDto.getDiscount(), discountCodeDto.isActive());
    }

}
