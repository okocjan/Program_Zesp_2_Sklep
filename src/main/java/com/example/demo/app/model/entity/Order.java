package com.example.demo.app.model.entity;

import com.example.demo.app.model.entity.custom.Stauts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"order\"", schema = "storedb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "address", nullable = false)
    private String deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private DiscountCode discount;

    @ManyToMany(mappedBy = "orders")
    private List<Product> products = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
    @PrimaryKeyJoinColumn
    private OrderSend order;

    @Enumerated(EnumType.STRING)
    private Stauts status;

}