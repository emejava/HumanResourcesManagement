package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.Duty;
import com.humanresourcesmanagement.model.entity.Position;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.service.PositionService;

import java.util.List;
import java.util.Map;

public class PositionController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static PositionController positionController = new PositionController();

    private PositionController() {
    }

    public static PositionController getPositionController() {
        return positionController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            String name,
            Duty duty,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Position position = new Position(name, duty);
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(position);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return PositionService.getPositionService().save(position, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }
    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            String name,
            Duty duty,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Position position = new Position(
                id,
                name,
                duty);
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(position);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return PositionService.getPositionService().edit(position, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }
    //  ---------DELETE-------------------------------------------------------------
    public String delete(Long id, User user) {
        try {
            return PositionService.getPositionService().delete(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long id, User user) {
        try {
            return PositionService.getPositionService().deactivate(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return PositionService.getPositionService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return PositionService.getPositionService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Position findById(Long id, User user) {

        try {
            return PositionService.getPositionService().findById(id, user);
        } catch (Exception e) {
            return null;  // TODO: How return error with returning object
        }
    }

    //  ---------SELECT-BY-NAME--------------------------------------------
    public String findByName(String name, User user) {
        try {
            return PositionService.getPositionService().findByName(name, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
