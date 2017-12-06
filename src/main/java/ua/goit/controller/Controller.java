package ua.goit.controller;

import java.util.List;
import java.util.Optional;

public interface Controller<T, K> {


    Optional<T> read(K key);

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> getAll();
}
