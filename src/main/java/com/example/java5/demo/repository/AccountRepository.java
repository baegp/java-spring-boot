package com.example.java5.demo.repository;

import com.example.java5.demo.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT a FROM Account a WHERE a.username = :username and a.password = :password ")
    public Account checkLogin(@Param("username") String username, @Param("password") String password);

    // @Query("SELECT u FROM accounts u WHERE u.username = :username and u.password
    // = :password")
    // public Account checkLogin(@Param("username") String username,
    // @Param("password") String password);
}
