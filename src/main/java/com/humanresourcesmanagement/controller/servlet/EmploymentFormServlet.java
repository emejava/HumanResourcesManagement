package com.humanresourcesmanagement.controller.servlet;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.*;
import com.humanresourcesmanagement.controller.validation.*;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.util.Map;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet(urlPatterns = "/application/employmentForm.do")
public class EmploymentFormServlet extends HttpServlet {

    //      ---------FIND-PERSONS-------------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        //    doGET---------PERSON-LIST---------
        req.getSession().setAttribute("personList", PersonController.getPersonController().findAll(doer));
        req.getSession().setAttribute("unitList", PersonController.getPersonController().findAll(doer));
        req.getSession().setAttribute("positionList", PersonController.getPersonController().findAll(doer));

        resp.sendRedirect("/application/Employment.jsp");
    }

    //      ---------INSERT-DATA-------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");

        //    doPOST---------EMPLOYMENT-DATA------
        Long personId = Long.valueOf(req.getParameter("PersonId"));
        Person person = (Person) PersonController.getPersonController().findById(personId, doer).get(true);
        EmploymentType employmentType = EmploymentType.valueOf(req.getParameter("EmploymentType"));
        Unit unit = new Unit();
        Long unitId = Long.valueOf(req.getParameter("Unit"));
        unit.setId(unitId);
        Position position = (Position) PositionController.getPositionController().findById(
                Long.valueOf(req.getParameter("Position")), doer).get(true);
        Duty duty = (Duty) DutyController.getDutyController().findByPosition(position,doer).get(true);
        LocalDate startWorkingDate = LocalDate.parse(req.getParameter("StartWorkingDate"));
        ShiftWork shiftWork = ShiftWork.valueOf(req.getParameter("ShiftWork"));

        Long personnelCode = unitId + duty.getId() + personId;

        //  -----VALIDATE-AND-SAVE-DATA-WITH-CONTROLLER-----
        EmploymentController.getEmploymentController().save(
                personnelCode,
                person,
                employmentType,
                unit,
                duty,
                position,
                startWorkingDate,
                shiftWork,
                doer
        );
        //  doPOST------RESPONSE---------------------
        resp.sendRedirect("/application/Employment.jsp");
    }
}
