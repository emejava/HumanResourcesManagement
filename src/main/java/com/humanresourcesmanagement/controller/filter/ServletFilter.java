package com.humanresourcesmanagement.controller.filter;

import com.humanresourcesmanagement.controller.session.SessionManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/application/*")
public class ServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        if (SessionManager.validateHttpSession(httpSession)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/login.jsp");
        }
    }
}
