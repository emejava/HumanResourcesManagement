package com.humanresourcesmanagement.controller.servlet;

import com.humanresourcesmanagement.controller.RetirementController;
import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/RetiredPersonnel")

public class RetiredPersonnelList extends HttpServlet {

    //      ---------RETIRED-PERSONNEL-LIST------------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        req.getSession().setAttribute("RetiredPersonnelList",
                RetirementController.getRetirementController().findAll(doer));
        resp.sendRedirect("/application/RetiredPersonnelList.jsp");
    }
}
