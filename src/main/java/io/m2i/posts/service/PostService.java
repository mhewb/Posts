package io.m2i.posts.service;
import io.m2i.posts.api.dto.PostDTO;
import io.m2i.posts.dao.CategoryDAO;
import io.m2i.posts.dao.CategoryJdbcDAO;
import io.m2i.posts.dao.PostDAO;
import io.m2i.posts.dao.PostJdbcDAO;
import io.m2i.posts.model.Category;
import io.m2i.posts.model.Post;

import java.time.LocalDateTime;
import java.util.List;

// CRUD
public class PostService {

    private final PostDAO postDAO = new PostJdbcDAO();
    CategoryService categoryService = new CategoryService();

    public List<Post> fetchAllPosts() {
        return postDAO.findAll();
    }

    public Post getPostById(int id) {
        return postDAO.getById(id);
    }
    
    public Post createPost(String title, String author, String content, String imgUrl, String categoryName) {

        Category category = categoryService.getCategoryByName(categoryName);
        Post post = new Post(title, author, content, imgUrl, category);
        postDAO.create(post);

        return post;
    }

    public boolean updatePost(Post post) {
        return postDAO.update(post);
    }

    public boolean updatePost(PostDTO dto) {

        Category category = categoryService.getCategoryByName(dto.getCategory().getName());
        dto.setCategory(category);

        Post post = new Post(
                dto.getId(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getContent(),
                dto.getImgUrl(),
                dto.getCategory()

        );

        return postDAO.update(post);
    }

    public void deletePostById(int id) {
        Post post = postDAO.getById(id);
        postDAO.delete(post);
    }

}
