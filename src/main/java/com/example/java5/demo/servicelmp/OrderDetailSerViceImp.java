package com.example.java5.demo.servicelmp;

import com.example.java5.demo.domain.OrderDetailKey;
import com.example.java5.demo.domain.OrdersDetail;
import com.example.java5.demo.dto.OrderDTO;
import com.example.java5.demo.dto.OrderDetailDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java5.demo.repository.OrderDetailRepository;
import com.example.java5.demo.service.OrderDetailService;
import com.example.java5.demo.utils.DBProvider;

import groovy.transform.AutoClone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailSerViceImp implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    public List<OrdersDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    public <S extends OrdersDetail> S save(S entity) {
        return orderDetailRepository.save(entity);
    }

    public Optional<OrdersDetail> findById(OrderDetailKey id) {
        return orderDetailRepository.findById(id);
    }

    public void deleteById(OrderDetailKey id) {
        orderDetailRepository.deleteById(id);
    }

    public void delete(OrdersDetail entity) {
        orderDetailRepository.delete(entity);
    }

    Connection conn = DBProvider.getConnection();

    public List<OrderDetailDTO> getListOrderDetail(int id) {
        List<OrderDetailDTO> ListCat = new ArrayList<OrderDetailDTO>();
        try {
            String sql = "SELECT * FROM orders_detail where order_id =  ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                OrderDetailDTO posts = new OrderDetailDTO(rst.getInt(1), rst.getInt(2), rst.getFloat(3), rst.getInt(4),
                        rst.getFloat(5), rst.getString(6));
                ListCat.add(posts);
                System.out.println("products data" + posts);
            }
            return ListCat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
