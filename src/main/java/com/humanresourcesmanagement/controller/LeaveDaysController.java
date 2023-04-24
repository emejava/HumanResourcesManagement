package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.LeaveDays;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.service.LeaveDaysService;

import java.sql.Date;

public class LeaveDaysController {

    //  ---------SINGLETON---------------------------------------------------------------
    private static LeaveDaysController leaveDaysController = new LeaveDaysController();

    public LeaveDaysController() {
    }

    public static LeaveDaysController getLeaveDaysController() {
        return leaveDaysController;
    }
    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            Person person,
            Date from,
            Date till,
            Long daysCount,
            String request,
            User user){
        LeaveDays leaveDays = new LeaveDays(
                person,
                from,
                till,
                daysCount,
                request);
        try {
            return LeaveDaysService.getLeaveDaysService().save(leaveDays,user).toString();
        }catch (Exception e){
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
        Long id,
        Person person,
        Date from,
        Date till,
        Long daysCount,
        String request,
        Boolean accepted,
        User user){
        LeaveDays leaveDays = new LeaveDays(
                id,
                person,
                from,
                till,
                daysCount,
                request,
                accepted);
        try {
            return LeaveDaysService.getLeaveDaysService().edit(leaveDays,user).toString();
        }catch (Exception e){
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------DELETE-------------------------------------------------------------
    public String delete(Long id , User user){
        try {
            return LeaveDaysService.getLeaveDaysService().delete(id,user).toString();
        }catch (Exception e){
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user){
        try {
            return LeaveDaysService.getLeaveDaysService().findAll(user).toString();
        }catch (Exception e){
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------SELECT-BY-ID-------------------------------------------------------
    public String findById(Long id,User user){
        try {
            return LeaveDaysService.getLeaveDaysService().findById(id,user).toString();
        }catch (Exception e){
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------SELECT-BY-REQUEST-STATUS--------------------------------------------
    public String findByAccepted(Boolean accepted, User user){
        try {
            return LeaveDaysService.getLeaveDaysService().findByAccepted(accepted,user).toString();
        }catch (Exception e){
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
    public String findByPersonnelCode(String personnelCode, User user){
        try {
            return LeaveDaysService.getLeaveDaysService().findByPersonnelCode(personnelCode,user).toString();
        }catch (Exception e){
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
