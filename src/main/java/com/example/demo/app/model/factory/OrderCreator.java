package com.example.demo.app.model.factory;

import com.example.demo.app.model.dto.OrderPersistDto;
import com.example.demo.app.model.dto.OrderUpdateDto;
import com.example.demo.app.model.entity.DiscountCode;
import com.example.demo.app.model.entity.Order;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.model.entity.custom.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class OrderCreator {

    public static Order createOrderToUpdate(OrderUpdateDto dto, DiscountCode code) {
        return new Order(dto.getId(), dto.getName(), dto.getLastName(), dto.getEmail(), dto.getPhoneNumber(),
                dto.getAddress(), dto.getTotalPrice(), null, LocalDate.now(), dto.getStatus(),
                dto.getDeliveryType(), code, null);
    }

    public static Order createOrderToPersist(OrderPersistDto dto, DiscountCode discount, List<Product> products) {
        return new Order(null, dto.getName(), dto.getLastName(), dto.getEmail(), dto.getPhoneNumber(),
                dto.getAddress(), dto.getTotalPrice(), UUID.randomUUID(), LocalDate.now(), Status.NEW,
                dto.getDeliveryType(), discount, products);
    }

}
