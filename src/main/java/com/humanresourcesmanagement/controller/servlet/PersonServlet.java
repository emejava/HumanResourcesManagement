package com.humanresourcesmanagement.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="login", urlPatterns = "/login.do")
public class PersonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("nationalCode"), req.getParameter("birthDay"), req.getParameter("fatherName"), req.getParameter("address"), req.getParameter("maritalStatus"), req.getParameter("phoneNo"), req.getParameter("landLineNo"), req.getParameter("children");
    }
}
