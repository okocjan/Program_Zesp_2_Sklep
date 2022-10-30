package com.example.demo.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "discount_code", schema = "storedb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountCode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "code", nullable = false, length = 30, unique = true)
    private String code;
    @Column(name = "discount", nullable = false, length = 5)
    private Integer discount;
    @Column(name = "active", nullable = false)
    private boolean active;

}