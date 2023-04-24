package io.m2i.posts.servlet;

import io.m2i.posts.dao.PostDAO;
import io.m2i.posts.dao.PostJdbcDAO;
import io.m2i.posts.model.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = EditPostServlet.URL)
public class EditPostServlet extends HttpServlet {

    public static final String URL = "/edit-post";
    public static final String JSP = "/WEB-INF/create-post.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String author = (String) session.getAttribute("username");


        int id = Integer.parseInt(req.getParameter("id"));
        PostDAO postDAO = new PostJdbcDAO();
        Post post = postDAO.getById(id);

        System.out.println(post);
        req.setAttribute("post", post);
        req.setAttribute("author", author);
        req.setAttribute("update", "update");

        req
                .getRequestDispatcher(EditPostServlet.JSP)
                .forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        System.out.println(id);

        PostDAO postDAO = new PostJdbcDAO();
        Post post = postDAO.getById(id);

        post.setTitle(req.getParameter("postTitle"));
        post.setContent(req.getParameter("postContent"));
        post.setImgUrl(req.getParameter("imgUrl"));

        postDAO.update(post);

        resp.sendRedirect(EditPostServlet.URL+ "/?id=" + id);


    }




}
