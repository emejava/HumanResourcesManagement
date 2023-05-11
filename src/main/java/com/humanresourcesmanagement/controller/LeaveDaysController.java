package com.humanresourcesmanagement.controller;

import com.fasterxml.jackson.databind.ObjectReader;
import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.ErrorsTO;
import com.humanresourcesmanagement.model.entity.LeaveDays;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.service.LeaveDaysService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LeaveDaysController {

    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();

    //  ---------SINGLETON---------------------------------------------------------------
    private static LeaveDaysController leaveDaysController = new LeaveDaysController();

    public LeaveDaysController() {
    }

    public static LeaveDaysController getLeaveDaysController() {
        return leaveDaysController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object> save(
            Person person,
            LocalDate from,
            LocalDate till,
            Byte daysCount,
            String request,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        LeaveDays leaveDays = new LeaveDays(
                person,
                from,
                till,
                daysCount,
                request);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(leaveDays);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, LeaveDaysService.getLeaveDaysService().save(leaveDays, user));
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
            LocalDate from,
            LocalDate till,
            Byte daysCount,
            String request,
            Boolean accepted,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        LeaveDays leaveDays = new LeaveDays(
                id,
                person,
                from,
                till,
                daysCount,
                request,
                accepted);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(leaveDays);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true,LeaveDaysService.getLeaveDaysService().edit(leaveDays, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }return result;

    }

    //  ---------DELETE-------------------------------------------------------------
    public Map<Boolean, Object> delete(Long id, User user) {
        result.clear();
        try {
            result.put(true,LeaveDaysService.getLeaveDaysService().delete(id, user));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return LeaveDaysService.getLeaveDaysService().findAll(user).toString();
        } catch (Exception e) {
           return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Map<Boolean, Object> findById(Long id, User user) {
        result.clear();
        try {
            result.put(true,LeaveDaysService.getLeaveDaysService().findById(id, user));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-REQUEST-STATUS--------------------------------------------
    public Map<Boolean, Object> findByAccepted(Boolean accepted, User user) {
        result.clear();
        try {
            result.put(true,LeaveDaysService.getLeaveDaysService().findByAccepted(accepted, user));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
    public Map<Boolean, Object> findByPersonnelCode(String personnelCode, User user) {
        result.clear();
        try {
            result.put(true,LeaveDaysService.getLeaveDaysService().findByPersonnelCode(personnelCode, user));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE-IN-TIME-PERIOD----------------------------------
    public Map<Boolean,Object> findByPersonnelCodeAndBetweenTime(
            Long personnelCode,LocalDate from,LocalDate till,User user){
        result.clear();
        try {
            result.put(true,LeaveDaysService.getLeaveDaysService().findByPersonnelCodeAndBetweenTime(
                    personnelCode,from,till,user));
        }catch (Exception e){
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
