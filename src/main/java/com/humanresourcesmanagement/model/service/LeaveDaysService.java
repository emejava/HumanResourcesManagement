package com.humanresourcesmanagement.model.service;


import com.humanresourcesmanagement.model.entity.LeaveDays;
import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.Payment;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaveDaysService {
    //  ---------SINGLETON---------------------------------------------------------
    private static LeaveDaysService leaveDaysService = new LeaveDaysService();

    public LeaveDaysService() {
    }

    public static LeaveDaysService getLeaveDaysService() {
        return leaveDaysService;
    }
    //  ---------INSERT-DATA--------------------------------------------------------
    public LeaveDays save(LeaveDays leaveDays, User user) throws Exception {
        try (CrudRepository<LeaveDays, Long> leaveDaysDa = new CrudRepository<>()) {
            leaveDays = leaveDaysDa.save(leaveDays);
            Log log = new Log(Action.Insert, leaveDays.toString(), user);
            LogService.getLogService().save(log);
            return leaveDays;
        }
    }
    //  ---------UPDATE-DATA--------------------------------------------------------
    public LeaveDays edit(LeaveDays leaveDays, User user) throws Exception {
        try (CrudRepository<LeaveDays, Long> leaveDaysDa = new CrudRepository<>()) {
            leaveDays = leaveDaysDa.edit(leaveDays);
            Log log = new Log(Action.Update, leaveDays.toString(), user);
            LogService.getLogService().save(log);
            return leaveDays;
        }
    }
    //  ---------DELETE--------------------------------------------------------
    public LeaveDays delete(Long id, User user) throws Exception {
        try (CrudRepository<LeaveDays, Long> leaveDaysDa = new CrudRepository<>()) {
            LeaveDays leaveDays = leaveDaysDa.delete(LeaveDays.class , id);
            Log log = new Log(Action.Delete, leaveDays.toString(), user);
            LogService.getLogService().save(log);
            return leaveDays;
        }
    }
    //  ---------SELECT-ALL--------------------------------------------------------
    public List<LeaveDays> findAll(User user) throws Exception {
        try (CrudRepository<LeaveDays, Long> leaveDaysDa = new CrudRepository<>()) {
            List<LeaveDays> leaveDaysList = leaveDaysDa.findAll(LeaveDays.class);
            Log log = new Log(Action.Search,"All Leave Days Request", user);
            LogService.getLogService().save(log);
            return leaveDaysList;
        }
    }
    //  ---------SELECT-BY-ID-------------------------------------------------------
    public LeaveDays findById(Long id , User user) throws Exception {
        try (CrudRepository<LeaveDays, Long> leaveDaysDa = new CrudRepository<>()){
            LeaveDays leaveDays = leaveDaysDa.findById(LeaveDays.class , id);
            Log log = new Log(Action.SearchBy,leaveDays.toString(), user);
            LogService.getLogService().save(log);
            return leaveDays;
        }
    }
    //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------------------
    public List<LeaveDays> findByPersonnelCode(String personnelCode, User user) throws Exception {
        try (CrudRepository<LeaveDays, Long> leaveDaysDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("personnelCode", personnelCode);
            List<LeaveDays> allLeaveDaysOf = leaveDaysDa.executeQuery("person.findByPersonnelCode", params);
            Log log = new Log(Action.SearchBy, "All Leave Days Request Of " + personnelCode, user);
            LogService.getLogService().save(log);
            return allLeaveDaysOf;
        }
    }
    //  ---------SELECT-BY-ACCEPTED-------------------------------------------------------
    public List<LeaveDays> findByAccepted(Boolean accepted, User user) throws Exception {
        try (CrudRepository<LeaveDays, Long> leaveDaysDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("accepted", accepted);
            List<LeaveDays> allAcceptedLeaveDays = leaveDaysDa.executeQuery("leave.findByAccepted", params);
            Log log = new Log(Action.SearchBy, "All Accepted Leave Days", user);
            LogService.getLogService().save(log);
            return allAcceptedLeaveDays;
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE-IN-TIME-PERIOD----------------------------------
    public List<LeaveDays> findByPersonnelCodeAndBetweenTime(
            Long personnelCode, LocalDate from, LocalDate till , User user) throws Exception {
        try (CrudRepository<LeaveDays, Long> leaveDaysDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("personnelCode", personnelCode);
            params.put("from", from);
            params.put("till", till);
            params.put("accepted",true);
            List<LeaveDays> leaveDaysList =
                    leaveDaysDa.executeQuery("leaveDays.findByPersonnelCodeAndBetweenTime", params);
            Log log = new Log(Action.Search, leaveDaysList.toString(), user);
            LogService.getLogService().save(log);
            return leaveDaysList;
        }
    }
}
