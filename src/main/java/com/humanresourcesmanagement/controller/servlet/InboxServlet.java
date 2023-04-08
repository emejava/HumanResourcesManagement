package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.MessageController;
import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/inbox.do")
public class InboxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        req.getSession().setAttribute("messageList", MessageController.getMessageController().findAllByReceiver(user,user));
        resp.sendRedirect("/inbox.jsp");
    }
}
