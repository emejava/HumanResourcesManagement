package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.repository.CrudRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DismissalService {
    //  ---------SINGLETON---------------------------------------------------------
    private static DismissalService dismissalService = new DismissalService();

    private DismissalService() {
    }

    public static DismissalService getDismissalService() {
        return dismissalService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Dismissal save(Dismissal dismissal, User user) throws Exception {
        try (CrudRepository<Dismissal, Long> dismissalDa = new CrudRepository<>()) {
            dismissal = dismissalDa.save(dismissal);
            Log log = new Log(Action.Insert, dismissal.toString(), user);
            LogService.getLogService().save(log);
            return dismissal;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Dismissal edit(Dismissal dismissal, User user) throws Exception {
        try (CrudRepository<Dismissal, Long> dismissalDa = new CrudRepository<>()) {
            dismissal = dismissalDa.edit(dismissal);
            Log log = new Log(Action.Update, dismissal.toString(), user);
            LogService.getLogService().save(log);
            return dismissal;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Dismissal> findAll(User user) throws Exception {
        try (CrudRepository<Dismissal, Long> dismissalDa = new CrudRepository<>()) {
            List<Dismissal> dismissalList = dismissalDa.findAll(Dismissal.class);
            Log log = new Log(Action.Search, "All Dismissals", user);
            LogService.getLogService().save(log);
            return dismissalList;
        }
    }
    //  ---------SELECT-BY-PERSONNEL-CODE-------------------------------------------
    public Dismissal findByPersonnelCode(Long personnelCode, User user) throws Exception {
        try (CrudRepository<Dismissal, Long> dismissalDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("personnelCode", personnelCode);
            List<Dismissal> dismissals = dismissalDa.executeQuery("dismissal.findByPersonnelCode", params);
            Log log = new Log(Action.Search, dismissals.toString(), user);
            LogService.getLogService().save(log);
            if (dismissals.size() == 1) {
                return dismissals.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------SELECT-BY-DATE------------------------------------------------------
    public List<Dismissal> findByDate(LocalDate date, User user) throws Exception {
        try (CrudRepository<Dismissal, Long> dismissalDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("date", date);
            List<Dismissal> dismissals = dismissalDa.executeQuery("dismissal.findByDate", params);
            Log log = new Log(Action.Search, "FIND BY DATE", user);
            LogService.getLogService().save(log);
            return dismissals;
        }
    }

}