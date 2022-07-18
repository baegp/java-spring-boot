package com.example.java5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.java5.demo.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
