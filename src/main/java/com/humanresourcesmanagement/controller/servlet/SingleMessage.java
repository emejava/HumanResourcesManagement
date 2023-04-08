package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.MessageController;
import com.humanresourcesmanagement.model.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/singleMessage.do")
public class SingleMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user  = (User)req.getSession().getAttribute("user");
        Long id = Long.parseLong(req.getParameter("messageId"));
        req.getSession().setAttribute("singleMessage",MessageController.getMessageController().findById(id,user));
        resp.sendRedirect("/singleMessage.jsp");
    }
}
