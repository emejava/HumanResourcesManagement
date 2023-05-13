package com.humanresourcesmanagement.controller.servlet;

import com.humanresourcesmanagement.controller.NewsController;
import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = "/application/dashboard")
public class DashboardServlet extends HttpServlet {
    //      ---------GET-DATA-------------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");

        //    doPOST---------NEWS-------------
        req.getSession().setAttribute("newsList",NewsController.getNewsController().findAllActive(doer));

        resp.sendRedirect("/application/Dashboard.jsp");
    }
}
