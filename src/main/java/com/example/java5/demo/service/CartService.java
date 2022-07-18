package com.example.java5.demo.service;

import org.springframework.stereotype.Service;

import com.example.java5.demo.domain.Products;

@Service
public interface CartService {
    public int add(Products p);

    public int remove(int id);
}
