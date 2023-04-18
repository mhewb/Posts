package io.formation.posts.dao;
import java.util.List;

public interface GenericDAO<T, ID> {

    void create(T entity);

    List<T> findAll();

//    T findById(ID id);

//    void update(T entity);

//    void delete(T entity);

}