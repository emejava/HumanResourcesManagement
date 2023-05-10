package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.Recruitment;
import com.humanresourcesmanagement.model.entity.enums.Gender;
import com.humanresourcesmanagement.model.entity.enums.MaritalStatus;
import com.humanresourcesmanagement.model.entity.enums.ShiftWork;
import com.humanresourcesmanagement.model.service.RecruitmentService;
import com.humanresourcesmanagement.model.service.RecruitmentService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class RecruitmentController {

    //  ---------SINGLETON---------------------------------------------------------------
    private static final RecruitmentController recruitmentController = new RecruitmentController();

    private RecruitmentController() {
    }

    public static RecruitmentController getRecruitmentController() {
        return recruitmentController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            Person person,
            String education,
            String fieldOfStudy,
            String educationPlace,
            String workExperience,
            String lastJob,
            String lastJobExitReason,
            String lastJobAddress,
            String lastJobNo,
            String jobGoal,
            ShiftWork shiftWork,
            String requestedSalary,
            User user) {

        //  ---------CREATE-OBJECT-----------------
        Recruitment recruitment = new Recruitment(
                person,
                education,
                fieldOfStudy,
                educationPlace,
                workExperience,
                lastJob,
                lastJobExitReason,
                lastJobAddress,
                lastJobNo,
                jobGoal,
                shiftWork,
                requestedSalary
        );
        //  ---------VALIDATING-DATA---------------
        Map<String, String> recruitmentErrors = Validation.getValidation().doValidation(recruitment);
        if (recruitmentErrors != null) {
            return new Gson().toJson(recruitmentErrors);
        } else {
            try {
                return RecruitmentService.getRecruitmentService().save(recruitment, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            Person person,
            String education,
            String fieldOfStudy,
            String educationPlace,
            String workExperience,
            String lastJob,
            String lastJobExitReason,
            String lastJobAddress,
            String lastJobNo,
            String jobGoal,
            ShiftWork shiftWork,
            String requestedSalary,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Recruitment recruitment = new Recruitment(
                id,
                person,
                education,
                fieldOfStudy,
                educationPlace,
                workExperience,
                lastJob,
                lastJobExitReason,
                lastJobAddress,
                lastJobNo,
                jobGoal,
                shiftWork,
                requestedSalary);
        //  ---------VALIDATING-DATA---------------
        Map<String, String> recruitmentErrors = Validation.getValidation().doValidation(recruitment);
        if (recruitmentErrors != null) {
            return new Gson().toJson(recruitmentErrors);
        } else {
            try {
                return RecruitmentService.getRecruitmentService().edit(recruitment, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String delete(Long id, User user) {
        try {
            return RecruitmentService.getRecruitmentService().delete(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long id, User user) {
        try {
            return RecruitmentService.getRecruitmentService().deactivate(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public String activate(Long id, User user) {
        try {
            return RecruitmentService.getRecruitmentService().activate(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return RecruitmentService.getRecruitmentService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public String findById(Long id, User user) {
        try {
            return RecruitmentService.getRecruitmentService().findById(id, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-FIELD-OF-STUDY-------------------------------------------
    public String findByFieldOfStudy(String fieldOfStudy, User user) {
        try {
            return RecruitmentService.getRecruitmentService().findByFieldOfStudy(fieldOfStudy, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-UNIVERSITY------------------------------------------------
    public String findByUniversity(String university, User user) {
        try {
            return RecruitmentService.getRecruitmentService().findByUniversity(university, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
