package com.humanresourcesmanagement.controller.servlet;

import com.humanresourcesmanagement.controller.*;
import com.humanresourcesmanagement.model.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "retireEmployee.do")
public class RetireEmployeeForm extends HttpServlet {

    //      ---------FIND-PERSONS-------------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        //    doGET---------PERSON-LIST---------
        req.getSession().setAttribute("personList", PersonController.getPersonController().findAll(doer));

        resp.sendRedirect("/Retirement.jsp");
    }

    //      ---------INSERT-DATA-------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");

        //    doPOST---------RETIREMENT-DATA------
        Person person = PersonController.getPersonController().findById(Long.valueOf(req.getParameter("PersonId")), doer);
        Long personnelCode = person.getEmployment().getPersonnelCode();         //TODO: Front return the person id to servlet but show id, first and last name
        LocalDate date = LocalDate.parse(req.getParameter("Date"));
        Payment lastPayment = PaymentController.getPaymentController().findByPersonnelCodeAndEndTime(
                personnelCode, LocalDate.parse(req.getParameter("LastPayment")), doer);

        //      doPOST---------SAVE-FILES---------
        //            -----------------File1
        Part part = req.getPart("Attachment");
        String attachmentName = part.getSubmittedFileName();
        String path = "C:\\root\\ " + attachmentName;
        for (Part reqPart : req.getParts()) {
            part.write(path);
        }
        Attachment attachment = new Attachment(attachmentName, doer, path);

        //  -----VALIDATE-AND-SAVE-DATA-WITH-CONTROLLER-----
        RetirementController.getRetirementController().save(
                person,
                date,
                lastPayment,
                attachment,
                doer
        );
        //  doPOST------RESPONSE---------------------
        resp.sendRedirect("Retirement.jsp");
    }

}
