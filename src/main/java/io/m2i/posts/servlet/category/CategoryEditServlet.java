package io.m2i.posts.servlet.category;

import io.m2i.posts.model.Category;
import io.m2i.posts.service.CategoryService;
import io.m2i.posts.servlet.post.PostEditServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = CategoryEditServlet.URL)
public class CategoryEditServlet extends HttpServlet {

    public static final String URL = "/edit-category";
    private static final String JSP = "WEB-INF/category/category-form.jsp";

    CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        Category category = categoryService.getCategoryById(id);

        req.setAttribute("category", category);
        req.setAttribute("update", "update");

        req.getRequestDispatcher(CategoryEditServlet.JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("nameCategory");

        categoryService.updateCategoryById(id, name);

        resp.sendRedirect(CategoryEditServlet.URL+ "/?id=" + id);


    }
}
