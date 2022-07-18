package com.example.java5.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.java5.demo.domain.OrderDetailKey;
import com.example.java5.demo.domain.OrdersDetail;

public interface OrderDetailRepository extends JpaRepository<OrdersDetail, OrderDetailKey> {

}
