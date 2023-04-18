package io.formation.posts.dao;

import io.formation.posts.model.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostJdbcDAO implements PostDAO {

    private Post mapToPost(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");
        String createdAtStr = resultSet.getString("createdAt");
        String imgUrl = resultSet.getString("imgUrl");

        LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, Post.CUSTOM_FORMATTER);

        return new Post(id, title, author, content, createdAt, imgUrl);
    }

    @Override
    public void create(Post post) {

        Connection connection = ConnectionManager.getInstance();
        String query =
                "INSERT INTO Posts(title, author, content, createdAt, imgUrl) " +
                " VALUES(?,?,?,?,?)";

        try {
            PreparedStatement myPreparedStatement = connection.prepareStatement(query);
            myPreparedStatement.setString(1, post.getTitle());
            myPreparedStatement.setString(2, post.getAuthor());
            myPreparedStatement.setString(3, post.getContent());
            myPreparedStatement.setString(4, post.getCreatedAt().format(Post.CUSTOM_FORMATTER));
            myPreparedStatement.setString(5, post.getImgUrl());

            int row = myPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public List<Post> findAll() {
        List<Post> postList = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, title, author, content, createdAt, imgUrl FROM Posts";

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
    public List<Post> findByAuthor(String author) {
        return null;
    }

}