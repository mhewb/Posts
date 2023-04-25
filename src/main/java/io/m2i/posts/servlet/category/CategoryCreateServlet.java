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

@WebServlet(urlPatterns = CategoryCreateServlet.URL)
public class CategoryCreateServlet extends HttpServlet {

    public static final String URL = "/create-category";
    public static final String JSP = "/WEB-INF/category/category-form.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String author = (String) session.getAttribute("username");

        CategoryService categoryService = new CategoryService();
        req.setAttribute("categories", categoryService.fetchAllCategories());

        req
                .getRequestDispatcher(CategoryCreateServlet.JSP)
                .forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String name = req.getParameter("categoryName");

        if ( name.isBlank() ) {
            req.setAttribute("createCategoryError", "Empty name is not allowed");
        }

        try {
            CategoryService categoryService = new CategoryService();
            categoryService.createCategory(name);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("createCategoryError", "Error while creating post");
        }

        req
                .getRequestDispatcher(CategoryCreateServlet.JSP)
                .forward(req, resp);

        resp.sendRedirect(req.getContextPath() + "/");


    }




}
