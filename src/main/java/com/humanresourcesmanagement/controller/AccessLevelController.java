package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.AccessLevel;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.service.AccessLevelService;

public class AccessLevelController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static AccessLevelController accessLevelController = new AccessLevelController();

    private AccessLevelController() {
    }

    public static AccessLevelController getAccessLevelController() {
        return accessLevelController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            String title,
            Boolean canInsert,
            Boolean canUpdate,
            Boolean canDelete,
            Boolean canFind,
            Boolean canFindAll,
            User user) {

        AccessLevel accessLevel = new AccessLevel(
                title,
                canInsert,
                canUpdate,
                canDelete,
                canFind,
                canFindAll);

        try {
            return AccessLevelService.getAccessLevelService().save(accessLevel,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            String title,
            Boolean canInsert,
            Boolean canUpdate,
            Boolean canDelete,
            Boolean canFind,
            Boolean canFindAll,
            User user) {

        AccessLevel accessLevel = new AccessLevel(
                id,
                title,
                canInsert,
                canUpdate,
                canDelete,
                canFind,
                canFindAll);

        try {
            return AccessLevelService.getAccessLevelService().edit(accessLevel,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------DELETE-------------------------------------------------------------
    public String delete(Long id,User user) {
        try {
            return AccessLevelService.getAccessLevelService().delete(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
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
    public String findById(Long id,User user) {

        try {
            return AccessLevelService.getAccessLevelService().findById(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
