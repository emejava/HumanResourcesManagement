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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecruitmentController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();

    //  ---------SINGLETON---------------------------------------------------------------
    private static final RecruitmentController recruitmentController = new RecruitmentController();

    private RecruitmentController() {
    }

    public static RecruitmentController getRecruitmentController() {
        return recruitmentController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object> save(
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

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(recruitment);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, RecruitmentService.getRecruitmentService().save(recruitment, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean,Object> edit(
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

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(recruitment);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, RecruitmentService.getRecruitmentService().edit(recruitment, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Map<Boolean,Object> delete(Long id, User user) {
        result.clear();
        try {
            result.put(true, RecruitmentService.getRecruitmentService().delete(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Map<Boolean,Object> deactivate(Long id, User user) {
        result.clear();
        try {
            result.put(true, RecruitmentService.getRecruitmentService().deactivate(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public Map<Boolean,Object> activate(Long id, User user) {
        result.clear();
        try {
            result.put(true, RecruitmentService.getRecruitmentService().activate(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
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
    public Map<Boolean,Object> findById(Long id, User user) {
        result.clear();
        try {
            result.put(true, RecruitmentService.getRecruitmentService().findById(id, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-FIELD-OF-STUDY-------------------------------------------
    public Map<Boolean,Object> findByFieldOfStudy(String fieldOfStudy, User user) {
        result.clear();
        try {
            result.put(true, RecruitmentService.getRecruitmentService().findByFieldOfStudy(fieldOfStudy, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-UNIVERSITY------------------------------------------------
    public Map<Boolean,Object> findByUniversity(String university, User user) {
        result.clear();
        try {
            result.put(true, RecruitmentService.getRecruitmentService().findByUniversity(university, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
