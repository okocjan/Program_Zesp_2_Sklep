package com.example.demo.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountCodeDto {

    private Long id;
    private String code;
    private Integer discount;
    private boolean active;

}
