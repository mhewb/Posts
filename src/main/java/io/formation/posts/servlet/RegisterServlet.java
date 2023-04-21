package io.formation.posts.servlet;

import io.formation.posts.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            req.setAttribute(
                    "registerError",
                    "You cannot have an empty username, email or password");
        } else {

            try {

                UserService userService = new UserService();
                userService.createUser(username, email, password);

                req.setAttribute("registerUser", true);
                req.setAttribute("username", username);
                req.setAttribute("password", password);

            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute(
                        "registerError",
                        "Something happened while creating new user, please check logs.");
            }
        }

        req
                .getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(req, resp);
        }

    }