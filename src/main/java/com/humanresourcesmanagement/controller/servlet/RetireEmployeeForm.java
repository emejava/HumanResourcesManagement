package com.humanresourcesmanagement.controller.servlet;

import com.humanresourcesmanagement.controller.*;
import com.humanresourcesmanagement.model.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/application/retireEmployee.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
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
        Person person = (Person) PersonController.getPersonController().findById(
                Long.valueOf(req.getParameter("PersonId")), doer).get(true);
        Long personnelCode = person.getEmployment().getPersonnelCode();
        LocalDate date = LocalDate.parse(req.getParameter("Date"));
        Payment lastPayment = (Payment) PaymentController.getPaymentController().findByPersonnelCodeAndEndTime(
                personnelCode, LocalDate.parse(req.getParameter("LastPayment")), doer).get(true);

        //      doPOST---------SAVE-FILES---------
        //            -----------------File1
        Part part = req.getPart("Attachment");
        String attachmentName = part.getSubmittedFileName();
        String path = "C:\\root\\ " + attachmentName;
        part.write(path);

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
        resp.sendRedirect("/application/Retirement.jsp");
    }

}
