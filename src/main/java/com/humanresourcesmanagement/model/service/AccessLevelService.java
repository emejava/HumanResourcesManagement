package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.*;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.List;

public class AccessLevelService {
    //  ---------SINGLETON---------------------------------------------------------
    private static AccessLevelService accessLevelService = new AccessLevelService();

    private AccessLevelService() {
    }

    public static AccessLevelService getAccessLevelService() {
        return accessLevelService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public AccessLevel save(AccessLevel accessLevel, User user) throws Exception {
        try (CrudRepository<AccessLevel, Long> accessLevelDa = new CrudRepository<>()) {
            accessLevel = accessLevelDa.save(accessLevel);
            Log log = new Log(Action.Insert, accessLevel.toString(), user);
            LogService.getLogService().save(log);
            return accessLevel;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public AccessLevel edit(AccessLevel accessLevel, User user) throws Exception {
        try (CrudRepository<AccessLevel, Long> accessLevelDa = new CrudRepository<>()) {
            accessLevel = accessLevelDa.edit(accessLevel);
            Log log = new Log(Action.Update, accessLevel.toString(), user);
            LogService.getLogService().save(log);
            return accessLevel;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public AccessLevel delete(Long id, User user) throws Exception {
        try (CrudRepository<AccessLevel, Long> accessLevelDa = new CrudRepository<>()) {
            AccessLevel accessLevel = accessLevelDa.delete(AccessLevel.class, id);
            Log log = new Log(Action.Delete, accessLevel.toString(), user);
            LogService.getLogService().save(log);
            return accessLevel;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<AccessLevel> findAll(User user) throws Exception {
        try (CrudRepository<AccessLevel, Long> accessLevelDa = new CrudRepository<>()) {
            List<AccessLevel> accessLevelList = accessLevelDa.findAll(AccessLevel.class);
            Log log = new Log(Action.Search, "All AccessLevels", user);
            LogService.getLogService().save(log);
            return accessLevelList;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public AccessLevel findById(Long id, User user) throws Exception {
        try (CrudRepository<AccessLevel, Long> accessLevelDa = new CrudRepository<>()) {
            AccessLevel accessLevel = accessLevelDa.findById(AccessLevel.class, id);
            Log log = new Log(Action.Search, accessLevel.toString(), user);
            LogService.getLogService().save(log);
            return accessLevel;
        }
    }
}
