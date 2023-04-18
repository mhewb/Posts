package io.formation.posts.servlet;

import io.formation.posts.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = CreatePostServlet.URL)
public class CreatePostServlet extends HttpServlet {

    public static final String URL = "/create-post";
    public static final String JSP = "/WEB-INF/create-post.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String author = (String) session.getAttribute("username");

        req.setAttribute("author", author);

        req
                .getRequestDispatcher(CreatePostServlet.JSP)
                .forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String author = (String) session.getAttribute("username");

        String title = req.getParameter("postTitle");
        String content = req.getParameter("postContent");
        String imgUrl = req.getParameter("imgUrl");

        if ( title.isBlank() || content.isBlank() ) {
            req.setAttribute("createPostError", "Empty title or content is not allowed");
        }


        try {
            PostService postService = new PostService();
            postService.createPost(title, author, content, imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("createPostError", "Error while creating post");
        }

        req
                .getRequestDispatcher(CreatePostServlet.JSP)
                .forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/");


        }




}
