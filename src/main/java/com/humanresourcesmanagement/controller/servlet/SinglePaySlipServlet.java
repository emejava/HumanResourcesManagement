package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.PaymentController;
import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/application/SinglePaySlip")
public class SinglePaySlipServlet extends HttpServlet {

    //      ---------SINGLE-PAYSLIP-LIST------------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        Long id = (Long) req.getSession().getAttribute("paySlipId");
        req.getSession().setAttribute("SinglePaySlip",
                PaymentController.getPaymentController().findById(id,doer).get(true));
        resp.sendRedirect("/application/SinglePayment.jsp");
    }
}
