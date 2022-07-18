package com.example.java5.demo.servicelmp;

import com.example.java5.demo.domain.Orders;
import com.example.java5.demo.dto.OrderDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java5.demo.repository.OrderRepository;
import com.example.java5.demo.service.OrderService;
import com.example.java5.demo.utils.DBProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    Connection conn = DBProvider.getConnection();

    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    public <S extends Orders> S save(S entity) {
        return orderRepository.save(entity);
    }

    public Optional<Orders> findById(Integer id) {
        return orderRepository.findById(id);
    }

    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    public void delete(Orders entity) {
        orderRepository.delete(entity);
    }
}
