package com.example.demo.app.model.entity;

import com.example.demo.app.model.entity.custom.DeliveryType;
import com.example.demo.app.model.entity.custom.Stauts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "name", nullable = false, length = 150)
    private String name;
    @Column(name = "last_name", nullable = false, length = 150)
    private String lastName;
    @Column(name = "email", nullable = false, length = 150)
    private String email;
    @Column(name = "phone_number", nullable = false, length = 12)
    private String phoneNumber;
    @Column(name = "address", nullable = false, length = 150)
    private String address;
    @Column(name = "total_price", nullable = false, precision = 2)
    private Double totalPrice;
    @Column(name = "tracking_number", nullable = false, length = 150)
    private String trackingNumber;
    @Column(name = "order_date", nullable = false, length = 30)
    private LocalDate orderDate;
    @Column(name = "status", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private Stauts status;
    @Column(name = "deliveryType", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private DiscountCode discount;

    @ManyToMany(mappedBy = "orders", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    private List<Product> products = new ArrayList<>();

}