package com.example.demo.app.service;

import com.example.demo.app.model.dto.DiscountCodePersistDto;
import com.example.demo.app.model.entity.DiscountCode;

import java.util.List;
import java.util.Optional;

public interface IDiscountCodeService {

    Optional<DiscountCode> getById(Long id);

    Optional<DiscountCode> getByCode(String code);

    DiscountCode addDiscountCode(DiscountCodePersistDto discountCodePersistDto);

    boolean toggleCode(List<Long> ids);

}
