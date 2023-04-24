package io.m2i.posts.service;
import io.m2i.posts.dao.PostDAO;
import io.m2i.posts.dao.PostJdbcDAO;
import io.m2i.posts.model.Post;

import java.time.LocalDateTime;
import java.util.List;

// CRUD
public class PostService {

    private final PostDAO postDAO = new PostJdbcDAO();

    public List<Post> fetchAllPosts() {
        return postDAO.findAll();
    }

    public Post getPostById(int id) {
        return postDAO.getById(id);
    }

    public void createPost(String title, String author, String content, String imgUrl) {
        Post p = new Post(title, author, content);
        p.setImgUrl(imgUrl);
        postDAO.create(p);
    }

    public void createPost(Post post) {
        postDAO.create(post);
    }

    public void deletePostById(int id) { // TODO: necessary?
        Post post = postDAO.getById(id);
        postDAO.delete(post);
    }
}
