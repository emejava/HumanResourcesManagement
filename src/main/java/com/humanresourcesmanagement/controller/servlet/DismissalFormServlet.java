package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.*;
import com.humanresourcesmanagement.model.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "dismissal.do")
public class DismissalFormServlet extends HttpServlet {

    //      ---------FIND-PERSONS-------------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        //    doPOST---------PERSON-LIST---------
        req.getSession().setAttribute("EmploymentList", PersonController.getPersonController().findAllActive(doer));

        resp.sendRedirect("/Dismissal.jsp");
    }

    //      ---------INSERT-DATA-------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        //    doPOST---------EMPLOYMENT-DATA------
        Person person = PersonController.getPersonController().findById(Long.valueOf(req.getParameter("PersonId")), doer);
        Long personnelCode = person.getEmployment().getPersonnelCode();         //TODO: Front return the person id to servlet but show id, first and last name
        String reason = req.getParameter("Reason");
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        Payment lastPayment = PaymentController.getPaymentController().findByPersonnelCodeAndEndTime(
                personnelCode, LocalDate.parse(req.getParameter("LastPayment")), doer);

        //      doPOST---------SAVE-FILES---------
        //            -----------------File1
        Part CVParts = req.getPart("Attachment");
        String CVName = CVParts.getSubmittedFileName();
        String path = "c:\\root\\" + CVName;
        for (Part part : req.getParts()) {
            part.write(path);
        }
        Attachment CV = new Attachment(CVName, doer, path);


        //  -----VALIDATE-AND-SAVE-DATA-WITH-CONTROLLER-----
        DismissalController.getDismissalController().save(
                person,
                reason,
                date,
                lastPayment,
                CV,
                doer
        );
        //  doPOST------RESPONSE---------------------
        resp.sendRedirect("/Dismissal.jsp");
    }
}
