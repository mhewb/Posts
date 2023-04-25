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
import java.util.List;

@WebServlet(urlPatterns = PostListServlet.URL)
public class PostListServlet extends HttpServlet {

    private static final String JSP = "/WEB-INF/post/post-list.jsp";
    public static final String URL = "/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("username") == null) {

            req.setAttribute("isLog", false);

        } else {

            PostService postService = new PostService();
            List<Post> postList = postService.fetchAllPosts();
            req.setAttribute("posts", postList);

        }


        req.getRequestDispatcher(JSP).forward(req, resp);
    }
}
