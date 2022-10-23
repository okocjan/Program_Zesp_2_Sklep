package com.example.demo.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_picture", schema = "storedb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductPicture implements Serializable {

    @Id
    @Column(name = "product_id", nullable = false)
    @JsonIgnore
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @Column(name = "source", nullable = false, length = 1024)
    private String source;

}