package com.example.java5.demo.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordersDetail")
public class OrdersDetail {

    // @Column(nullable = false)
    // private int orderId;
    // @Column(nullable = false)
    // private int productId;
    @EmbeddedId
    OrderDetailKey id;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    private Orders orders;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    private Products products;

    @Column(nullable = false)
    private Float price;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private float total;
    @Column(nullable = false)
    private String image;
}
