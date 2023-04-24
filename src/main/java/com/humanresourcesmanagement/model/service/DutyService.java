package com.humanresourcesmanagement.model.service;


import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.*;
import com.humanresourcesmanagement.model.repository.CrudRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DutyService{
    //  ---------SINGLETON---------------------------------------------------------
    private static DutyService dutyService = new DutyService();

    private DutyService() {
    }

    public static DutyService getDutyService() {
        return dutyService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Duty save(Duty duty, User user) throws Exception {
        try (CrudRepository<Duty, Long> dutyDa = new CrudRepository<>()) {
            duty = dutyDa.save(duty);
            Log log = new Log(Action.Insert, duty.toString(), user);
            LogService.getLogService().save(log);
            return duty;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Duty edit(Duty duty, User user) throws Exception {
        try (CrudRepository<Duty, Long> dutyDa = new CrudRepository<>()) {
            duty = dutyDa.edit(duty);
            Log log = new Log(Action.Update, duty.toString(), user);
            LogService.getLogService().save(log);
            return duty;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public Duty delete(Long id, User user) throws Exception {
        try (CrudRepository<Duty, Long> dutyDa = new CrudRepository<>()) {
            Duty duty = dutyDa.delete(Duty.class, id);
            Log log = new Log(Action.Delete, duty.toString(), user);
            LogService.getLogService().save(log);
            return duty;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Duty deactivate(Long id, User user) throws Exception {
        try (CrudRepository<Duty, Long> dutyDa = new CrudRepository<>()) {
            Duty duty = dutyDa.findById(Duty.class, id);
            duty.setStatus(Status.Inactive);
            dutyDa.edit(duty);
            Log log = new Log(Action.Deactivate, duty.toString(), user);
            LogService.getLogService().save(log);
            return duty;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Duty> findAll(User user) throws Exception {
        try (CrudRepository<Duty, Long> dutyDa = new CrudRepository<>()) {
            List<Duty> duties = dutyDa.findAll(Duty.class);
            Log log = new Log(Action.Search, "All duties", user);
            LogService.getLogService().save(log);
            return duties;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<Duty> findAllActive(User user) throws Exception {
        try (CrudRepository<Duty, Long> dutyDa = new CrudRepository<>()) {
            Map<String,Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("status",status);
            List<Duty> duties = dutyDa.executeQuery("duty.findAllActive", params);
            Log log = new Log(Action.Search, "All Active duties", user);
            LogService.getLogService().save(log);
            return duties;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Duty findById(Long id, User user) throws Exception {
        try (CrudRepository<Duty, Long> dutyDa = new CrudRepository<>()) {
            Duty duty = dutyDa.findById(Duty.class, id);
            Log log = new Log(Action.Search, duty.toString(), user);
            LogService.getLogService().save(log);
            return duty;
        }
    }

    //  ---------SELECT-BY-POSITION-------------------------------------------------
    public List<Duty> findByPosition(Position position, User user) throws Exception {
        try (CrudRepository<Duty, Long> dutyDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("position", position);
            params.put("status",status);
            List<Duty> duties = dutyDa.executeQuery("duty.findByPosition", params);
            Log log = new Log(Action.Search, "FIND BY Position", user);
            LogService.getLogService().save(log);
            return duties;
        }
    }
}
