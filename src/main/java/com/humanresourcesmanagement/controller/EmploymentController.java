package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.EmploymentType;
import com.humanresourcesmanagement.model.entity.enums.ShiftWork;
import com.humanresourcesmanagement.model.service.EmploymentService;

import java.time.LocalDate;
import java.util.Map;

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
            LocalDate startWorkingDate,
            ShiftWork shiftWork,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Employment employment = new Employment(
                personnelCode,
                person,
                employmentType,
                unit,
                duty,
                position,
                startWorkingDate,
                shiftWork
        );

        //  ---------VALIDATING-DATA---------------
        Map<String, String> employmentErrors = Validation.getValidation().doValidation(employment);
        if (employmentErrors != null) {
            return new Gson().toJson(employmentErrors);
        } else {
            try {
                PersonController.getPersonController().setPersonEmployment(person, employment, user);
                return EmploymentService.getEmploymentService().save(employment, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
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
            LocalDate startWorkingDate,
            ShiftWork shiftWork,
            Attachment CV,
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
        Map<String, String> employmentErrors = Validation.getValidation().doValidation(employment);
        if (employmentErrors != null) {
            return new Gson().toJson(employmentErrors);
        } else {
            try {
                return EmploymentService.getEmploymentService().edit(employment, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

        //  ---------LOGICAL-DELETE-----------------------------------------------------
        public String delete (Long id, User user){
            try {
                return EmploymentService.getEmploymentService().delete(id, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }

        //  ---------SELECT-ALL---------------------------------------------------------
        public String findAll (User user){
            try {
                return EmploymentService.getEmploymentService().findAll(user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }

        //  ---------SELECT-BY-ID-------------------------------------------------------
        public String findById (Long personnelCode, User user){
            try {
                return EmploymentService.getEmploymentService().findById(personnelCode, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }
