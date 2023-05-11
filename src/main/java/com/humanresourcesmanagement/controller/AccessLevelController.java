package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.AccessLevel;
import com.humanresourcesmanagement.model.entity.ErrorsTO;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.service.AccessLevelService;

import java.util.HashMap;
import java.util.Map;

public class AccessLevelController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();

    //  ---------SINGLETON---------------------------------------------------------------
    private static AccessLevelController accessLevelController = new AccessLevelController();

    private AccessLevelController() {
    }

    public static AccessLevelController getAccessLevelController() {
        return accessLevelController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean, Object> save(
            String title,
            Boolean canInsert,
            Boolean canUpdate,
            Boolean canDelete,
            Boolean canFind,
            Boolean canFindAll,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        AccessLevel accessLevel = new AccessLevel(
                title,
                canInsert,
                canUpdate,
                canDelete,
                canFind,
                canFindAll);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(accessLevel);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true,AccessLevelService.getAccessLevelService().save(accessLevel, user));
            } catch (Exception e) {
                result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }return result;
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean, Object> edit(
            Long id,
            String title,
            Boolean canInsert,
            Boolean canUpdate,
            Boolean canDelete,
            Boolean canFind,
            Boolean canFindAll,
            User user) {

        //  ---------CREATE-OBJECT-----------------
        AccessLevel accessLevel = new AccessLevel(
                title,
                canInsert,
                canUpdate,
                canDelete,
                canFind,
                canFindAll);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(accessLevel);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true,AccessLevelService.getAccessLevelService().edit(accessLevel, user));
            } catch (Exception e) {
                result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }return result;
    }

    //  ---------DELETE-------------------------------------------------------------
    public Map<Boolean, Object> delete(Long id, User user) {
        result.clear();
        try {
            result.put(true,AccessLevelService.getAccessLevelService().delete(id, user));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return AccessLevelService.getAccessLevelService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Map<Boolean, Object> findById(Long id, User user) {
        result.clear();

        try {
            result.put(true,AccessLevelService.getAccessLevelService().findById(id, user));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
