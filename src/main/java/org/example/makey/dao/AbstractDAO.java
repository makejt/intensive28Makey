package org.example.makey.dao;

import java.util.Set;
public interface AbstractDAO<T> {
    boolean add(T t);
    boolean update(T t);
    boolean delete(int id);
    T getById(int id);
    Set<T> getAll();
}