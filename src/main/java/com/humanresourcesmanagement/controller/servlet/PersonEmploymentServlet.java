package com.humanresourcesmanagement.controller.servlet;

import com.humanresourcesmanagement.controller.PersonEmploymentController;
import com.humanresourcesmanagement.model.entity.Duty;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.entity.Unit;
import com.humanresourcesmanagement.model.entity.enums.EmploymentType;
import com.humanresourcesmanagement.model.entity.enums.Position;
import com.humanresourcesmanagement.model.entity.enums.ShiftWork;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "Employment", urlPatterns = "/employment.do")
public class PersonEmploymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long personId = Long.valueOf(req.getParameter("personId"));
        EmploymentType employmentType = EmploymentType.valueOf(req.getParameter("EmploymentType"));
        Long unitId = Long.valueOf(req.getParameter("Unit"));
        Position position = Position.valueOf(req.getParameter("Position"));
        Timestamp startWorkingDate = Timestamp.valueOf(req.getParameter("StartWorkingDate"));
        String workingPerDay = req.getParameter("WorkingTimePerDay");
        ShiftWork shiftWork = ShiftWork.valueOf(req.getParameter("ShiftWork"));
        Person person = new Person();
        person.setId(personId);
        Unit unit = new Unit();
        unit.setId(unitId);
        Duty duty = UnitController.findDutyByUnitId(unitId);
        PersonEmploymentController.getPersonEmploymentController().save(person,employmentType,unit,
                duty,position,startWorkingDate,workingPerDay,shiftWork);

        resp.sendRedirect("/employment.do");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("personEmploymentList",PersonEmploymentController.getPersonEmploymentController().findAll());
        resp.sendRedirect("employment.jsp");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long personnelCode = Long.valueOf(req.getParameter("personnelCode"));
        Long personId = Long.valueOf(req.getParameter("personId"));
        EmploymentType employmentType = EmploymentType.valueOf(req.getParameter("EmploymentType"));
        Long unitId = Long.valueOf(req.getParameter("Unit"));
        Long dutyId = Long.valueOf(req.getParameter("Duty"));
        Position position = Position.valueOf(req.getParameter("Position"));
        Timestamp startWorkingDate = Timestamp.valueOf(req.getParameter("StartWorkingDate"));
        String workingPerDay = req.getParameter("WorkingTimePerDay");
        ShiftWork shiftWork = ShiftWork.valueOf(req.getParameter("ShiftWork"));
        Person person = new Person();
        person.setId(personId);
        Unit unit = new Unit();
        unit.setId(unitId);
        Duty duty = UnitController.findDutyByUnitId(unitId);
        PersonEmploymentController.getPersonEmploymentController().edit(personnelCode,person,employmentType,unit,
                duty,position,startWorkingDate,workingPerDay,shiftWork);

        resp.sendRedirect("/employment.do");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long personnelCode = Long.valueOf(req.getParameter("personnelCode"));
        PersonEmploymentController.getPersonEmploymentController().deactivate(personnelCode);

        resp.sendRedirect("/employment.do");
    }
}
