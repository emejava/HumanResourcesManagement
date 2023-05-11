package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.UnitService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();
    //  ---------SINGLETON---------------------------------------------------------------
    private static UnitController unitController = new UnitController();

    private UnitController() {
    }

    public static UnitController getUnitController() {
        return unitController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object> save(
            String name,
            String city,
            List<Duty> duties,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Unit unit = new Unit(
                name,
                city,
                duties);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(unit);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, UnitService.getUnitService().save(unit, user));
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
            List<Unit> relatedUnits,
            String city,
            List<Duty> duties,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Unit unit = new Unit(
                id,
                name,
                relatedUnits,
                city,
                duties);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(unit);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, UnitService.getUnitService().edit(unit, user));
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
            result.put(true, UnitService.getUnitService().delete(id, user));
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
            result.put(true, UnitService.getUnitService().deactivate(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return UnitService.getUnitService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return UnitService.getUnitService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Map<Boolean,Object> findById(Long id, User user) {
        result.clear();
        try {
            result.put(true, UnitService.getUnitService().findById(id, user));
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
            result.put(true, UnitService.getUnitService().findByName(name, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-CITY--------------------------------------------
    public Map<Boolean,Object> findByCity(String city, User user) {
        result.clear();
        try {
            result.put(true, UnitService.getUnitService().findByName(city, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
