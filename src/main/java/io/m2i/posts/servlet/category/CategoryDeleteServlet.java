package io.m2i.posts.servlet.category;

import io.m2i.posts.model.Category;
import io.m2i.posts.service.CategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = CategoryDeleteServlet.URL)
public class CategoryDeleteServlet extends HttpServlet {

    public static final String URL = "/delete-category";
    private static final String JSP = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        CategoryService categoryService = new CategoryService();
        categoryService.deleteCategoryById(id);

        req.getRequestDispatcher(CategoryListServlet.URL).forward(req, resp);

    }
}
