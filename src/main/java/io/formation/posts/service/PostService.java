package io.formation.posts.service;
import io.formation.posts.dao.PostDAO;
import io.formation.posts.dao.PostJdbcDAO;
import io.formation.posts.model.Post;

import java.time.LocalDateTime;
import java.util.List;

// CRUD
public class PostService {

    private final PostDAO postDAO = new PostJdbcDAO();
    public List<Post> fetchAllPosts() {
        return postDAO.findAll();
    }
    public void createPost(String title, String author, String content, String imgUrl) {

        LocalDateTime createdAt = LocalDateTime.now();
        Post p = new Post(title, author, content, createdAt);
        p.setImgUrl(imgUrl);
        postDAO.create(p);

    }



}
