package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.service.DismissalService;

import java.time.LocalDate;
import java.util.Map;

public class DismissalController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static DismissalController dismissalController = new DismissalController();

    private DismissalController() {
    }

    public static DismissalController getDismissalController() {
        return dismissalController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
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

        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(dismissal);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                PersonController.getPersonController().changeStatus(person, Status.Fired, user);
                return DismissalService.getDismissalService().save(dismissal, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
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

        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(dismissal);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {

            try {
                return DismissalService.getDismissalService().edit(dismissal, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

        //  ---------SELECT-ALL---------------------------------------------------------
        public String findAll (User user){
            try {
                return DismissalService.getDismissalService().findAll(user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }

        //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
        public String findByPersonnelCode (Long personnelCode, User user){

            try {
                return DismissalService.getDismissalService().findByPersonnelCode(personnelCode, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }

        //  ---------SELECT-BY-DATE------------------------------------------------------
        public String findByDate (LocalDate date, User user){
            try {
                return DismissalService.getDismissalService().findByDate(date, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }

    }

