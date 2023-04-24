package io.m2i.posts.servlet;

import io.m2i.posts.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = LoginServlet.URL)
public class LoginServlet extends HttpServlet {

    public static final String URL = "/login";
    public static final String JSP = "/WEB-INF/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("username") != null) {
            req.setAttribute("alreadyLogged", "You are already logged !");
        } else {
            req.setAttribute("username", session.getAttribute("username"));
        }

        req
                .getRequestDispatcher(LoginServlet.JSP)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = new UserService();
        boolean isLogged = userService.checkLog(username, password);
        System.out.println(isLogged);

        if (isLogged == false) {
            req.setAttribute("loggingError", "Bad credentials.");
            req.getRequestDispatcher(LoginServlet.JSP).forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/");
        }


    }
}
