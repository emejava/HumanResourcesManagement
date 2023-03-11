package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.RecruitmentSupplyDepController;
import com.humanresourcesmanagement.model.entity.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "RecruitmentSupplyDep",urlPatterns = "/RecruitmentSupplyDep.do")
public class RecruitmentSupplyDepServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long personId = Long.valueOf(request.getParameter("personId"));
        String education = request.getParameter("Education");
        String fieldOfStudy = request.getParameter("field Of Study");
        Integer ageCondition =Integer.valueOf(request.getParameter("Age Condition"));
        String university =request.getParameter("University");
        String workExperience =request.getParameter("Work Experience");
        String lastJob =request.getParameter("Last Job");
        String lastJobExitReason =request.getParameter("Last Job Exit Reason");
        String lastJobAddress =request.getParameter("Last Job Address");
        String lastJobNo =request.getParameter("Last Job No");
        String jobObjective =request.getParameter("Job Objective");
        String requestedSalary =request.getParameter("Requested Salary");
        Person person = new Person();
        person.setId(personId);
        RecruitmentSupplyDepController.getRecruitmentSupplyDepController().save(person,
                education ,fieldOfStudy,ageCondition,university,workExperience,lastJob,lastJobExitReason,
                lastJobAddress,lastJobNo,jobObjective,requestedSalary);
        response.sendRedirect("/RecruitmentSupplyDep.do");
        response.getWriter().write("اطلاعات شما با صلاحیت ثبت شد");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("RecruitmentSupplyList" ,RecruitmentSupplyDepController.getRecruitmentSupplyDepController().findAll());
        response.sendRedirect("RecruitmentSupply.jsp");
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("RecruitmentSupply_ID"));
        Long personId = Long.valueOf(request.getParameter("PersonId"));
        String education = request.getParameter("Education");
        String fieldOfStudy = request.getParameter("field Of Study");
        Integer ageCondition =Integer.valueOf(request.getParameter("Age Condition"));
        String university =request.getParameter("University");
        String workExperience =request.getParameter("Work Experience");
        String lastJob =request.getParameter("Last Job");
        String lastJobExitReason =request.getParameter("Last Job Exit Reason");
        String lastJobAddress =request.getParameter("Last Job Address");
        String lastJobNo =request.getParameter("Last Job No");
        String jobObjective =request.getParameter("Job Objective");
        String requestedSalary =request.getParameter("Requested Salary");
        Person person = new Person();
        person.setId(personId);
        RecruitmentSupplyDepController.getRecruitmentSupplyDepController().edit(id,person,
                education ,fieldOfStudy,ageCondition,university,workExperience,lastJob,lastJobExitReason,
                lastJobAddress,lastJobNo,jobObjective,requestedSalary);
        response.sendRedirect("/RecruitmentSupplyDep.do");
    }
}
