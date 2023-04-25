package io.m2i.posts.servlet.post;

import io.m2i.posts.model.Post;
import io.m2i.posts.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = PostEditServlet.URL)
public class PostEditServlet extends HttpServlet {

    public static final String URL = "/edit-post";
    public static final String JSP = "/WEB-INF/post/post-form.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String author = (String) session.getAttribute("username");

        int id = Integer.parseInt(req.getParameter("id"));

        PostService postService = new PostService();
        Post post = postService.getPostById(id);

        req.setAttribute("post", post);
        req.setAttribute("author", author);
        req.setAttribute("update", "update");

        req
                .getRequestDispatcher(PostEditServlet.JSP)
                .forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        PostService postService = new PostService();
        Post post = postService.getPostById(id);

        post.setTitle(req.getParameter("postTitle"));
        post.setContent(req.getParameter("postContent"));
        post.setImgUrl(req.getParameter("imgUrl"));

        postService.updatePost(post);

        resp.sendRedirect(PostEditServlet.URL+ "/?id=" + id);


    }




}
