package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.*;
import com.humanresourcesmanagement.model.repository.CrudRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecruitmentService {
    //  ---------SINGLETON---------------------------------------------------------
    private static RecruitmentService recruitmentService = new RecruitmentService();

    private RecruitmentService() {
    }

    public static RecruitmentService getRecruitmentService() {
        return recruitmentService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Recruitment save(Recruitment recruitment, User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            recruitment = recruitmentDa.save(recruitment);
            Log log = new Log(Action.Insert, recruitment.toString(), user);
            LogService.getLogService().save(log);
            return recruitment;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Recruitment edit(Recruitment recruitment, User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            recruitment = recruitmentDa.edit(recruitment);
            Log log = new Log(Action.Update, recruitment.toString(), user);
            LogService.getLogService().save(log);
            return recruitment;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public Recruitment delete(Long id, User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            Recruitment recruitment = recruitmentDa.delete(Recruitment.class, id);
            Log log = new Log(Action.Delete, recruitment.toString(), user);
            LogService.getLogService().save(log);
            return recruitment;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Recruitment deactivate(Long id, User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            Recruitment recruitment = recruitmentDa.findById(Recruitment.class, id);
            recruitment.setStatus(Status.Inactive);
            recruitmentDa.edit(recruitment);
            Log log = new Log(Action.Deactivate, recruitment.toString(), user);
            LogService.getLogService().save(log);
            return recruitment;
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public Recruitment activate(Long id, User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            Recruitment recruitment = recruitmentDa.findById(Recruitment.class, id);
            recruitment.setStatus(Status.Active);
            recruitmentDa.edit(recruitment);
            Log log = new Log(Action.Activate, recruitment.toString(), user);
            LogService.getLogService().save(log);
            return recruitment;
        }
    }

    //  ---------REJECT-STATUS-------------------------------------------------------
    public Recruitment reject(Long id, User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            Recruitment recruitment = recruitmentDa.findById(Recruitment.class, id);
            recruitment.setStatus(Status.Rejected);
            recruitmentDa.edit(recruitment);
            Log log = new Log(Action.Reject, recruitment.toString(), user);
            LogService.getLogService().save(log);
            return recruitment;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Recruitment> findAll(User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            List<Recruitment> recruitmentList = recruitmentDa.findAll(Recruitment.class);
            Log log = new Log(Action.Search, "All Recruitments", user);
            LogService.getLogService().save(log);
            return recruitmentList;
        }
    }

    //  ---------SELECT-BY-STATUS------------------------------------------------
    public List<Recruitment> findByStatus(String status, User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("status",status);
            List<Recruitment> recruitments = recruitmentDa.executeQuery("recruitment.findByStatus", params);
            Log log = new Log(Action.Search, "University", user);
            LogService.getLogService().save(log);
            return recruitments;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Recruitment findById(Long id, User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            Recruitment recruitment = recruitmentDa.findById(Recruitment.class, id);
            Log log = new Log(Action.Search, recruitment.toString(), user);
            LogService.getLogService().save(log);
            return recruitment;
        }
    }

    //  ---------SELECT-BY-FIELD-OF-STUDY-------------------------------------------
    public List<Recruitment> findByFieldOfStudy(String fieldOfStudy, User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("fieldOfStudy", fieldOfStudy);
            List<Recruitment> recruitments = recruitmentDa.executeQuery("recruitment.findByFieldOfStudy", params);
            Log log = new Log(Action.Search, "Field Of Study", user);
            LogService.getLogService().save(log);
            return recruitments;
        }
    }

    //  ---------SELECT-BY-UNIVERSITY------------------------------------------------
    public List<Recruitment> findByUniversity(String university, User user) throws Exception {
        try (CrudRepository<Recruitment, Long> recruitmentDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("university", university);
            List<Recruitment> recruitments = recruitmentDa.executeQuery("recruitment.findByUniversity", params);
            Log log = new Log(Action.Search, "University", user);
            LogService.getLogService().save(log);
            return recruitments;
        }
    }


}
