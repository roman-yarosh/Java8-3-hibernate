package ua.goit.model.dao;

import java.io.Serializable;
import java.util.Optional;

public interface GenericDao<K extends Serializable, T> {

    Optional<T> read(K key);

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}
