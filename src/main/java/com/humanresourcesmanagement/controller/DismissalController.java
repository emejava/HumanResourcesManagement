package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.service.DismissalService;
import com.humanresourcesmanagement.model.service.PersonService;

import java.time.LocalDate;

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
            File attachment,
            User user) {
        person.setStatus(Status.Fired);
        Dismissal dismissal = new Dismissal(
                person,
                reason,
                date,
                lastPayment,
                attachment);

        try {
            PersonService.getPersonService().edit(person,user);
            return DismissalService.getDismissalService().save(dismissal, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
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

        Dismissal dismissal = new Dismissal(
                id,
                person,
                reason,
                date,
                lastPayment);

        try {
            return DismissalService.getDismissalService().edit(dismissal, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
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
    public String findByPersonnelCode(Long personnelCode, User user) {

        try {
            return DismissalService.getDismissalService().findByPersonnelCode(personnelCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-DATE------------------------------------------------------
    public String findByDate(LocalDate date, User user) {
        try {
            return DismissalService.getDismissalService().findByDate(date, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

}

