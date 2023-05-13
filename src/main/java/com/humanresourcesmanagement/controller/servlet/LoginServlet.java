package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.UserController;
import com.humanresourcesmanagement.controller.session.SessionManager;
import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    //   -------VALIDATE-ACCOUNT---------------------doPost
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");

        User user = (User) UserController.getUserController().isValidate(username,password).get(true);
        if( user != null){
            SessionManager.addHttpSession(req.getSession());
            req.getSession().setAttribute("user", user);
            if(rememberMe != null){
            Cookie uCookie = new Cookie("username",username);
            Cookie pCookie = new Cookie("password",password);
            resp.addCookie(uCookie);
            resp.addCookie(pCookie);
            }
            req.getRequestDispatcher("/application/Dashboard.jsp").forward(req,resp);
        }else{
            resp.sendRedirect("/login.jsp");
            resp.getWriter().write("Invalid Username and Password");
        }

    }
}
