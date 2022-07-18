package com.example.java5.demo.service;

import com.example.java5.demo.domain.Products;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.java5.demo.dto.CategoryDTO;
import com.example.java5.demo.dto.ProductDTO;
import com.example.java5.demo.repository.ProductRepository;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

public interface ProductService {

    long count();

    <S extends Products> long count(Example<S> example);

    void delete(Products entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Products> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Products> entities);

    void deleteById(Integer id);

    void deleteInBatch(Iterable<Products> entities);

    boolean equals(Object obj);

    <S extends Products> boolean exists(Example<S> example);

    boolean existsById(Integer id);

    List<Products> findAll();

    <S extends Products> List<S> findAll(Example<S> example, Sort sort);

    Page<Products> findAll(Pageable pageable);

    List<Products> findAll(Sort sort);

    List<Products> findAllById(Iterable<Integer> ids);

    <S extends Products, R> R findBy(Example<S> example,
            Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    Optional<Products> findById(Integer id);

    <S extends Products> Optional<S> findOne(Example<S> example);

    void flush();

    Products getById(Integer id);

    Products getOne(Integer id);

    int hashCode();

    <S extends Products> S save(S entity);

    <S extends Products> List<S> saveAll(Iterable<S> entities);

    <S extends Products> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Products> S saveAndFlush(S entity);

    String toString();

    // public List<ProductsDTO> getListCat(int idCat) {
    // List<ProductsDTO> List = new ArrayList<ProductsDTO>();
    // try {
    // String sql = "SELECT * FROM products where categoryid =?";
    // PreparedStatement pst = connection.prepareStatement(sql);
    // pst.setInt(1, idCat);

    // ResultSet rst = pst.executeQuery();
    // while (rst.next()) {
    // ProductsDTO product = new ProductsDTO(rst.getInt(1), rst.getString(2),
    // rst.getString(3), rst.getFloat(4), rst.getString(5), rst.getInt(6));
    // List.add(product);
    // }
    // return List;

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

    // List<Product> list = new ArrayList<Product>();

    // public void setList(List<Product> list) {
    // this.list = list;
    // }

    // public ProductService() {
    // list.add(new Product(1, "name", "342", "sadasd", 1));
    // list.add(new Product(2, "cc", "12312", "sadsa", 2));
    // list.add(new Product(3, "cc", "12312", "sadsa", 3));
    // list.add(new Product(4, "cc", "12312", "sadsa", 4));
    // list.add(new Product(5, "cc", "12312", "sadsa", 5));
    // }

    // public List<Product> getLisProduct() {
    // return this.list;
    // }

    // public boolean add(Product pro) {
    // try {
    // list.add(pro);
    // return true;
    // } catch (Exception e) {
    // return false;
    // }
    // }

    // public boolean edit(Product pro) {
    // try {
    // for (int i = 0; i <= list.size(); i++) {
    // if (list.get(i).getId() == pro.getId()) {
    // list.set(i, pro);
    // return true;
    // }
    // }
    // list.add(pro);
    // return true;
    // } catch (Exception e) {
    // return false;
    // }
    // }

    // public boolean remove(int id) {
    // try {
    // for (int i = 0; i <= list.size(); i++) {
    // if (list.get(i).getId() == id) {
    // // xoá đi
    // list.remove(i);
    // return true;
    // }
    // }
    // return false;
    // } catch (Exception e) {
    // return false;
    // }
    // }

    // public Product findProductByName(String name) {
    // for (Product Product : list) {
    // if (Product.getName().equals(name)) {
    // return Product;
    // }
    // }
    // return null;
    // }

    // public Product findProductById(int id) {
    // for (Product Product : list) {
    // if (Product.getId() == id) {
    // return Product;
    // }
    // }
    // return null;
    // }

    // public List<String> getNameCategory() {
    // List<String> list = new ArrayList<String>();
    // CategoryService service = new CategoryService();
    // for (CategoryDTO item : service.getList()) {
    // list.add(item.getName());
    // }
    // return list;
    // }

    // public List<Integer> getListIdCategories() {
    // List<Integer> list = new ArrayList<Integer>();
    // CategoryService service = new CategoryService();
    // for (CategoryDTO item : service.getList()) {
    // list.add(item.getId());
    // }
    // return list;
    // }

    // Connection connection = DBProvider.getConnection();
    List<ProductDTO> getListCategory(int id);

    List<ProductDTO> searchByName(String name);

    // List<Products> listAll(String keyword);

    List<ProductDTO> sortByPrice(Float price);

    List<ProductDTO> sortByNameASC(String name);

    List<ProductDTO> sortByNameDESC(String name);

    List<ProductDTO> sortByPriceMinToMax(Float price);

}
