package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.service.DismissalService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DismissalController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();
    //  ---------SINGLETON---------------------------------------------------------------
    private static DismissalController dismissalController = new DismissalController();

    private DismissalController() {
    }

    public static DismissalController getDismissalController() {
        return dismissalController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean, Object> save(
            Person person,
            String reason,
            LocalDate date,
            Payment lastPayment,
            Attachment attachment,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Dismissal dismissal = new Dismissal(
                person,
                reason,
                date,
                lastPayment,
                attachment
        );

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(dismissal);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                PersonController.getPersonController().changeStatus(person, Status.Fired, user);
                result.put(true, DismissalService.getDismissalService().save(dismissal, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean, Object> edit(
            Long id,
            Person person,
            String reason,
            LocalDate date,
            Payment lastPayment,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Dismissal dismissal = new Dismissal(
                id,
                person,
                reason,
                date,
                lastPayment);
        result.clear();

        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(dismissal);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {

            try {
                result.put(true, DismissalService.getDismissalService().edit(dismissal, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return DismissalService.getDismissalService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
    public Map<Boolean, Object> findByPersonnelCode(Long personnelCode, User user) {
        result.clear();
        try {
            result.put(true, DismissalService.getDismissalService().findByPersonnelCode(personnelCode, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        } finally {
            return result;
        }
    }

    //  ---------SELECT-BY-DATE------------------------------------------------------
    public Map<Boolean, Object> findByDate(LocalDate date, User user) {
        result.clear();
        try {
            result.put(true, DismissalService.getDismissalService().findByDate(date, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        } finally {
            return result;
        }
    }

}

