package com.example.demo.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountCodePersistDto {

    @NotNull(message = "Field code can not be null")
    @NotEmpty(message = "Field code can not be empty")
    @Size(max = 30, message = "Field code can not exceed 30 characters")
    private String code;
    @NotNull(message = "Field discount can not be null")
    private Integer discount;

}
