package io.m2i.posts.dao;

import io.m2i.posts.model.User;


public interface UserDAO extends GenericDAO<User, Integer>{

    User findByUsername(String username);

}
