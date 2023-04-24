package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.NewsController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.humanresourcesmanagement.model.entity.User;

import java.io.IOException;

@WebServlet(urlPatterns = "/main.do")
public class MainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User visitor = (User)req.getSession().getAttribute("user");
        req.getSession().setAttribute("allNews", NewsController.getNewsController().findAllActive(visitor));
        resp.sendRedirect("/main.jsp");
    }
}
