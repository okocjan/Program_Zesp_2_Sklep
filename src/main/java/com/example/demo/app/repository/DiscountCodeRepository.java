package com.example.demo.app.repository;

import com.example.demo.app.model.entity.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {

    DiscountCode getDiscountCodeByCode(@Param(value = "code") String code);

}
