package com.example.demo.app.service;

import com.example.demo.app.model.dto.OrderPersistDto;
import com.example.demo.app.model.dto.OrderUpdateDto;
import com.example.demo.app.model.entity.*;
import com.example.demo.app.model.entity.custom.Status;
import com.example.demo.app.repository.OrderRepository;
import com.example.demo.app.repository.ProductOrderRepository;
import com.example.demo.app.repository.StorageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.app.model.factory.OrderCreator.createOrderToPersist;
import static com.example.demo.app.model.factory.OrderCreator.createOrderToUpdate;

@Service
public class OrderServiceImpl implements IOrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final DiscountCodeService discountCodeService;
    private final ProductServiceImpl productService;
    private final ProductOrderRepository productOrderRepository;
    private final StorageRepository storageRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            DiscountCodeService discountCodeService,
                            ProductServiceImpl productService,
                            ProductOrderRepository productOrderRepository,
                            StorageRepository storageRepository) {
        this.orderRepository = orderRepository;
        this.discountCodeService = discountCodeService;
        this.productService = productService;
        this.productOrderRepository = productOrderRepository;
        this.storageRepository = storageRepository;
    }

    @Override
    public Optional<Order> getOrder(Long id) {
        try {
            log.info("Trying to find order with id: {}", id);
            return orderRepository.findById(id);
        } catch (NoSuchElementException e) {
            log.warn("Element not found!!!", e);
        } catch (Exception e) {
            log.error("Error while searching for order!!!\n{0}", e);
        }
        return Optional.empty();
    }

    @Override
    public Set<Order> getAll() {
        try {
            log.info("Trying to get orders");
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
            log.info("Trying to update order.");
            DiscountCode code = null;
            if (Objects.nonNull(orderUpdateDto.getDiscount())) {
                code = discountCodeService.getByCode(orderUpdateDto.getDiscount()).orElse(null);
            }
            Order toSave = createOrderToUpdate(orderUpdateDto, code);
            return orderRepository.save(toSave);
        } catch (Exception e) {
            log.error("Error while updating order!!!\n{0}", e);
            return null;
        }
    }

    @Override
    public Order addOrder(OrderPersistDto orderPersistDto) {
        try {
            log.info("Trying to persist order.");
            Optional<DiscountCode> discountCode = Optional.empty();
            String discount = orderPersistDto.getDiscount();
            if (Objects.nonNull(discount)) {
                discountCode = discountCodeService.getByCode(discount);
            }

            List<Product> products = productService.getAllProductsByIdWithDuplicates(orderPersistDto.getProducts());
            Order persisted = orderRepository.saveAndFlush(createOrderToPersist(orderPersistDto, discountCode.orElse(null), products));
            List<ProductOrder> productOrderList = persisted.getProducts().stream()
                            .map(obj -> new ProductOrder(null, persisted, obj)).toList();
            List<Storage> storageUpdateList = createStorageListToUpdate(products);

            storageRepository.saveAllAndFlush(storageUpdateList);
            productOrderRepository.saveAllAndFlush(productOrderList);
            return persisted;
        } catch (Exception e) {
            log.error("Error while persisting order!!!\n{0}", e);
            return null;
        }
    }

    private List<Storage> createStorageListToUpdate(List<Product> products) {
        Map<Long, Storage> map = products.stream().collect(Collectors.toMap(Product::getId, Product::getStorage, (id1, id2) -> id1));
        for (Product product : products) {
            map.get(product.getId()).substractQuantity();
        }
        return map.values().stream().toList();
    }

    @Override
    public boolean changeOrderStatus(List<Long> idList, Status status) {
        try {
            log.info("Trying to change orders status");
            Set<Order> orders = orderRepository.findOrdersByIdInOrderByOrderDate(idList);
            orders = orders.stream()
                    .peek(order -> order.setStatus(status)).collect(Collectors.toSet());
            orderRepository.saveAllAndFlush(orders);
            return true;
        } catch (Exception e) {
            log.error("Error while trying to change orders status");
            return false;
        }
    }
}
