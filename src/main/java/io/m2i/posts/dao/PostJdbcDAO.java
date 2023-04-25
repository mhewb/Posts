package io.m2i.posts.dao;

import io.m2i.posts.model.Category;
import io.m2i.posts.model.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostJdbcDAO implements PostDAO {

    private Post mapToPost(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");
        String imgUrl = resultSet.getString("imgUrl");

        // TODO: Should I call the Service instead of the DAO ?
        int idCategory = resultSet.getInt("id_categories");
        CategoryDAO categoryDAO = new CategoryJdbcDAO();
        Category category = categoryDAO.getById(idCategory);

        return new Post(id, title, author, content, imgUrl, category);
    }
    @Override
    public void create(Post post) {

        Connection connection = ConnectionManager.getInstance();
        String query =
                "INSERT INTO Posts(title, author, content, imgUrl, id_Categories) " +
                " VALUES(?,?,?,?,?)";

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);
            myPreparedStatement.setString(1, post.getTitle());
            myPreparedStatement.setString(2, post.getAuthor());
            myPreparedStatement.setString(3, post.getContent());
            myPreparedStatement.setString(4, post.getImgUrl());
            myPreparedStatement.setInt(5, post.getCategory().getId());

            int row = myPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Post> findAll() {
        List<Post> postList = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, title, author, content, imgUrl, id_categories FROM Posts";

        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(query);

            while (myResultSet.next()) {
                Post p = mapToPost(myResultSet);
                postList.add(p);
            }

//            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    @Override
    public Post getById(Integer id) {

        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, title, author, content, imgURL, id_Categories FROM Posts WHERE id=?;";
        Post postFound = null;

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);
            myPreparedStatement.setInt(1, id);
            ResultSet result = myPreparedStatement.executeQuery();
            if (result.next()) {
                postFound = mapToPost(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return postFound;

    }

    public void update(Post post) {

        Connection connection = ConnectionManager.getInstance();
        String query = new StringBuilder()
                .append("UPDATE Posts ")
                .append("SET title = ?, ")
                .append("content = ?, ")
                .append("imgUrl = ?, ")
                .append("id_Categories = ?")
                .append(" WHERE id=?").toString();

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);

            myPreparedStatement.setString(1, post.getTitle());
            myPreparedStatement.setString(2, post.getContent());
            myPreparedStatement.setString(3, post.getImgUrl());
            myPreparedStatement.setInt(4, post.getCategory().getId());
            myPreparedStatement.setInt(5, post.getId());

            int row = myPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Post post) {

        Connection connection = ConnectionManager.getInstance();
        String sqlQuery = "DELETE FROM Posts WHERE id=?";

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(sqlQuery);
            myPreparedStatement.setInt(1, post.getId());

            int row = myPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to delete Post");
        }

    }

}