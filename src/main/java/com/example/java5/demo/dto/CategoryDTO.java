package com.example.java5.demo.dto;

import javax.validation.constraints.NotEmpty;

// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component

public class CategoryDTO {
    private int categoryId;
    @NotEmpty
    private String name;

}
