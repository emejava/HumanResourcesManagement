package com.humanresourcesmanagement.controller.servlet;

import com.humanresourcesmanagement.controller.ResignationController;
import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;

@WebServlet(urlPatterns = "/application/ResignConfirmation")
public class ResignConfirmationServlet extends HttpServlet {

    //      ---------RESIGN-CONFIRMATION-LIST------------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        req.getSession().setAttribute("ResignConfirmation", ResignationController.getResignationController().findAll(doer));
        resp.sendRedirect("/application/ResignConfirmationList.jsp");
    }

}
