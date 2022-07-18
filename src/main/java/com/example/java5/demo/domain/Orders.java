package com.example.java5.demo.domain;

import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 15, nullable = false)
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @PrePersist
    private void createAt() {
        createDate = new Date();
    }

    @Column(length = 200, nullable = false)
    private String note;
    private float total;
    private int status;
    @Column(length = 100, nullable = false)
    private String address;
    @Column(length = 100, nullable = false)
    private String email;

    @OneToMany(mappedBy = "orders")
    Set<OrdersDetail> ordersDetail;

    @ManyToOne
    @JoinColumn(name = "accountOrder")
    private Account account;
}
