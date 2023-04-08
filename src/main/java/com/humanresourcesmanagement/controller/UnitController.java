package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.UnitService;

import java.util.List;

public class UnitController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static UnitController unitController = new UnitController();

    private UnitController() {
    }

    public static UnitController getUnitController() {
        return unitController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            String name,
            List<Unit> relatedUnits,
            String city,
            List<Duty> duties,
            User user) {

        Unit unit = new Unit(
                name,
                relatedUnits,
                city,
                duties);

        try {
            return UnitService.getUnitService().save(unit, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            String name,
            List<Unit> relatedUnits,
            String city,
            List<Duty> duties,
            User user) {

        Unit unit = new Unit(
                id,
                name,
                relatedUnits,
                city,
                duties);

        try {
            return UnitService.getUnitService().edit(unit, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public String delete(Long id, User user) {
        try {
            return UnitService.getUnitService().delete(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long id, User user) {
        try {
            return UnitService.getUnitService().deactivate(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
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
    public String findById(Long id, User user) {

        try {
            return UnitService.getUnitService().findById(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-NAME--------------------------------------------
    public String findByName(String name, User user) {
        try {
            return UnitService.getUnitService().findByName(name, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-CITY--------------------------------------------
    public String findByCity(String city, User user) {
        try {
            return UnitService.getUnitService().findByName(city, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
