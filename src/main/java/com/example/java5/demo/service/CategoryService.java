package com.example.java5.demo.service;

import com.example.java5.demo.domain.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.java5.demo.dto.CategoryDTO;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import org.springframework.stereotype.Service;

public interface CategoryService {

    long count();

    <S extends Category> long count(Example<S> example);

    void delete(Category entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Category> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Category> entities);

    void deleteById(Integer id);

    void deleteInBatch(Iterable<Category> entities);

    boolean equals(Object obj);

    <S extends Category> boolean exists(Example<S> example);

    boolean existsById(Integer id);

    List<Category> findAll();

    <S extends Category> List<S> findAll(Example<S> example);

    <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Category> List<S> findAll(Example<S> example, Sort sort);

    Page<Category> findAll(Pageable pageable);

    List<Category> findAll(Sort sort);

    List<Category> findAllById(Iterable<Integer> ids);

    <S extends Category, R> R findBy(Example<S> example,
            Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    Optional<Category> findById(Integer id);

    <S extends Category> Optional<S> findOne(Example<S> example);

    void flush();

    Category getById(Integer id);

    Category getOne(Integer id);

    int hashCode();

    <S extends Category> S save(S entity);

    <S extends Category> List<S> saveAll(Iterable<S> entities);

    <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Category> S saveAndFlush(S entity);

    String toString();

    // List<CategoryDTO> list = new ArrayList<CategoryDTO>();

    // public List<CategoryDTO> getList() {
    // return this.list;
    // }

    // public void setList(List<CategoryDTO> list) {
    // this.list = list;
    // }

    // public CategoryService() {
    // list.add(new CategoryDTO(12, "balo", "aas"));
    // list.add(new CategoryDTO(13, "quan", "aas"));
    // list.add(new CategoryDTO(14, "ao", "aas"));
    // list.add(new CategoryDTO(15, "giay", "aas"));

    // }

    // public boolean add(CategoryDTO cat) {
    // try {
    // list.add(cat);
    // return true;
    // } catch (Exception e) {
    // return false;
    // }
    // }

    // public boolean edit(CategoryDTO cat) {
    // try {
    // for (int i = 0; i <= list.size(); i++) {
    // if (list.get(i).getId() == cat.getId()) {
    // list.set(i, cat);
    // return true;
    // }
    // }
    // list.add(cat);
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

    // public CategoryDTO findCategoryByName(String name) {
    // List<CategoryDTO> list = this.getList();
    // for (CategoryDTO Category : list) {
    // if (Category.getName().equals(name)) {
    // return Category;
    // }
    // }
    // return null;
    // }

    // public CategoryDTO findCategoryById(int id) {
    // for (CategoryDTO Category : list) {
    // if (Category.getId() == id) {
    // return Category;
    // }
    // }
    // return null;
    // }

}
