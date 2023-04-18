package io.formation.posts.dao;

import io.formation.posts.model.User;


public interface UserDAO extends GenericDAO<User, Integer>{

    User findByUsername(String username);

}
