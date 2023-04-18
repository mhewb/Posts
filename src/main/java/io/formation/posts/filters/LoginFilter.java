package io.formation.posts.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        // Public route accessible w/o connexion
        if (
                request.getRequestURI().equals(request.getContextPath() + "/")
                        || request.getRequestURI().equals(request.getContextPath() + "/login")
                        || request.getRequestURI().equals(request.getContextPath() + "/register")
        ) {
            chain.doFilter(req, resp); // Les pages restent accessibles
        } else {
            if (session.getAttribute("username") == null) {
                // If no username: go to login page
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                // Continue la chaine des filtres/navigations
                chain.doFilter(req, resp);
            }
        }

    }
}
