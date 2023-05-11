package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.service.PersonService;
import com.humanresourcesmanagement.model.service.ResignationService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ResignationController {

    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();
    //  ---------SINGLETON---------------------------------------------------------------
    private static ResignationController resignationController = new ResignationController();

    private ResignationController() {
    }

    public static ResignationController getResignationController() {
        return resignationController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object> save(
            Person person,
            LocalDate date,
            String reason,
            Attachment attachment,
            Payment lastPayment,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Resignation resignation = new Resignation(
                person,
                date,
                reason,
                attachment,
                lastPayment
        );

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(resignation);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                PersonController.getPersonController().changeStatus(person, Status.Pending, user);
                result.put(true, ResignationService.getResignationService().save(resignation, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

        //  ---------UPDATE-DATA--------------------------------------------------------
        public Map<Boolean,Object> edit (
                Long id,
                Person person,
                Attachment attachment,
                Payment lastPayment,
                User user) {
            //  ---------CREATE-OBJECT-----------------
            Resignation resignation = new Resignation(
                    id,
                    person,
                    attachment,
                    lastPayment);

            result.clear();
            //  ---------VALIDATING-DATA---------------
            Map<String, String> errors = Validation.getValidation().doValidation(resignation);
            if (errors != null) {
                errorsTO.setErrors(errors);
                result.put(false, errorsTO);
            } else {
                try {
                    result.put(true, ResignationService.getResignationService().edit(resignation, user));
                } catch (Exception e) {
                    result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
                }
            }
            return result;
        }


        //  ---------ACCEPT-RESIGN-------------------------------------------------------
        public Map<Boolean,Object> accept (
                Long id,
                Person person,
                Status status,
                User user){
            person.setStatus(Status.Retired);
            Resignation resignation = new Resignation(
                    id,
                    person,
                    status);

            result.clear();
            try {
                PersonService.getPersonService().edit(person, user);
                result.put(true, ResignationService.getResignationService().edit(resignation, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
            return result;
        }

        //  ---------SELECT-ALL---------------------------------------------------------
        public String findAll (User user){
            try {
                return ResignationService.getResignationService().findAll(user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }

        //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
        public Map<Boolean,Object> findByPersonnelCode (Long personnelCode, User user){
            result.clear();
            try {
                result.put(true, ResignationService.getResignationService().findByPersonnelCode(personnelCode, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }finally {
                return result;
            }
        }

        //  ---------SELECT-BY-DATE------------------------------------------------------
        public Map<Boolean,Object> findByDate (LocalDate date, User user){
            result.clear();
            try {
                result.put(true, ResignationService.getResignationService().findByDate(date, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }finally {
                return result;
            }
        }
    }
