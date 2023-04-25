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

@WebServlet(urlPatterns = PostDetailsServlet.URL)
public class PostDetailsServlet extends HttpServlet {

    protected static final String URL = "/post-details";
    private static final String JSP = "/WEB-INF/post/post-details.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("username") != null) {
            req.setAttribute("isLogged", true);
        }

        int id = Integer.parseInt(req.getParameter("id"));

        PostService postService = new PostService();
        Post post = postService.getPostById(id);

        req.setAttribute("post", post);

        req.getRequestDispatcher(JSP).forward(req, resp);

    }
}
