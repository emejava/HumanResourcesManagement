package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.repository.CrudRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetirementService {
    //  ---------SINGLETON---------------------------------------------------------
    private static RetirementService retirementService = new RetirementService();

    private RetirementService() {
    }

    public static RetirementService getRetirementService() {
        return retirementService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Retirement save(Retirement retirement, User user) throws Exception {
        try (CrudRepository<Retirement, Long> retirementDa = new CrudRepository<>()) {
            retirement = retirementDa.save(retirement);
            Log log = new Log(Action.Insert, retirement.toString(), user);
            LogService.getLogService().save(log);
            return retirement;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Retirement edit(Retirement retirement, User user) throws Exception {
        try (CrudRepository<Retirement, Long> retirementDa = new CrudRepository<>()) {
            retirement = retirementDa.edit(retirement);
            Log log = new Log(Action.Update, retirement.toString(), user);
            LogService.getLogService().save(log);
            return retirement;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Retirement> findAll(User user) throws Exception {
        try (CrudRepository<Retirement, Long> retirementDa = new CrudRepository<>()) {
            List<Retirement> retirementList = retirementDa.findAll(Retirement.class);
            Log log = new Log(Action.Search, "All Retirements", user);
            LogService.getLogService().save(log);
            return retirementList;
        }
    }
    //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
    public Retirement findByPersonnelCode(Long personnelCode, User user) throws Exception {
        try (CrudRepository<Retirement, Long> retirementDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("personnelCode", personnelCode);
            List<Retirement> retirements = retirementDa.executeQuery("retirement.findByPersonnelCode", params);
            Log log = new Log(Action.Search, retirements.toString(), user);
            LogService.getLogService().save(log);
            if (retirements.size() == 1) {
                return retirements.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------SELECT-BY-DATE------------------------------------------------------
    public List<Retirement> findByDate(LocalDate date, User user) throws Exception {
        try (CrudRepository<Retirement, Long> retirementDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("date", date);
            List<Retirement> retirements = retirementDa.executeQuery("retirement.findByDate", params);
            Log log = new Log(Action.Search, "FIND BY DATE", user);
            LogService.getLogService().save(log);
            return retirements;
        }
    }
}
