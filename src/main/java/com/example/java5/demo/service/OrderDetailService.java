package com.example.java5.demo.service;

import com.example.java5.demo.domain.OrderDetailKey;
import com.example.java5.demo.domain.OrdersDetail;
import com.example.java5.demo.dto.OrderDetailDTO;
import com.example.java5.demo.repository.OrderDetailRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService {

    void deleteById(OrderDetailKey id);

    List<OrdersDetail> findAll();

    Optional<OrdersDetail> findById(OrderDetailKey id);

    <S extends OrdersDetail> S save(S entity);

    List<OrderDetailDTO> getListOrderDetail(int id);

    void delete(OrdersDetail entity);

}
