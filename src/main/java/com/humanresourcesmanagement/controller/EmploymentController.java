package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.EmploymentType;
import com.humanresourcesmanagement.model.entity.enums.ShiftWork;
import com.humanresourcesmanagement.model.service.EmploymentService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EmploymentController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();

    //  ---------SINGLETON---------------------------------------------------------------
    private static final EmploymentController employmentController = new EmploymentController();

    private EmploymentController() {
    }

    public static EmploymentController getEmploymentController() {
        return employmentController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object> save(
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

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(employment);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                PersonController.getPersonController().setPersonEmployment(person, employment, user);
                result.put(true, EmploymentService.getEmploymentService().save(employment, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean,Object> edit(
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

        result.clear();
        Map<String, String> errors = Validation.getValidation().doValidation(employment);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, EmploymentService.getEmploymentService().edit(employment, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

        //  ----------DELETE-----------------------------------------------------
        public Map<Boolean,Object> delete (Long id, User user){
            result.clear();
            try {
                result.put(true, EmploymentService.getEmploymentService().delete(id, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }finally {
                return result;
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
        public Map<Boolean,Object> findById (Long personnelCode, User user){
            result.clear();
            try {
                result.put(true, EmploymentService.getEmploymentService().findById(personnelCode, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }finally {
                return result;
            }
        }
    }
