package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.*;
import com.humanresourcesmanagement.model.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/application/leaveDaysSubmit")
public class LeaveDaysSubmitServlet extends HttpServlet {

    //      ---------INSERT-DATA-------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        Person person = (Person) PersonController.getPersonController().findByPersonnelCode(
                Long.valueOf(req.getParameter("PersonnelCode")),doer).get(true);
        LocalDate till = LocalDate.parse(req.getParameter("Till"));
        LocalDate from = LocalDate.parse(req.getParameter("From"));
        Byte daysCount = Byte.valueOf(req.getParameter("DaysCount"));
        String request = req.getParameter("Request");

        //  -----VALIDATE-AND-SAVE-DATA-WITH-CONTROLLER-----
        LeaveDaysController.getLeaveDaysController().save(
                person,
                from,
                till,
                daysCount,
                request,
                doer);

        //  doPOST------RESPONSE---------------------
        resp.sendRedirect("/application/LeaveDays.jsp");

    }
}
