package com.example.demo.app.model.entity;

import com.example.demo.app.model.entity.custom.ProductType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product", schema = "storedb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false, length = 2048)
    private String description;
    @Column(name = "capacity", nullable = false)
    private Integer capacity;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType type;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ref_product_order",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    @JsonIgnore
    private Set<Order> orders = new LinkedHashSet<>();
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Storage storage;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ProductPicture productPicture;

    public Product(Long id, String name, Integer capacity, Double price, ProductType type, String description,
                   Integer quantity, String source) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.type = type;
        this.description = description;
        this.storage = new Storage();
        this.storage.setProductId(id);
        this.storage.setCount(quantity);
        this.productPicture = new ProductPicture();
        this.productPicture.setId(id);
        this.productPicture.setSource(source);
    }

    public Product(String name, Integer capacity, Double price, ProductType type, String description,
                   Integer quantity, String source) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.type = type;
        this.description = description;
        this.storage = new Storage();
        this.storage.setProductId(id);
        this.storage.setCount(quantity);
        this.productPicture = new ProductPicture();
        this.productPicture.setId(id);
        this.productPicture.setSource(source);
    }
}