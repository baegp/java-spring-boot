package com.example.java5.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.java5.demo.domain.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {
    // @Query("SELECT p FROM Products p WHERE name(p.name, ' ', p.image, ' ',
    // p.price) LIKE %?1%")
    // public List<Products> search(String keyword);

}
