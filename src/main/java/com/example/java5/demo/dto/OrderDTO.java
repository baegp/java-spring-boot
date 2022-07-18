package com.example.java5.demo.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    @NotEmpty(message = "Không được để trông ")
    private String username;
    @NotEmpty(message = "Không được để trông ")
    private String address;
    @NotEmpty(message = "Không được để trông ")
    private String phone;
    @NotEmpty(message = "Không được để trông ")
    private String note;
    @NotEmpty(message = "Không được để trông ")
    private String email;
}
