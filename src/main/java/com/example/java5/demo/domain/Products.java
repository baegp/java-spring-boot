package com.example.java5.demo.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "products")
public class Products implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 200, nullable = false)
    private String image;
    @Column(nullable = false)
    private Float price;
    @Column(length = 50, nullable = false)
    private boolean available;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    // @EqualsAndHashCode.Exclude
    // @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category categoryId;

    @OneToMany(mappedBy = "products", cascade = { CascadeType.ALL })
    Set<OrdersDetail> ordersDetail;

    @PrePersist
    private void createAt() {
        createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        createDate = new Date();
    }

}
