package com.example.java5.demo.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String image;
    private float price;
    private boolean available;
    private Date createDate;
    private int categoryId;
}
