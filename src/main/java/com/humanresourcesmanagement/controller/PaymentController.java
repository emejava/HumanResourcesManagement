package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Gender;
import com.humanresourcesmanagement.model.entity.enums.MaritalStatus;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.service.PaymentService;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static final PaymentController paymentController = new PaymentController();
    private Map<Boolean, Object> result = new HashMap<>();
    Map<String, String> errors;

    private PaymentController() {
    }

    public static PaymentController getPaymentController() {
        return paymentController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            Year year,
            LocalDate from,
            LocalDate till,
            Person person,
            Double BasicSalary,
            Long overTime,
            Long absenceTime,
            Long operationTime,
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
        //  ---------CREATE-OBJECT-----------------
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
                tax);
        errors.clear();
        errors = Validation.getValidation().doValidation(payment);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return PaymentService.getPaymentService().edit(payment, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            Year year,
            LocalDate from,
            LocalDate till,
            Person person,
            Double BasicSalary,
            Long overTime,
            Long absenceTime,
            Long operationTime,
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
        //  ---------CREATE-OBJECT-----------------
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
        //  ---------VALIDATING-DATA---------------
        errors.clear();
        errors = Validation.getValidation().doValidation(payment);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return PaymentService.getPaymentService().edit(payment, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

    //  ---------SUBMIT-TRANSACTION-NUMBER-------------------------------------------------------
    public Map<Boolean, Object> submitTransactionNumber(
            Long id,
            Long transactionNumber,
            Status status,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Payment payment = new Payment(
                id,
                transactionNumber,
                status);
        result.clear();
        try {
            result.put(true,PaymentService.getPaymentService().edit(payment, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        } finally {
            return result;
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
    public Map<Boolean, Object> findById(Long id, User user) {
        result.clear();
        try {
            result.put(true, PaymentService.getPaymentService().findById(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        } finally {
            return result;
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
    public String findByYearAndMonth(Year year, LocalDate from, LocalDate till, User user) {
        try {
            return PaymentService.getPaymentService().findByYearAndMonth(year, from, till, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-END-TIME-OF-PAYMENT--------------------------------------------
    public Payment findByPersonnelCodeAndEndTime(Long personnelCode, LocalDate till, User user) {
        try {
            return PaymentService.getPaymentService().findByPersonnelCodeAndEndTime(personnelCode, till, user);
        } catch (Exception e) {
            return null; // TODO: How return error with returning object
        }
    }

}
