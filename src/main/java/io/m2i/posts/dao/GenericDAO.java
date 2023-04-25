package io.m2i.posts.dao;
import java.util.List;

public interface GenericDAO<T, ID> {

    void create(T entity);

    List<T> findAll();

    T getById(ID id);
    boolean update(T entity);

    void delete(T entity);

}