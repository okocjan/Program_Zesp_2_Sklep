package com.example.demo.app.controller;

import com.example.demo.app.model.dto.OrderPersistDto;
import com.example.demo.app.model.dto.OrderUpdateDto;
import com.example.demo.app.model.entity.Order;
import com.example.demo.app.model.entity.custom.Status;
import com.example.demo.app.model.responses.OrderResponse;
import com.example.demo.app.service.IOrderService;
import com.example.demo.app.tools.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.demo.app.model.responses.OrderResponse.failed;
import static com.example.demo.app.model.responses.OrderResponse.success;

@Controller
@RequestMapping("/order")
@Api(tags = "Orders", description = "Orders API")
@CrossOrigin
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<OrderResponse> getAll() {
        Set<Order> result = orderService.getAll();
        return result.isEmpty()
                ? new ResponseEntity<>(success(result), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getProduct(@PathVariable(name = "id") Long id) {
        Optional<Order> result = orderService.getOrder(id);
        return result
                .map(order -> new ResponseEntity<>(success(order), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(failed(Constants.ELEMENT_NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addProduct(@RequestBody @Valid OrderPersistDto order,
                                                      BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(failed(result), HttpStatus.BAD_REQUEST);
        }
        Order toReturn = orderService.addOrder(order);
        return new ResponseEntity<>(success(toReturn), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrderResponse> updateProduct(@RequestBody @Valid OrderUpdateDto order,
                                                         BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(failed(result), HttpStatus.BAD_REQUEST);
        }
        Order toReturn = orderService.updateOrder(order);
        return new ResponseEntity<>(success(toReturn), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<OrderResponse> modifyOrderStatus(@RequestParam List<Long> idList,
                                                           @RequestParam Status status) {
        boolean result = orderService.changeOrderStatus(idList, status);
        return result
                ? new ResponseEntity<>(success(), HttpStatus.OK)
                : new ResponseEntity<>(failed(Constants.ELEMENT_NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
    }
}
