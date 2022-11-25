package com.example.demo.app.repository;

import com.example.demo.app.model.entity.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {

    Optional<DiscountCode> getDiscountCodeByCode(@Param(value = "code") String code);

    List<DiscountCode> getDiscountCodesByIdIn(@Param(value = "ids") List<Long> ids);

}
