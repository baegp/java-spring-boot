package com.example.java5.demo.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private int orderId;
    private int productId;
    private float price;
    private int quantity;
    private float total;
    private String image;
}
