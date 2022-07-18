package com.example.java5.demo.servicelmp;

import com.example.java5.demo.domain.Products;
import com.example.java5.demo.dto.ProductDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java5.demo.repository.ProductRepository;
import com.example.java5.demo.service.ProductService;
import com.example.java5.demo.utils.DBProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;

    // Connection connection = DBProvider.getConnection();
    Connection conn = DBProvider.getConnection();

    public List<ProductDTO> sortByPrice(Float price) {
        List<ProductDTO> List = new ArrayList<ProductDTO>();
        try {
            String sql = "SELECT * FROM products ORDER BY price DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDTO product = new ProductDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getFloat(4),
                        rst.getBoolean(5), rst.getDate(6), rst.getInt(7));
                List.add(product);
            }
            return List;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDTO> sortByPriceMinToMax(Float price) {
        List<ProductDTO> List = new ArrayList<ProductDTO>();
        try {
            String sql = "SELECT * FROM products ORDER BY price ASC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDTO product = new ProductDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getFloat(4),
                        rst.getBoolean(5), rst.getDate(6), rst.getInt(7));
                List.add(product);
            }
            return List;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDTO> sortByNameDESC(String name) {
        List<ProductDTO> List = new ArrayList<ProductDTO>();
        try {
            String sql = "SELECT * FROM products ORDER BY name DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDTO product = new ProductDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getFloat(4),
                        rst.getBoolean(5), rst.getDate(6), rst.getInt(7));
                List.add(product);
            }
            return List;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDTO> sortByNameASC(String name) {
        List<ProductDTO> List = new ArrayList<ProductDTO>();
        try {
            String sql = "SELECT * FROM products ORDER BY name ASC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDTO product = new ProductDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getFloat(4),
                        rst.getBoolean(5), rst.getDate(6), rst.getInt(7));
                List.add(product);
            }
            return List;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDTO> getListCategory(int id) {
        List<ProductDTO> ListCat = new ArrayList<ProductDTO>();
        try {
            String sql = "SELECT * FROM products where category_id =  ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDTO posts = new ProductDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getFloat(4),
                        rst.getBoolean(5), rst.getDate(6), rst.getInt(7));
                ListCat.add(posts);
                System.out.println("products data" + posts);
            }
            return ListCat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDTO> searchByName(String name) {
        List<ProductDTO> List = new ArrayList<ProductDTO>();
        try {
            String sql = "SELECT * FROM products where name like ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDTO product = new ProductDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getFloat(4),
                        rst.getBoolean(5), rst.getDate(6), rst.getInt(7));
                List.add(product);
            }
            return List;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDTO> search(String name) {
        Connection conn = DBProvider.getConnection();
        List<ProductDTO> ListCat = new ArrayList<ProductDTO>();
        try {
            String sql = "SELECT * FROM product where name like  ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDTO product = new ProductDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getFloat(4),
                        rst.getBoolean(5), rst.getDate(6), rst.getInt(7));
                ListCat.add(product);
            }
            return ListCat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // public List<Products> listAll(String keyword) {
    // if (keyword != null) {
    // return productRepository.search(keyword);
    // }
    // return productRepository.findAll();
    // }

    public List<Products> findAll() {
        return productRepository.findAll();
    }

    public List<Products> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    public List<Products> findAllById(Iterable<Integer> ids) {
        return productRepository.findAllById(ids);
    }

    public <S extends Products> List<S> saveAll(Iterable<S> entities) {
        return productRepository.saveAll(entities);
    }

    public void flush() {
        productRepository.flush();
    }

    public <S extends Products> S saveAndFlush(S entity) {
        return productRepository.saveAndFlush(entity);
    }

    public <S extends Products> List<S> saveAllAndFlush(Iterable<S> entities) {
        return productRepository.saveAllAndFlush(entities);
    }

    public void deleteInBatch(Iterable<Products> entities) {
        productRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Products> entities) {
        productRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        productRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        productRepository.deleteAllInBatch();
    }

    public Products getOne(Integer id) {
        return productRepository.getOne(id);
    }

    public Products getById(Integer id) {
        return productRepository.getById(id);
    }

    public <S extends Products> List<S> findAll(Example<S> example) {
        return productRepository.findAll(example);
    }

    public <S extends Products> List<S> findAll(Example<S> example, Sort sort) {
        return productRepository.findAll(example, sort);
    }

    public Page<Products> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public <S extends Products> S save(S entity) {
        return productRepository.save(entity);
    }

    public Optional<Products> findById(Integer id) {
        return productRepository.findById(id);
    }

    public boolean existsById(Integer id) {
        return productRepository.existsById(id);
    }

    public long count() {
        return productRepository.count();
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public void delete(Products entity) {
        productRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> ids) {
        productRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Products> entities) {
        productRepository.deleteAll(entities);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public <S extends Products> Optional<S> findOne(Example<S> example) {
        return productRepository.findOne(example);
    }

    public <S extends Products> Page<S> findAll(Example<S> example, Pageable pageable) {
        return productRepository.findAll(example, pageable);
    }

    public <S extends Products> long count(Example<S> example) {
        return productRepository.count(example);
    }

    public <S extends Products> boolean exists(Example<S> example) {
        return productRepository.exists(example);
    }

    public <S extends Products, R> R findBy(Example<S> example,
            Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return productRepository.findBy(example, queryFunction);
    }

    @Override
    public int hashCode() {
        return productRepository.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return productRepository.equals(obj);
    }

    @Override
    public String toString() {
        return productRepository.toString();
    }

}
