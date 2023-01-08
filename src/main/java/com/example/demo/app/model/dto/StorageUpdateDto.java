package com.example.demo.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageUpdateDto {

    @NotNull(message = "Field productId can not be null")
    private Long productId;
    @NotNull(message = "Field quantity can not be null")
    private Integer quantity;

}
