package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.Resignation;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResignationService {
    //  ---------SINGLETON---------------------------------------------------------
    private static ResignationService resignationService = new ResignationService();

    private ResignationService() {
    }

    public static ResignationService getResignationService() {
        return resignationService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Resignation save(Resignation resignation, User user) throws Exception {
        try (CrudRepository<Resignation, Long> resignationDa = new CrudRepository<>()) {
            resignation = resignationDa.save(resignation);
            Log log = new Log(Action.Insert, resignation.toString(), user);
            LogService.getLogService().save(log);
            return resignation;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Resignation edit(Resignation resignation, User user) throws Exception {
        try (CrudRepository<Resignation, Long> resignationDa = new CrudRepository<>()) {
            resignation = resignationDa.edit(resignation);
            Log log = new Log(Action.Update, resignation.toString(), user);
            LogService.getLogService().save(log);
            return resignation;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Resignation> findAll(User user) throws Exception {
        try (CrudRepository<Resignation, Long> resignationDa = new CrudRepository<>()) {
            List<Resignation> resignationList = resignationDa.findAll(Resignation.class);
            Log log = new Log(Action.Search, "All Resignations", user);
            LogService.getLogService().save(log);
            return resignationList;
        }
    }
    //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
    public Resignation findByPersonnelCode(Long personnelCode, User user) throws Exception {
        try (CrudRepository<Resignation, Long> resignationDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("personnelCode", personnelCode);
            List<Resignation> resignations = resignationDa.executeQuery("resignation.findByPersonnelCode", params);
            Log log = new Log(Action.Search, resignations.toString(), user);
            LogService.getLogService().save(log);
            if (resignations.size() == 1) {
                return resignations.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------SELECT-BY-DATE------------------------------------------------------
    public List<Resignation> findByDate(LocalDate date, User user) throws Exception {
        try (CrudRepository<Resignation, Long> resignationDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("date", date);
            List<Resignation> resignations = resignationDa.executeQuery("resignation.findByDate", params);
            Log log = new Log(Action.Search, "FIND BY DATE", user);
            LogService.getLogService().save(log);
            return resignations;
        }
    }
}
