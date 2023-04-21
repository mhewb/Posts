package io.formation.posts.dao;

import io.formation.posts.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private User mapToUser(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        boolean isAdmin = resultSet.getBoolean("isAdmin");

        return new User(id, username, email, password);
    }

    @Override
    public void create(User user) {

        Connection connection = ConnectionManager.getInstance();
        String query = "INSERT INTO Users(username, email, password, isAdmin) VALUES(?,?,?,?)";

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);
            myPreparedStatement.setString(1, user.getUsername());
            myPreparedStatement.setString(2, user.getEmail());
            myPreparedStatement.setString(3, user.getPassword());
            myPreparedStatement.setBoolean(4, user.isAdmin());
            int row = myPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> findAll() {

        List<User> userList = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, username, email, password FROM Users";


        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(query);

            while (myResultSet.next()) {
                User user = mapToUser(myResultSet);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getById(Integer integer) {
        return null;
    }

    @Override
    public void update(User entity) {

    }


    @Override
    public User findByUsername(String username) {

        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, username, email, password FROM users WHERE username=?;";
        User userFound = null;

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);
            myPreparedStatement.setString(1, username);
            ResultSet result = myPreparedStatement.executeQuery();
            if (result.next()) {
                userFound = new User(
                        result.getInt("id"),
                        result.getString("email"),
                        result.getString("username"),
                        result.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return userFound;

    }
}