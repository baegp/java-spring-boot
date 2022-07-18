package com.example.java5.demo.servicelmp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.example.java5.demo.repository.AccountRepository;
import com.example.java5.demo.service.AccountService;
import com.example.java5.demo.utils.DBProvider;
import com.example.java5.demo.utils.PasswordHelpers;
import com.example.java5.demo.domain.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    Connection conn = DBProvider.getConnection();
    PasswordHelpers passwordHelpers = new PasswordHelpers();

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

    public void updateForget(String password, String username) {
        String sqlUpdate = "UPDATE accounts "
                + "SET password = ? "
                + "WHERE username = ?";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            pstmt.setString(1, passwordHelpers.encrypt(password));
            pstmt.setString(2, username);

            int rowAffected = pstmt.executeUpdate();
            System.out.println(String.format("Row affected %d", rowAffected));

            pstmt.setString(1, passwordHelpers.encrypt(password));
            pstmt.setString(2, username);

            rowAffected = pstmt.executeUpdate();
            System.out.println(String.format("Row affected %d", rowAffected));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

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

    public boolean isExistAccountForget(String username, String email) {
        try {
            String sql = "SELECT * FROM accounts where username = ? and email = ? ";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, username);
            pst.setString(2, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());

        }
        return false;
    }

    public String getPasswordForgot(String username, String email) {
        PasswordHelpers passwordHelpers = new PasswordHelpers();
        try {

            String sql = "SELECT * FROM accounts where username = ? and email = ?";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, username);
            pst.setString(2, email);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return passwordHelpers.decrypt(rs.getString("password"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());

        }
        return "";
    }

    public Account checkLogin(String username, String password) {
        return accountRepository.checkLogin(username, new PasswordHelpers().encrypt(password));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    public List<Account> findAllById(Iterable<String> ids) {
        return accountRepository.findAllById(ids);
    }

    public <S extends Account> List<S> saveAll(Iterable<S> entities) {
        return accountRepository.saveAll(entities);
    }

    public void flush() {
        accountRepository.flush();
    }

    public <S extends Account> S saveAndFlush(S entity) {
        return accountRepository.saveAndFlush(entity);
    }

    public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
        return accountRepository.saveAllAndFlush(entities);
    }

    public void deleteInBatch(Iterable<Account> entities) {
        accountRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Account> entities) {
        accountRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<String> ids) {
        accountRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        accountRepository.deleteAllInBatch();
    }

    public Account getOne(String id) {
        return accountRepository.getOne(id);
    }

    public Account getById(String id) {
        return accountRepository.getById(id);
    }

    public <S extends Account> List<S> findAll(Example<S> example) {
        return accountRepository.findAll(example);
    }

    public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
        return accountRepository.findAll(example, sort);
    }

    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public <S extends Account> S save(S entity) {
        PasswordHelpers passwordHelpers = new PasswordHelpers();
        entity.setPassword(passwordHelpers.encrypt(entity.getPassword()));
        return accountRepository.save(entity);

    }

    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    public boolean existsById(String id) {
        return accountRepository.existsById(id);
    }

    public long count() {
        return accountRepository.count();
    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends String> ids) {
        accountRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Account> entities) {
        accountRepository.deleteAll(entities);
    }

    public void deleteAll() {
        accountRepository.deleteAll();
    }

    public <S extends Account> Optional<S> findOne(Example<S> example) {
        return accountRepository.findOne(example);
    }

    public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
        return accountRepository.findAll(example, pageable);
    }

    public <S extends Account> long count(Example<S> example) {
        return accountRepository.count(example);
    }

    public <S extends Account> boolean exists(Example<S> example) {
        return accountRepository.exists(example);
    }

    public <S extends Account, R> R findBy(Example<S> example,
            Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return accountRepository.findBy(example, queryFunction);
    }
}
