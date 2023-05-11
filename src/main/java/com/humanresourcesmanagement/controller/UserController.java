package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();

    //    ---------SINGLETON---------------------------------------------------------
    private static UserController userController = new UserController();

    private UserController() {
    }

    public static UserController getUserController() {
        return userController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------

    public Map<Boolean,Object> save(
            String username,
            String password,
            Person person,
            Role role,
            AccessLevel accessLevel,
            User user) {

        //  ---------CREATE-OBJECT-----------------
        User newUser = new User(
                username,
                password,
                person,
                role,
                accessLevel);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(user);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, UserService.getUserService().save(newUser, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean,Object> edit(
            String username,
            String password,
            Person person,
            AccessLevel accessLevel,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        User newUser = new User(
                username,
                password,
                person,
                accessLevel);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(user);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, UserService.getUserService().edit(newUser, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ----------DELETE------------------------------------------------------------
    public Map<Boolean,Object> delete(Long id, User user) {
        result.clear();
        try {
            result.put(true, UserService.getUserService().delete(id, user));
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
            result.put(true, UserService.getUserService().deactivate(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public Map<Boolean,Object> activate(Long id, User user) {
        result.clear();
        try {
            result.put(true, UserService.getUserService().activate(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
           return UserService.getUserService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return UserService.getUserService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-USERNAME---------------------------------------------------
    public Map<Boolean,Object> findByUsername(String username, User user) {
        result.clear();
        try {
            result.put(true, UserService.getUserService().findByUsername(username, user));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //    ------------VALIDATE-LOGIN-DATA------------------------------------------------
    public Map<Boolean,Object> isValidate(String username, String password) {
        result.clear();
        try {
            result.put(true, UserService.getUserService().isValidate(username, password));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //    ----------CHECK-USER-ACCESS------------------------------------------------
    public Map<Boolean,Object> hasAccess(String username) {
        result.clear();
        try {
            result.put(true, UserService.getUserService().hasAccess(username));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
