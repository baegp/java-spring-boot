package com.example.java5.demo.servicelmp;

import com.example.java5.demo.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java5.demo.repository.CategoryRepository;
import com.example.java5.demo.service.CategoryService;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findAll(Sort sort) {
        return categoryRepository.findAll(sort);
    }

    public List<Category> findAllById(Iterable<Integer> ids) {
        return categoryRepository.findAllById(ids);
    }

    public <S extends Category> List<S> saveAll(Iterable<S> entities) {
        return categoryRepository.saveAll(entities);
    }

    public void flush() {
        categoryRepository.flush();
    }

    public <S extends Category> S saveAndFlush(S entity) {
        return categoryRepository.saveAndFlush(entity);
    }

    public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
        return categoryRepository.saveAllAndFlush(entities);
    }

    public void deleteInBatch(Iterable<Category> entities) {
        categoryRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Category> entities) {
        categoryRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        categoryRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        categoryRepository.deleteAllInBatch();
    }

    public Category getOne(Integer id) {
        return categoryRepository.getOne(id);
    }

    public Category getById(Integer id) {
        return categoryRepository.getById(id);
    }

    public <S extends Category> List<S> findAll(Example<S> example) {
        return categoryRepository.findAll(example);
    }

    public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
        return categoryRepository.findAll(example, sort);
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public <S extends Category> S save(S entity) {
        return categoryRepository.save(entity);
    }

    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    public boolean existsById(Integer id) {
        return categoryRepository.existsById(id);
    }

    public long count() {
        return categoryRepository.count();
    }

    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public void delete(Category entity) {
        categoryRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> ids) {
        categoryRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Category> entities) {
        categoryRepository.deleteAll(entities);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return categoryRepository.findOne(example);
    }

    public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
        return categoryRepository.findAll(example, pageable);
    }

    public <S extends Category> long count(Example<S> example) {
        return categoryRepository.count(example);
    }

    public <S extends Category> boolean exists(Example<S> example) {
        return categoryRepository.exists(example);
    }

    public <S extends Category, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return categoryRepository.findBy(example, queryFunction);
    }

    @Override
    public int hashCode() {
        return categoryRepository.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return categoryRepository.equals(obj);
    }

    @Override
    public String toString() {
        return categoryRepository.toString();
    }

}
