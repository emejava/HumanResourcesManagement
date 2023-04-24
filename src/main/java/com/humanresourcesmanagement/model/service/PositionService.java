package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.*;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionService {
    //  ---------SINGLETON---------------------------------------------------------
    private static PositionService positionService = new PositionService();

    private PositionService() {
    }

    public static PositionService getPositionService() {
        return positionService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Position save(Position position, User user) throws Exception {
        try(CrudRepository<Position, Long> positionDa = new CrudRepository<>()) {
            position = positionDa.save(position);
            Log log = new Log(Action.Insert, position.toString(), user);
            LogService.getLogService().save(log);
            return position;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Position edit(Position position,User user) throws Exception {
        try(CrudRepository<Position, Long> positionDa = new CrudRepository<>()) {
            position = positionDa.edit(position);
            Log log = new Log(Action.Update, position.toString(), user);
            LogService.getLogService().save(log);
            return position;
        }
    }

    //  ----------DELETE------------------------------------------------------------
    public Position delete(Long id,User user) throws Exception {
        try(CrudRepository<Position, Long> positionDa = new CrudRepository<>()) {
            Position position = positionDa.delete(Position.class, id);
            Log log = new Log(Action.Delete, position.toString(), user);
            LogService.getLogService().save(log);
            return position;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Position deactivate(Long id,User user) throws Exception {
        try(CrudRepository<Position, Long> positionDa = new CrudRepository<>()) {
            Position position = positionDa.findById(Position.class, id);
            position.setStatus(Status.Inactive);
            positionDa.edit(position);
            Log log = new Log(Action.Deactivate, position.toString(), user);
            LogService.getLogService().save(log);
            return position;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Position> findAll(User user) throws Exception {
        try(CrudRepository<Position, Long> positionDa = new CrudRepository<>()) {
            List<Position> positions = positionDa.findAll(Position.class);
            Log log = new Log(Action.Search, "All Positions", user);
            LogService.getLogService().save(log);
            return positions;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<Position> findAllActive(User user) throws Exception {
        try(CrudRepository<Position, Long> positionDa = new CrudRepository<>()) {
            Map<String,Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("status",status);
            List<Position> positions = positionDa.executeQuery("position.findAllActive", params);
            Log log = new Log(Action.Search, "All Positions", user);
            LogService.getLogService().save(log);
            return positions;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Position findById(Long id,User user) throws Exception {
        try(CrudRepository<Position, Long> positionDa = new CrudRepository<>()) {
            Position position = positionDa.findById(Position.class,id);
            Log log = new Log(Action.Search, position.toString(), user);
            LogService.getLogService().save(log);
            return position;
        }
    }

    //  ---------SELECT-BY-NAME--------------------------------------------
    public Position findByName(String name, User user) throws Exception {
        try (CrudRepository<Position, Long> positionDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("name", name);
            List<Position> positions = positionDa.executeQuery("unit.findByName", params);
            Log log = new Log(Action.Search, positions.toString(), user);
            LogService.getLogService().save(log);
            if (positions.size() == 1) {
                return positions.get(0);
            } else {
                return null;
            }
        }
    }
}
