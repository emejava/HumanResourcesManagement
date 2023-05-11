//package com.humanresourcesmanagement.controller.filter;
//
//import com.humanresourcesmanagement.controller.session.SessionManager;
//import com.humanresourcesmanagement.model.entity.User;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//@WebFilter(urlPatterns = "/server/*")
//public class ServerFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpSession httpSession = ((HttpServletRequest) request).getSession();
//        SessionManager.validateHttpSession(httpSession);
//        User user = (User) request.getAttribute("user");
//        switch (user.getAccessLevel().getTitle()) {
//            case "admin":
//            case "employee":
//            case "manager":
//                chain.doFilter(request, response);
//                break;
//        }
//        ((HttpServletResponse)response).sendRedirect("/login.jsp");
//    }
//}
