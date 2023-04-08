package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.AccessLevel;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.entity.Role;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.service.UserService;

public class UserController {

    //    ---------SINGLETON---------------------------------------------------------
    private static UserController userController = new UserController();

    private UserController() {
    }

    public static UserController getUserController() {
        return userController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------

    public String save(
            String username,
            String password,
            Person person,
            Role role,
            AccessLevel accessLevel,
            User user) {

        User newUser = new User(
                username,
                password,
                person,
                role,
                accessLevel);
        try {
            return UserService.getUserService().save(newUser,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }

    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            String username,
            String password,
            Person person,
            AccessLevel accessLevel,
            User user) {

        User newUser = new User(
                username,
                password,
                person,
                accessLevel);

        try {
            return UserService.getUserService().edit(newUser,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ----------DELETE------------------------------------------------------------
    public String delete(Long id,User user) {
        try {
            return UserService.getUserService().delete(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long id,User user) {
        try {
            return UserService.getUserService().deactivate(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public String activate(Long id,User user) {
        try {
            return UserService.getUserService().activate(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
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
    public String findAllActive(User user){
        try {
            return UserService.getUserService().findAllActive(user).toString();
        }catch (Exception e){
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-USERNAME---------------------------------------------------
    public User findByUsername(String username,User user){
        try {
            return UserService.getUserService().findByUsername(username,user);
        }catch (Exception e){
            ExceptionWrapper.getExceptionWrapper().getMessage(e);
            return null;
        }
    }

//    ------------VALIDATE-LOGIN-DATA------------------------------------------------
    public User isValidate(String username, String password) {
        try {
            return UserService.getUserService().isValidate(username, password);
        } catch (Exception e) {
            ExceptionWrapper.getExceptionWrapper().getMessage(e);
            return null;
        }
    }

    //    ----------CHECK-USER-ACCESS------------------------------------------------
    public String hasAccess(String username) {
        try {
            return UserService.getUserService().hasAccess(username);
        } catch (Exception e) {
            ExceptionWrapper.getExceptionWrapper().getMessage(e);
            return null;
        }
    }
}
