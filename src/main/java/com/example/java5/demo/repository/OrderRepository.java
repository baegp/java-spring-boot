package com.example.java5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.java5.demo.domain.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
