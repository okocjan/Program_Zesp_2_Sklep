package com.example.demo.app.model.dto;

import com.example.demo.app.model.entity.custom.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDto {

    @Nullable
    private Long id;
    private String name;
    private Integer capacity;
    private Double price;
    private ProductType type;
    private String description;
    private Integer quantity;
    private String source;

}
