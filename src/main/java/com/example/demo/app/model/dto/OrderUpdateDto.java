package com.example.demo.app.model.dto;

import com.example.demo.app.model.entity.DiscountCode;
import com.example.demo.app.model.entity.custom.DeliveryType;
import com.example.demo.app.model.entity.custom.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDto {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Double totalPrice;
    private Status status;
    private DeliveryType deliveryType;
    private DiscountCode discount;

}
