package com.example.demo.app.model.dto;

import com.example.demo.app.model.entity.custom.DeliveryType;
import com.example.demo.app.model.entity.custom.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderPersistDto {

    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Double totalPrice;
    private DeliveryType deliveryType;
    private String discount;
    private List<Long> products;

}
