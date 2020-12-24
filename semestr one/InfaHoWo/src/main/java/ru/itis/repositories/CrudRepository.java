package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    List<T> findAll();
    T findById(Integer id);
    Optional<T> findById(Long id);

    void save(T entity);
    void update(T entity);
    void remove(T entity);
    void removeById(T entity);
}