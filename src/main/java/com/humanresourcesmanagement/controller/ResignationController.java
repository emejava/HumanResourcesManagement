package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.service.PersonService;
import com.humanresourcesmanagement.model.service.ResignationService;

import java.time.LocalDate;

public class ResignationController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static ResignationController resignationController = new ResignationController();

    private ResignationController() {
    }

    public static ResignationController getResignationController() {
        return resignationController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            Person person,
            LocalDate date,
            File attachment,
            User user) {
        Resignation resignation = new Resignation(
                person,
                date,
                attachment);

        try {
            return ResignationService.getResignationService().save(resignation, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            Person person,
            File attachment,
            Payment lastPayment,
            User user) {
        Resignation resignation = new Resignation(
                id,
                person,
                attachment,
                lastPayment);

        try {
            return ResignationService.getResignationService().edit(resignation, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------ACCEPT-RESIGN-------------------------------------------------------
    public String edit(
            Long id,
            Person person,
            Status status,
            User user) {
        person.setStatus(Status.Retired);
        Resignation resignation = new Resignation(
                id,
                person,
                status);

        try {
            PersonService.getPersonService().edit(person, user);
            return ResignationService.getResignationService().edit(resignation, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return ResignationService.getResignationService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
    public String findByPersonnelCode(Long personnelCode, User user) {

        try {
            return ResignationService.getResignationService().findByPersonnelCode(personnelCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-DATE------------------------------------------------------
    public String findByDate(LocalDate date, User user) {
        try {
            return ResignationService.getResignationService().findByDate(date, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
