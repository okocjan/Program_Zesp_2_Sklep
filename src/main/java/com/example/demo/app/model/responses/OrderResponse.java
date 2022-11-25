package com.example.demo.app.model.responses;

import com.example.demo.app.model.entity.Order;
import com.example.demo.app.tools.GenericResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderResponse extends GenericResponse {

    @JsonProperty(value = "orders")
    List<Order> orders;

    private OrderResponse(boolean success, Order order) {
        super(success);
        this.orders = Collections.singletonList(order);
    }

    private OrderResponse(boolean success) {
        super(success);
    }

    private OrderResponse(boolean success, Set<Order> orders) {
        super(success);
        this.orders = orders.stream().toList();
    }

    private OrderResponse(boolean success, List<String> messages) {
        super(success, messages);
    }

    public static OrderResponse success(Order order) {
        return new OrderResponse(true, order);
    }

    public static OrderResponse success(Set<Order> orders) {
        return new OrderResponse(true, orders);
    }

    public static OrderResponse success() {
        return new OrderResponse(true);
    }

    public static OrderResponse failed(BindingResult result) {
        Set<String> messages = new HashSet<>();
        result.getAllErrors().forEach(e -> messages.add(e.getDefaultMessage()));
        return new OrderResponse(false, messages.stream().toList());
    }

    public static OrderResponse failed(List<String> messages) {
        return new OrderResponse(false, messages);
    }

    public static OrderResponse failed(String message) {
        return new OrderResponse(false, Collections.singletonList(message));
    }
    
}
