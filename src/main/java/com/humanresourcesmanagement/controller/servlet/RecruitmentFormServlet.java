package com.humanresourcesmanagement.controller.servlet;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.*;
import com.humanresourcesmanagement.controller.validation.*;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/application/recruitment.do")
public class RecruitmentFormServlet extends HttpServlet {

    //      ---------FIND-PERSONS-------------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        //    doPOST---------PERSON-LIST---------
        req.getSession().setAttribute("personList", PersonController.getPersonController().findAll(doer));
        resp.sendRedirect("/application/Recruitment.jsp");
    }

    //      ---------INSERT-DATA-------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");

        //    doPOST---------RECRUITMENT-DATA---------
        Person person = new Person();
        person.setId(Long.valueOf(req.getParameter("PersonId")));
        String education = req.getParameter("Education");
        String fieldOfStudy = req.getParameter("FieldOfStudy");
        String educationPlace = req.getParameter("EducationPlace");
        String workExperience = req.getParameter("WorkExperience");
        String lastJob = req.getParameter("LastJob");
        String lastJobExitReason = req.getParameter("LastJobExitReason");
        String lastJobAdders = req.getParameter("LastJobAdders");
        String lastJobNo = req.getParameter("LastJobNo");
        String jobGoal = req.getParameter("JobGoal");
        ShiftWork shiftWork = ShiftWork.valueOf(req.getParameter("ShiftWork"));
        String requestedSalary = req.getParameter("RequestedSalary");

        //  -----VALIDATE-AND-SAVE-DATA-WITH-CONTROLLER-----
        RecruitmentController.getRecruitmentController().save(
                person,
                education,
                fieldOfStudy,
                educationPlace,
                workExperience,
                lastJob,
                lastJobExitReason,
                lastJobAdders,
                lastJobNo,
                jobGoal,
                shiftWork,
                requestedSalary,
                doer
        );

        //  doPOST------RESPONSE---------------------
        resp.sendRedirect("/application/Recruitment.jsp");
    }
}

