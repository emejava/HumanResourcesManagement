package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.*;
import com.humanresourcesmanagement.model.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/application/paymentSubmit.do")
public class PaymentSubmitServlet extends HttpServlet {

    //      ---------INSERT-DATA-------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");

        //    doPOST---------PAYMENT-DATA------
        Year year = Year.parse(req.getParameter("Year"));
        LocalDate from = LocalDate.parse(req.getParameter("From"));
        LocalDate till = LocalDate.parse(req.getParameter("Till"));
        Person person = (Person) PersonController.getPersonController().findByPersonnelCode(
                Long.valueOf(req.getParameter("PersonnelCode")), doer).get(true);
        Double basicSalary = Double.valueOf(req.getParameter("BasicSalary"));
        Long overTime = Long.valueOf(req.getParameter("OverTime"));
        Long absenceTime = Long.valueOf(req.getParameter("AbsenceTime"));
        Long operationTime = Long.valueOf(req.getParameter("OperationTime"));
        Double overTimePayCount = Double.valueOf(req.getParameter("OverTimePayCount"));
        Double absencePayCount = Double.valueOf(req.getParameter("AbsencePayCount"));
        Double operationPayCount = Double.valueOf(req.getParameter("OperationPayCount"));
        Double housing = Double.valueOf(req.getParameter("Housing"));
        List<LeaveDays> leaveDays = new ArrayList<>();
        leaveDays.add((LeaveDays) LeaveDaysController.getLeaveDaysController().findByPersonnelCodeAndBetweenTime(
                Long.valueOf(req.getParameter("PersonnelCode")),from,till,doer).get(true));
        Double leaveDaysPayDeduction = Double.valueOf(req.getParameter("LeaveDaysPayDeduction"));
        Double benefits = Double.valueOf(req.getParameter("Benefits"));
        Double managementBonus = Double.valueOf(req.getParameter("ManagementBonus"));
        Double childrenPay = Double.valueOf(req.getParameter("ChildrenPay"));
        Double severancePay = Double.valueOf(req.getParameter("SeverancePay"));
        Double insurance = Double.valueOf(req.getParameter("Insurance"));
        Double tax = Double.valueOf(req.getParameter("Tax"));

        //  -----VALIDATE-AND-SAVE-DATA-WITH-CONTROLLER-----
        PaymentController.getPaymentController().save(
                year,
                from,
                till,
                person,
                basicSalary,
                overTime,
                absenceTime,
                operationTime,
                overTimePayCount,
                absencePayCount,
                operationPayCount,
                housing,
                leaveDays,
                leaveDaysPayDeduction,
                benefits,
                managementBonus,
                childrenPay,
                severancePay,
                insurance,
                tax,
                doer
        );

        //  doPOST------RESPONSE---------------------
        resp.sendRedirect("/application/Payment.jsp");

    }
}
