package com.example.demo.app.service;

import com.example.demo.app.model.dto.OrderPersistDto;
import com.example.demo.app.model.dto.OrderUpdateDto;
import com.example.demo.app.model.entity.Order;
import com.example.demo.app.model.entity.custom.Status;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IOrderService {

    Optional<Order> getOrder(Long id);

    Set<Order> getAll();

    Set<Order> getAllWithStatus(Status stauts);

    Order updateOrder(OrderUpdateDto orderUpdateDto);

    Order addOrder(OrderPersistDto orderPersistDto);

    boolean changeOrderStatus(List<Long> idList, Status status);



}
