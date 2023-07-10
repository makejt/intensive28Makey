package org.example.makey.dao;

import java.util.List;
public interface AbstractDAO<T> {
    void add(T t);
    void update(T t);
    void delete(int id);
    T getById(int id);
    List<T> getAll();
}