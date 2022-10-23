package com.example.demo.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_send", schema = "storedb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderSend implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "tracking_number", nullable = false)
    private Integer trackingNumber;

    @Column(name = "delivery_company", nullable = false)
    private Integer deliveryCompany;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

}