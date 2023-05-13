package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.UnitController;
import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;

@WebServlet(urlPatterns = "/application/UnitAndDutyList")
public class UnitAndDutyListServlet extends HttpServlet {

    //      ---------UNIT-AND-DUTY-LIST-----------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        req.getSession().setAttribute("UnitList", UnitController.getUnitController().findAll(doer));
        resp.sendRedirect("/application/UnitAndDuty.jsp");
    }
}
