package io.m2i.posts.dao;

import io.m2i.posts.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryJdbcDAO implements CategoryDAO {

    private Category mapToCategory(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new Category(id, name);
    }

    @Override
    public void create(Category category) {

        Connection connection = ConnectionManager.getInstance();
        String query =
                "INSERT INTO Categories(name) VALUES(?)";

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);
            myPreparedStatement.setString(1, category.getName());

            int row = myPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, name FROM Categories";

        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(query);

            while (myResultSet.next()) {
                Category category = mapToCategory(myResultSet);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category getById(Integer id) {

        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, name FROM Categories WHERE id=?;";
        Category categoryFound = null;

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);
            myPreparedStatement.setInt(1, id);
            ResultSet result = myPreparedStatement.executeQuery();
            if (result.next()) {
                categoryFound = mapToCategory(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return categoryFound;

    }

    public boolean update(Category category) {

        Connection connection = ConnectionManager.getInstance();
        String query = new StringBuilder()
                .append("UPDATE Categories SET name = ? WHERE id=?").toString();

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);

            myPreparedStatement.setString(1, category.getName());
            myPreparedStatement.setInt(2, category.getId());

            int row = myPreparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public void delete(Category category) {

        Connection connection = ConnectionManager.getInstance();
        String sqlQuery = "DELETE FROM Categories WHERE id=?";

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(sqlQuery);
            myPreparedStatement.setInt(1, category.getId());

            int row = myPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to delete Category");
        }

    }

    @Override
    public Category getByName(String name) {

        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, name FROM Categories WHERE name=?;";
        Category categoryFound = null;

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);
            myPreparedStatement.setString(1, name);
            ResultSet result = myPreparedStatement.executeQuery();
            if (result.next()) {
                categoryFound = mapToCategory(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return categoryFound;
    }

}
