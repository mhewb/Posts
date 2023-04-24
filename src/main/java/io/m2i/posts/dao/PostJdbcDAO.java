package io.m2i.posts.dao;

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

        return new Post(id, title, author, content, imgUrl);
    }
    @Override
    public void create(Post post) {

        Connection connection = ConnectionManager.getInstance();
        String query =
                "INSERT INTO Posts(title, author, content, imgUrl) " +
                " VALUES(?,?,?,?)";

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);
            myPreparedStatement.setString(1, post.getTitle());
            myPreparedStatement.setString(2, post.getAuthor());
            myPreparedStatement.setString(3, post.getContent());
            myPreparedStatement.setString(4, post.getImgUrl());

            int row = myPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public List<Post> findAll() {
        List<Post> postList = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, title, author, content, imgUrl FROM Posts";

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
        String query = "SELECT id, title, author, content, imgURL FROM Posts WHERE id=?;";
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
                .append("imgUrl = ?")
                .append(" WHERE id=?").toString();

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);

            myPreparedStatement.setString(1, post.getTitle());
            myPreparedStatement.setString(2, post.getContent());
            myPreparedStatement.setString(3, post.getImgUrl());
            myPreparedStatement.setInt(4, post.getId());

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
            PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);
            prepStatement.setInt(1, post.getId());

            int row = prepStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to delete Post");
        }
    }

}