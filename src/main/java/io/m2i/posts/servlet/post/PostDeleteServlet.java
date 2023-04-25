package io.m2i.posts.servlet.post;

import io.m2i.posts.model.Post;
import io.m2i.posts.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = PostDeleteServlet.URL)
public class PostDeleteServlet extends HttpServlet {

    public static final String URL = "/delete-post";
    public static final String JSP = "/WEB-INF/post/post-list.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        PostService postService = new PostService();
        Post post = postService.getPostById(id);
        postService.deletePost(post);

        req.getRequestDispatcher(PostListServlet.URL).forward(req, resp);


    }

}
