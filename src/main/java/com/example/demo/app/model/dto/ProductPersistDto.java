package com.example.demo.app.model.dto;

import com.example.demo.app.model.entity.custom.ProductType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductPersistDto {

    @NotNull(message = "Field description can not be null.")
    @Size(max = 2048, message = "Field description can not exceed 2048 characters.")
    private String description;
    @NotNull(message = "Field name can not be null.")
    @Size(max = 255, message = "Field name can not exceed 255 characters.")
    private String name;
    @NotNull(message = "Field price can not be null.")
    private Double price;
    @NotNull(message = "Field type can not be null.")
    private ProductType type;
    @NotNull(message = "Field quantity can not be null.")
    private Integer quantity;
    @NotNull(message = "Field image can not be null.")
    private MultipartFile image;

}
