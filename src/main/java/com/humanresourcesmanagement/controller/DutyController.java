package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.DutyService;


import java.util.List;

public class DutyController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static DutyController dutyController = new DutyController();

    private DutyController() {
    }

    public static DutyController getDutyController() {
        return dutyController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            Position position,
            String dutyExplanation,
            User user) {

        Duty duty = new Duty(position,dutyExplanation);

        try {
            return DutyService.getDutyService().save(duty, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            Position position,
            String dutyExplanation,
            User user) {

        Duty duty = new Duty(
                id,
                position,
                dutyExplanation);

        try {
            return DutyService.getDutyService().edit(duty, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public String delete(Long id, User user) {
        try {
            return DutyService.getDutyService().delete(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long id, User user) {
        try {
            return DutyService.getDutyService().deactivate(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return DutyService.getDutyService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return DutyService.getDutyService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public String findById(Long id, User user) {

        try {
            return DutyService.getDutyService().findById(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-POSITION-------------------------------------------------
    public String findByPosition(Position position, User user) {
        try {
            return DutyService.getDutyService().findByPosition(position, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
