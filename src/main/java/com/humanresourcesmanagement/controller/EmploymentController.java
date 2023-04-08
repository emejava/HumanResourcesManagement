package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.EmploymentType;
import com.humanresourcesmanagement.model.entity.enums.ShiftWork;
import com.humanresourcesmanagement.model.service.EmploymentService;
import com.humanresourcesmanagement.model.service.EmploymentService;

import java.sql.Timestamp;
import java.util.List;

public class EmploymentController {

    //  ---------SINGLETON---------------------------------------------------------------
    private static final EmploymentController employmentController = new EmploymentController();

    private EmploymentController() {
    }

    public static EmploymentController getEmploymentController() {
        return employmentController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            Long personnelCode,
            Person person,
            EmploymentType employmentType,
            Unit unit,
            Duty duty,
            Position position,
            Timestamp startWorkingDate,
            ShiftWork shiftWork,
            User user) {
        Employment employment = new Employment(
                personnelCode,
                person,
                employmentType,
                unit,
                duty,
                position,
                startWorkingDate,
                shiftWork);

        try {
            return EmploymentService.getEmploymentService().save(employment, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long personnelCode,
            Person person,
            EmploymentType employmentType,
            Unit unit,
            Duty duty,
            Position position,
            Timestamp startWorkingDate,
            ShiftWork shiftWork,
            File CV,
            User user) {

        Employment employment = new Employment(
                personnelCode,
                person,
                employmentType,
                unit,
                duty,
                position,
                startWorkingDate,
                shiftWork,
                CV);

        try {
            return EmploymentService.getEmploymentService().edit(employment, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String delete(Long id, User user) {
        try {
            return EmploymentService.getEmploymentService().delete(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return EmploymentService.getEmploymentService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public String findById(Long personnelCode, User user) {
        try {
            return EmploymentService.getEmploymentService().findById(personnelCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
