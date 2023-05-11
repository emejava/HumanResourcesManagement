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

    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();

    //  ---------SINGLETON---------------------------------------------------------------
    private static final PaymentController paymentController = new PaymentController();

    private PaymentController() {
    }

    public static PaymentController getPaymentController() {
        return paymentController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean, Object> save(
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
        result.clear();
        Map<String,String> errors = Validation.getValidation().doValidation(payment);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, PaymentService.getPaymentService().edit(payment, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean,Object> edit(
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

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String,String> errors = Validation.getValidation().doValidation(payment);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, PaymentService.getPaymentService().edit(payment, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
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
    public Map<Boolean,Object> delete(Long id, User user) {
        result.clear();
        try {
            result.put(true, PaymentService.getPaymentService().delete(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Map<Boolean,Object> deactivate(Long id, User user) {
        result.clear();
        try {
            result.put(true, PaymentService.getPaymentService().deactivate(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        result.clear();
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
    public Map<Boolean,Object> findByPersonnelCode(Long personnelCode, User user) {
        result.clear();
        try {
            result.put(true, PaymentService.getPaymentService().findByPersonnelCode(personnelCode, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-NATIONAL-CODE----------------------------------------------
    public Map<Boolean,Object> findByNationalCode(String nationalCode, User user) {
        result.clear();
        try {
            result.put(true, PaymentService.getPaymentService().findByNationalCode(nationalCode, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-YEAR------------------------------------------------------
    public Map<Boolean,Object> findByYear(Year year, User user) {
        result.clear();
        try {
            result.put(true, PaymentService.getPaymentService().findByYear(year, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-YEAR-AND-MONTH--------------------------------------------
    public Map<Boolean,Object> findByYearAndMonth(Year year, LocalDate from, LocalDate till, User user) {
        result.clear();
        try {
            result.put(true, PaymentService.getPaymentService().findByYearAndMonth(year, from, till, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-END-TIME-OF-PAYMENT--------------------------------------------
    public Map<Boolean,Object> findByPersonnelCodeAndEndTime(Long personnelCode, LocalDate till, User user) {
        result.clear();
        try {
            result.put(true, PaymentService.getPaymentService().findByPersonnelCodeAndEndTime(personnelCode, till, user));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

}
