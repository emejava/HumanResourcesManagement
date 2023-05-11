package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.service.RetirementService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RetirementController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();
    //  ---------SINGLETON---------------------------------------------------------------
    private static RetirementController retirementController = new RetirementController();

    private RetirementController() {
    }

    public static RetirementController getRetirementController() {
        return retirementController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object>  save(
            Person person,
            LocalDate date,
            Payment lastPayment,
            Attachment attachment,
            User user) {

        //  ---------CREATE-OBJECT-----------------
        Retirement retirement = new Retirement(
                person,
                date,
                lastPayment,
                attachment
        );

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(retirement);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                PersonController.getPersonController().changeStatus(person, Status.Retired, user);
                result.put(true, RetirementService.getRetirementService().save(retirement, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean,Object>  edit(
            Long id,
            Person person,
            LocalDate date,
            Payment lastPayment,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Retirement retirement = new Retirement(
                id,
                person,
                date,
                lastPayment);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(retirement);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, RetirementService.getRetirementService().edit(retirement, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String  findAll(User user) {
        try {
            return RetirementService.getRetirementService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
    public Map<Boolean,Object>  findByPersonnelCode(Long personnelCode, User user) {
        result.clear();
        try {
            result.put(true, RetirementService.getRetirementService().findByPersonnelCode(personnelCode, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-DATE------------------------------------------------------
    public Map<Boolean,Object>  findByDate(LocalDate date, User user) {
        result.clear();
        try {
            result.put(true, RetirementService.getRetirementService().findByDate(date, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
