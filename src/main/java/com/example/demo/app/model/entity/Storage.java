package com.example.demo.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "storage", schema = "storedb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Storage implements Serializable {

    @Id
    @Column(name = "product_id")
    @JsonIgnore
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @Column(name = "count", nullable = false)
    private Integer count;

    @JsonIgnore
    public void substractQuantity() {
        this.count--;
    }

}