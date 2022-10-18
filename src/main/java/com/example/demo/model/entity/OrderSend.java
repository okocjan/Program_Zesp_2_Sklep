package com.example.demo.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_send", schema = "storedb")
public class OrderSend implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "tracking_number", nullable = false)
    private Integer trackingNumber;

    @Column(name = "delivery_company", nullable = false)
    private Integer deliveryCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Integer trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Integer getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(Integer deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

}