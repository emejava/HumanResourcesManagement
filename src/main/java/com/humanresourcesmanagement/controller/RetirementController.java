package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.service.RetirementService;

import java.time.LocalDate;
import java.util.Map;

public class RetirementController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static RetirementController retirementController = new RetirementController();

    private RetirementController() {
    }

    public static RetirementController getRetirementController() {
        return retirementController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
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

        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(retirement);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                PersonController.getPersonController().changeStatus(person, Status.Retired, user);
                return RetirementService.getRetirementService().save(retirement, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
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
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(retirement);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return RetirementService.getRetirementService().edit(retirement, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return RetirementService.getRetirementService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
    public String findByPersonnelCode(Long personnelCode, User user) {

        try {
            return RetirementService.getRetirementService().findByPersonnelCode(personnelCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-DATE------------------------------------------------------
    public String findByDate(LocalDate date, User user) {
        try {
            return RetirementService.getRetirementService().findByDate(date, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
