package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.Duty;
import com.humanresourcesmanagement.model.entity.ErrorsTO;
import com.humanresourcesmanagement.model.entity.Position;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.service.PositionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();
    //  ---------SINGLETON---------------------------------------------------------------
    private static PositionController positionController = new PositionController();

    private PositionController() {
    }

    public static PositionController getPositionController() {
        return positionController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object> save(
            String name,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Position position = new Position(name);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(position);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, PositionService.getPositionService().save(position, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }
    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean,Object> edit(
            Long id,
            String name,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Position position = new Position(
                id,
                name);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(position);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, PositionService.getPositionService().edit(position, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }
    //  ---------DELETE-------------------------------------------------------------
    public Map<Boolean,Object> delete(Long id, User user) {
        result.clear();
        try {
            result.put(true, PositionService.getPositionService().delete(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Map<Boolean,Object> deactivate(Long id, User user) {
        result.clear();
        try {
            result.put(true, PositionService.getPositionService().deactivate(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
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
    public Map<Boolean,Object> findById(Long id, User user) {
        result.clear();
        try {
            result.put(true, PositionService.getPositionService().findById(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-NAME--------------------------------------------
    public Map<Boolean,Object> findByName(String name, User user) {
        result.clear();
        try {
            result.put(true, PositionService.getPositionService().findByName(name, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
