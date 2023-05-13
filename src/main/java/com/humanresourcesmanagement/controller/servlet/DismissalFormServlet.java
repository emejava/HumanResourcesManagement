package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.*;
import com.humanresourcesmanagement.model.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/application/dismissal.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class DismissalFormServlet extends HttpServlet {

    //      ---------FIND-PERSONS-------------------------------------doGET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        //    doGET---------PERSON-LIST---------
        req.getSession().setAttribute("EmploymentList", PersonController.getPersonController().findAllActive(doer));

        resp.sendRedirect("/application/Dismissal.jsp");
    }

    //      ---------INSERT-DATA-------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        //    doPOST---------EMPLOYMENT-DATA------
        Person person = (Person) PersonController.getPersonController().findById(
                Long.valueOf(req.getParameter("PersonId")), doer).get(true);
        Long personnelCode = person.getEmployment().getPersonnelCode();
        String reason = req.getParameter("Reason");
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        Payment lastPayment = (Payment) PaymentController.getPaymentController().findByPersonnelCodeAndEndTime(
                personnelCode, LocalDate.parse(req.getParameter("LastPayment")), doer).get(true);

        //      doPOST---------SAVE-FILES---------
        //            -----------------File1
        Part CVParts = req.getPart("Attachment");
        String CVName = CVParts.getSubmittedFileName();
        String path = "c:\\root\\" + CVName;
        CVParts.write(path);
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
        resp.sendRedirect("/application/Dismissal.jsp");
    }
}
