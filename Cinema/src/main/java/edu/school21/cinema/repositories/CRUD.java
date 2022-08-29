package edu.school21.cinema.repositories;

import java.util.List;

public interface CRUD<T> {
    void update(T obj);
    T findById(Long id);
    void save(T obj);
    void delete(T obj);
    List<T> findAll();
    List<T> findByName(String name);
}
