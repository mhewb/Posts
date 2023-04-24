package io.m2i.posts.service;

import io.m2i.posts.dao.UserDAO;
import io.m2i.posts.dao.UserJdbcDAO;
import io.m2i.posts.model.User;

public class UserService {
    private final UserDAO userDAO = new UserJdbcDAO();
    public boolean checkLog(String username, String password) {
        User userFound = userDAO.findByUsername(username);
        if (userFound != null) {
            return userFound.getPassword().equals(password);
        }
        return false;
    }

    public void createUser(String username, String email, String password) {

        User u = new User(username, email, password);
        userDAO.create(u);


    }

}

