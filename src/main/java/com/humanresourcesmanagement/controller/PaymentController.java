package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Gender;
import com.humanresourcesmanagement.model.entity.enums.MaritalStatus;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.service.PaymentService;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Year;
import java.util.List;

public class PaymentController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static final PaymentController paymentController = new PaymentController();

    private PaymentController() {
    }

    public static PaymentController getPaymentController() {
        return paymentController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            Year year,
            Date from,
            Date till,
            Person person,
            Double BasicSalary,
            Time overTime,
            Time absenceTime,
            Time operationTime,
            Double overTimePayCount,
            Double absenceTimePayCount,
            Double operationTimePayCount,
            Double housing,
            List<LeaveDays> leaveDays,
            Double leaveDaysPayDeduction,
            Double benefits,
            Double managementBonus,
            Double childrenPay,
            Double severancePay,
            Double insurance,
            Double tax,
            User user) {
        Double debt = absenceTimePayCount + leaveDaysPayDeduction + insurance + tax;
        Double totalPayment = BasicSalary + overTimePayCount + operationTimePayCount + housing + benefits + managementBonus + childrenPay + severancePay - debt;
        Payment payment = new Payment(
                year,
                from,
                till,
                person,
                BasicSalary,
                overTime,
                absenceTime,
                operationTime,
                overTimePayCount,
                absenceTimePayCount,
                operationTimePayCount,
                housing,
                leaveDays,
                leaveDaysPayDeduction,
                benefits,
                managementBonus,
                childrenPay,
                severancePay,
                insurance,
                tax,
                debt,
                totalPayment);
        try {
            return PaymentService.getPaymentService().save(payment, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            Year year,
            Date from,
            Date till,
            Person person,
            Double BasicSalary,
            Time overTime,
            Time absenceTime,
            Time operationTime,
            Double overTimePayCount,
            Double absenceTimePayCount,
            Double operationTimePayCount,
            Double housing,
            List<LeaveDays> leaveDays,
            Double leaveDaysPayDeduction,
            Double benefits,
            Double managementBonus,
            Double childrenPay,
            Double severancePay,
            Double insurance,
            Double tax,
            Double debt,
            Double totalPayment,
            Long transactionNumber,
            Status status,
            User user) {

        Payment payment = new Payment(
                id,
                year,
                from,
                till,
                person,
                BasicSalary,
                overTime,
                absenceTime,
                operationTime,
                overTimePayCount,
                absenceTimePayCount,
                operationTimePayCount,
                housing,
                leaveDays,
                leaveDaysPayDeduction,
                benefits,
                managementBonus,
                childrenPay,
                severancePay,
                insurance,
                tax,
                debt,
                totalPayment,
                transactionNumber,
                status);

        try {
            return PaymentService.getPaymentService().edit(payment, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SUBMIT-TRANSACTION-NUMBER-------------------------------------------------------
    public String submitTransactionNumber(
            Long id,
            Long transactionNumber,
            User user) {

        Payment payment = new Payment(
                id,
                transactionNumber);
        payment.setStatus(Status.Payed);
        try {
            return PaymentService.getPaymentService().edit(payment, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String delete(Long id, User user) {
        try {
            return PaymentService.getPaymentService().delete(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long id, User user) {
        try {
            return PaymentService.getPaymentService().deactivate(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return PaymentService.getPaymentService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return PaymentService.getPaymentService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public String findById(Long id, User user) {
        try {
            return PaymentService.getPaymentService().findById(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE---------------------------------------------
    public String findByPersonnelCode(Long personnelCode, User user) {
        try {
            return PaymentService.getPaymentService().findByPersonnelCode(personnelCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-NATIONAL-CODE----------------------------------------------
    public String findByNationalCode(String nationalCode, User user) {
        try {
            return PaymentService.getPaymentService().findByNationalCode(nationalCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-YEAR------------------------------------------------------
    public String findByYear(Year year, User user) {
        try {
            return PaymentService.getPaymentService().findByYear(year, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-YEAR-AND-MONTH--------------------------------------------
    public String findByYearAndMonth(Year year, Date from, Date till, User user) {
        try {
            return PaymentService.getPaymentService().findByYearAndMonth(year, from, till, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

}
