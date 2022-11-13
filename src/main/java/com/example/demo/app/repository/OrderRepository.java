package com.example.demo.app.repository;

import com.example.demo.app.model.entity.Order;
import com.example.demo.app.model.entity.custom.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Set<Order> findOrdersByStatusOrderByOrderDate(@Param(value = "status") Status status);

    Set<Order> findOrdersByStatusAndIdInOrderByOrderDate(@Param(value = "status") Status status,
                                                         @Param(value = "id") List<Long> id);

}
