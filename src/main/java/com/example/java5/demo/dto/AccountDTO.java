/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.java5.demo.dto;

// import java.io.Serializable;

// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tranh
 */
// @Entity
// @Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AccountDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 4)
    private String password;
    @NotEmpty
    private String email;
    private MultipartFile photo;
    private boolean activated;
    private boolean admin;

}