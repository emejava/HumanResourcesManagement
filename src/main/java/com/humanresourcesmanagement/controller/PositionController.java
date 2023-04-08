package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.Duty;
import com.humanresourcesmanagement.model.entity.Position;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.service.PositionService;

import java.util.List;

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
            User user) {

        Position position = new Position(name);

        try {
            return PositionService.getPositionService().save(position, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            String name,
            User user) {

        Position position = new Position(
                id,
                name);

        try {
            return PositionService.getPositionService().edit(position, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
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
    public String findById(Long id, User user) {

        try {
            return PositionService.getPositionService().findById(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
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
