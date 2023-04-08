package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.Employment;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.repository.CrudRepository;
import java.util.List;

public class EmploymentService {
    //  ---------SINGLETON---------------------------------------------------------
    private static EmploymentService employmentService = new EmploymentService();

    private EmploymentService() {
    }

    public static EmploymentService getEmploymentService() {
        return employmentService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Employment save(Employment employment, User user) throws Exception {
        try (CrudRepository<Employment, Long> employmentDa = new CrudRepository<>()) {
            employment = employmentDa.save(employment);
            Log log = new Log(Action.Insert, employment.toString(), user);
            LogService.getLogService().save(log);
            return employment;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Employment edit(Employment employment, User user) throws Exception {
        try (CrudRepository<Employment, Long> employmentDa = new CrudRepository<>()) {
            employment = employmentDa.edit(employment);
            Log log = new Log(Action.Update, employment.toString(), user);
            LogService.getLogService().save(log);
            return employment;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public Employment delete(Long id, User user) throws Exception {
        try (CrudRepository<Employment, Long> employmentDa = new CrudRepository<>()) {
            Employment employment = employmentDa.delete(Employment.class, id);
            Log log = new Log(Action.Delete, employment.toString(), user);
            LogService.getLogService().save(log);
            return employment;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Employment> findAll(User user) throws Exception {
        try (CrudRepository<Employment, Long> employmentDa = new CrudRepository<>()) {
            List<Employment> employmentList = employmentDa.findAll(Employment.class);
            Log log = new Log(Action.Search, "All Employments", user);
            LogService.getLogService().save(log);
            return employmentList;
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE--------------------------------------------
    public Employment findById(Long personnelCode, User user) throws Exception {
        try (CrudRepository<Employment, Long> employmentDa = new CrudRepository<>()) {
            Employment employment = employmentDa.findById(Employment.class, personnelCode);
            Log log = new Log(Action.Search, employment.toString(), user);
            LogService.getLogService().save(log);
            return employment;
        }
    }
}