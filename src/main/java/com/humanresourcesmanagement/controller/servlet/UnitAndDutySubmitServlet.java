package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.*;
import com.humanresourcesmanagement.model.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/application/UnitAndDutySubmit.do")
public class UnitAndDutySubmitServlet extends HttpServlet {

    //      ---------DUTY-AND-POSITION------------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        req.getSession().setAttribute("DutyList", DutyController.getDutyController().findAll(doer));
        req.getSession().setAttribute("PositionList", PositionController.getPositionController().findAll(doer));
        resp.sendRedirect("/application/UnitAndDuty.jsp");
    }

    //      ---------INSERT-DATA--------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");

        //    doPOST---------UNIT-DATA-----------
        String name = req.getParameter("Name");
        String city = req.getParameter("City");
        String[] dutiesValues = req.getParameterValues("Duties");
        List<Duty> duties = new ArrayList<>();
        for (String value : dutiesValues) {
            Duty duty = (Duty) DutyController.getDutyController().findById(Long.valueOf(value),doer).get(true);
            duties.add(duty);
        }

        //    doPOST---------DUTY-DATA-----------
        String dutyExplanation = req.getParameter("DutyExplanation");
        Position position = (Position) PositionController.getPositionController().findById(
                Long.valueOf(req.getParameter("Position")),doer).get(true);

        //  -----VALIDATE-AND-SAVE-DATA-WITH-CONTROLLER-----
        DutyController.getDutyController().save(
                position,
                dutyExplanation,
                doer
        );
        UnitController.getUnitController().save(
                name,
                city,
                duties,
                doer
        );

        //  doPOST------RESPONSE---------------------
        resp.sendRedirect("/application/UnitAndDuty.jsp");
    }
}
