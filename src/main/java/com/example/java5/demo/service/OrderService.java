package com.example.java5.demo.service;

import com.example.java5.demo.domain.Orders;
import com.example.java5.demo.dto.OrderDTO;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    void deleteById(Integer id);

    List<Orders> findAll();

    Optional<Orders> findById(Integer id);

    <S extends Orders> S save(S entity);

    void delete(Orders entity);

}
