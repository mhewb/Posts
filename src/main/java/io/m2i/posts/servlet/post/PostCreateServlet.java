package io.m2i.posts.servlet.post;

import io.m2i.posts.model.Category;
import io.m2i.posts.service.CategoryService;
import io.m2i.posts.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = PostCreateServlet.URL)
public class PostCreateServlet extends HttpServlet {

    public static final String URL = "/create-post";
    public static final String JSP = "/WEB-INF/post/post-form.jsp";
    PostService postService = new PostService();
    CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String author = (String) session.getAttribute("username");

        req.setAttribute("author", author);

        req.setAttribute("categories", categoryService.fetchAllCategories());

        req
                .getRequestDispatcher(PostCreateServlet.JSP)
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
            postService.createPost(title, author, content, imgUrl, req.getParameter("category"));
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("createPostError", "Error while creating post");
        }

        req
                .getRequestDispatcher(PostCreateServlet.JSP)
                .forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/");


        }




}
