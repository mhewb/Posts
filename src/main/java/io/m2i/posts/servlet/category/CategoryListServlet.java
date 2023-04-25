package io.m2i.posts.servlet.category;

import io.m2i.posts.model.Category;
import io.m2i.posts.model.Post;
import io.m2i.posts.service.CategoryService;
import io.m2i.posts.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = CategoryListServlet.URL)
public class CategoryListServlet extends HttpServlet {

    private static final String JSP = "/WEB-INF/category/category-list.jsp";
    public static final String URL = "/category-list";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("username") == null) {
            req.setAttribute("isLog", false);
        } else {
            CategoryService categoryService = new CategoryService();
            List<Category> categoryList = categoryService.fetchAllCategories();
            req.setAttribute("categories", categoryList);
        }

        req
                .getRequestDispatcher(JSP)
                .forward(req, resp);
    }
}