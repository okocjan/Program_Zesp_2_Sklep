package com.example.demo.app.model.dto;

import com.example.demo.app.model.entity.custom.DeliveryType;
import com.example.demo.app.model.entity.custom.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDto {

    @NotNull(message = "Field id can not be null.")
    private Long id;
    @NotNull(message = "Field name can not be null.")
    @Size(max = 150, message = "Field name can not exceed 150 characters.")
    private String name;
    @NotNull(message = "Field lastName can not be null.")
    @Size(max = 150, message = "Field lastName can not exceed 150 characters.")
    private String lastName;
    @NotNull(message = "Field email can not be null.")
    @Size(max = 150, message = "Field email can not exceed 150 characters.")
    @Email(message = "Field email has to be email")
    private String email;
    @NotNull(message = "Field phoneNumber can not be null.")
    @Size(max = 12, message = "Field phoneNumber can not exceed 12 characters.")
    private String phoneNumber;
    @NotNull(message = "Field address can not be null.")
    @Size(max = 150, message = "Field address can not exceed 150 characters.")
    private String address;
    @NotNull(message = "Field totalPrice can not be null.")
    private Double totalPrice;
    @NotNull(message = "Field status can not be null.")
    @Size(max = 15, message = "Field status can not exceed 15 characters.")
    private Status status;
    @NotNull(message = "Field deliveryType can not be null.")
    @Size(max = 10, message = "Field deliveryType can not exceed 10 characters.")
    private DeliveryType deliveryType;
    private String discount;

}
