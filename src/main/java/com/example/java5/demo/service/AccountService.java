/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.java5.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.example.java5.demo.domain.Account;
import com.example.java5.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

/**
 *
 * @author tranh
 */
public interface AccountService {
    long count();

    <S extends Account> long count(Example<S> example);

    void delete(Account entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Account> entities);

    void deleteAllById(Iterable<? extends String> ids);

    void deleteAllByIdInBatch(Iterable<String> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Account> entities);

    void deleteById(String id);

    void deleteInBatch(Iterable<Account> entities);

    <S extends Account> boolean exists(Example<S> example);

    boolean existsById(String id);

    List<Account> findAll();

    <S extends Account> List<S> findAll(Example<S> example);

    <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Account> List<S> findAll(Example<S> example, Sort sort);

    Page<Account> findAll(Pageable pageable);

    List<Account> findAll(Sort sort);

    List<Account> findAllById(Iterable<String> ids);

    <S extends Account, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    Optional<Account> findById(String id);

    <S extends Account> Optional<S> findOne(Example<S> example);

    void flush();

    Account getById(String id);

    Account getOne(String id);

    <S extends Account> S save(S entity);

    <S extends Account> List<S> saveAll(Iterable<S> entities);

    <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Account> S saveAndFlush(S entity);

    Account checkLogin(String username, String password);

    String getPasswordForgot(String username, String email);

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;
    // public String hasPass(String pass) {
    // try {
    // String passPass = bCryptPasswordEncoder.encode(pass);
    // return passPass;
    // } catch (Exception e) {
    // return null;
    // }
    // }
    // public boolean hassPass(String password, String hassPass) {
    // try {
    // String passHash = bCryptPasswordEncoder.encode(password);
    // return passHash;
    // } catch (Exception e) {
    // return null;
    // }
    // }
    // public boolean compare(String username, String password) {
    // }
    boolean isExistAccountForget(String username, String email);

    // String updatePass(String password, String username);
    // boolean up(String password, String username);

    // public String updatePass(String password, String username) {
    // PasswordHelpers passwordHelpers = new PasswordHelpers();
    // try {
    // String sql = "UPDATE accounts SET password = ? WHERE username = ?";
    // PreparedStatement pst = conn.prepareCall(sql);
    // pst.setString(1, password);
    // pst.setString(2, username);
    // ResultSet rs = pst.executeQuery();
    // if (rs.next()) {
    // return passwordHelpers.encrypt(rs.getString("password"));
    // }
    // } catch (SQLException ex) {
    // ex.printStackTrace();
    // System.err.println(ex.getMessage());
    // }
    // return "false";
    // }
    void updateForget(String password, String username);

}