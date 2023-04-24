package com.humanresourcesmanagement.model.service;


import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.*;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitService{
    //  ---------SINGLETON---------------------------------------------------------
    private static UnitService unitService = new UnitService();

    private UnitService() {
    }

    public static UnitService getUnitService() {
        return unitService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Unit save(Unit unit, User user) throws Exception {
        try (CrudRepository<Unit, Long> unitDa = new CrudRepository<>()) {
            unit = unitDa.save(unit);
            Log log = new Log(Action.Insert, unit.toString(), user);
            LogService.getLogService().save(log);
            return unit;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Unit edit(Unit unit, User user) throws Exception {
        try (CrudRepository<Unit, Long> unitDa = new CrudRepository<>()) {
            unit = unitDa.edit(unit);
            Log log = new Log(Action.Update, unit.toString(), user);
            LogService.getLogService().save(log);
            return unit;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public Unit delete(Long id, User user) throws Exception {
        try (CrudRepository<Unit, Long> unitDa = new CrudRepository<>()) {
            Unit unit = unitDa.delete(Unit.class, id);
            Log log = new Log(Action.Delete, unit.toString(), user);
            LogService.getLogService().save(log);
            return unit;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Unit deactivate(Long id, User user) throws Exception {
        try (CrudRepository<Unit, Long> unitDa = new CrudRepository<>()) {
            Unit unit = unitDa.findById(Unit.class, id);
            unit.setStatus(Status.Inactive);
            unitDa.edit(unit);
            Log log = new Log(Action.Deactivate, unit.toString(), user);
            LogService.getLogService().save(log);
            return unit;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Unit> findAll(User user) throws Exception {
        try (CrudRepository<Unit, Long> unitDa = new CrudRepository<>()) {
            List<Unit> unitList = unitDa.findAll(Unit.class);
            Log log = new Log(Action.Search, "All Units", user);
            LogService.getLogService().save(log);
            return unitList;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<Unit> findAllActive(User user) throws Exception {
        try (CrudRepository<Unit, Long> unitDa = new CrudRepository<>()) {
            Map<String,Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("status",status);
            List<Unit> unitList = unitDa.executeQuery("unit.findAllActive", params);
            Log log = new Log(Action.Search, "All Active Units", user);
            LogService.getLogService().save(log);
            return unitList;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Unit findById(Long id, User user) throws Exception {
        try (CrudRepository<Unit, Long> unitDa = new CrudRepository<>()) {
            Unit unit = unitDa.findById(Unit.class, id);
            Log log = new Log(Action.Search, unit.toString(), user);
            LogService.getLogService().save(log);
            return unit;
        }
    }

    //  ---------SELECT-BY-NAME--------------------------------------------
    public Unit findByName(String name, User user) throws Exception {
        try (CrudRepository<Unit, Long> unitDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("name", name);
            List<Unit> units = unitDa.executeQuery("unit.findByName", params);
            Log log = new Log(Action.Search, units.toString(), user);
            LogService.getLogService().save(log);
            if (units.size() == 1) {
                return units.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------SELECT-BY-CITY--------------------------------------------
    public List<Unit> findByCity(String city, User user) throws Exception {
        try (CrudRepository<Unit, Long> unitDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("city", city);
            List<Unit> units = unitDa.executeQuery("unit.findByCity", params);
            Log log = new Log(Action.Search, "FIND BY CITY", user);
            LogService.getLogService().save(log);
            return units;
        }
    }


}
