package com.src.dao;

import java.util.List;

public interface GenericDAO <T,K>{
    void insert(T t);
    void update(T t);
    void delete(K key);
    T findById(K key);
    List<T> findAll();
}
