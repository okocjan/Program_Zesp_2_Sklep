package com.example.demo.app.service;

import com.example.demo.app.model.dto.OrderPersistDto;
import com.example.demo.app.model.dto.OrderUpdateDto;
import com.example.demo.app.model.entity.DiscountCode;
import com.example.demo.app.model.entity.Order;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.model.entity.custom.Status;
import com.example.demo.app.repository.DiscountCodeRepository;
import com.example.demo.app.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.app.model.factory.OrderFactory.createOrderToPersist;
import static com.example.demo.app.model.factory.OrderFactory.createOrderToUpdate;

@Service
public class OrderServiceImpl implements IOrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final DiscountCodeRepository discountCodeRepository;
    private final ProductServiceImpl productService;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, DiscountCodeRepository discountCodeRepository, ProductServiceImpl productService) {
        this.orderRepository = orderRepository;
        this.discountCodeRepository = discountCodeRepository;
        this.productService = productService;
    }

    @Override
    public Optional<Order> getOrder(Long id) {
        try {
            log.info("Trying to find order with id: {}", id);
            return orderRepository.findById(id);
        } catch (NoSuchElementException e) {
            log.warn("Element not found!!!", e);
        } catch (Exception e) {
            log.error("Error while searching for product!!!\n{0}", e);
        }
        return Optional.empty();
    }

    @Override
    public Set<Order> getAll() {
        try {
            log.info("Getting orders");
            return new HashSet<>(orderRepository.findAll());
        } catch (Exception e) {
            log.error("Getting orders failed!!!\n{0}", e);
            return Collections.emptySet();
        }
    }

    @Override
    public Set<Order> getAllWithStatus(Status stauts) {
        try {
            log.info("Getting orders with status {}", stauts);
            return orderRepository.findOrdersByStatusOrderByOrderDate(stauts);
        } catch (Exception e) {
            log.error("Getting orders failed!!!\n{0}", e);
        }
        return Collections.emptySet();
    }

    @Override
    public Order updateOrder(OrderUpdateDto orderUpdateDto) {
        try {
            log.info("Trying to update product.");
            Order toSave = createOrderToUpdate(orderUpdateDto);
            return orderRepository.save(toSave);
        } catch (Exception e) {
            log.error("Error while updating product!!!\n{0}", e);
            return null;
        }
    }

    @Override
    public Order addOrder(OrderPersistDto orderPersistDto) {
        try {
            log.info("Trying to persist product.");
            DiscountCode discountCode = null;
            if (Objects.nonNull(orderPersistDto.getDiscount())) {
                discountCode = discountCodeRepository.getDiscountCodeByCode(orderPersistDto.getDiscount());
            }
            List<Product> products = productService.getAllProductsById(orderPersistDto.getProducts());
            Order toSave = createOrderToPersist(orderPersistDto, discountCode, products);
            return orderRepository.save(toSave);
        } catch (Exception e) {
            log.error("Error while persisting product!!!\n{0}", e);
            return null;
        }
    }

    @Override
    public boolean changeOrderStatus(List<Long> idList, Status status) {
        try {
            log.info("Trying to change orders status");
            Set<Order> orders = orderRepository.findOrdersByStatusAndIdInOrderByOrderDate(status, idList);
            orders = orders.stream()
                    .peek(order -> order.setStatus(status)).collect(Collectors.toSet());
            orderRepository.saveAllAndFlush(orders);
            return true;
        } catch (Exception e) {
            log.error("Error while trying to change orders status");
        }
        return false;
    }
}
