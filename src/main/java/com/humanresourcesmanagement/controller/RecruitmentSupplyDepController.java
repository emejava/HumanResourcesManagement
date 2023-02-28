package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.entity.RecruitmentSupplyDep;
import com.humanresourcesmanagement.model.service.RecruitmentSupplyDepService;

import java.util.List;

public class RecruitmentSupplyDepController {
    private static RecruitmentSupplyDepController recruitmentSupplyDepController = new RecruitmentSupplyDepController();

    public RecruitmentSupplyDepController() {
    }

    public static RecruitmentSupplyDepController getRecruitmentSupplyDepController() {
        return recruitmentSupplyDepController;
    }
    public String save(Person person, String education, String fieldOfStudy, int ageCondition, String university,
                       String workExperience, String lastJob, String lastJobExitReason, String lastJobAddress,
                       String lastJobNo, String jobObjective, String requestedSalary) {
            if (ageCondition > 20 & ageCondition < 35) {
            try {
                RecruitmentSupplyDep recruitmentSupplyDep = new RecruitmentSupplyDep(person, education, fieldOfStudy, ageCondition, university, workExperience, lastJob, lastJobExitReason, lastJobAddress, lastJobNo, jobObjective, requestedSalary);
                return RecruitmentSupplyDepService.getSupplyDepService().save(recruitmentSupplyDep).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper(e).toString();
            }
        } else {
                return "Invalid Age : Please Try Again\n";
        }
    }

    public String edit(long id, Person person, String education, String fieldOfStudy, int ageCondition, String university,
                       String workExperience, String lastJob, String lastJobExitReason, String lastJobAddress,
                       String lastJobNo, String jobObjective, String requestedSalary) {
        try {
            RecruitmentSupplyDep recruitmentSupplyDep = new RecruitmentSupplyDep(id, person, education, fieldOfStudy,
                    ageCondition, university, workExperience, lastJob, lastJobExitReason, lastJobAddress, lastJobNo, jobObjective, requestedSalary);
            return RecruitmentSupplyDepService.getSupplyDepService().edit(recruitmentSupplyDep).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }
    public String remove(long id){
        try {
            return RecruitmentSupplyDepService.getSupplyDepService().deactivate(id).toString();
        }catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }
    public String findAll(){
        try {
            List<RecruitmentSupplyDep> recruitmentSupplyDepList = RecruitmentSupplyDepService.getSupplyDepService().findAll();
            return recruitmentSupplyDepList.toString();
        }catch (Exception e){
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }
    public String findById(long id) {
        try {
            return RecruitmentSupplyDepService.getSupplyDepService().findById(id).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }
    public String findByPerson_id(long id){
        try {
            return RecruitmentSupplyDepService.getSupplyDepService().findByPerson_id(id).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }
    public String findByFieldOfStudy(String fieldOfStudy){
        try {
            return RecruitmentSupplyDepService.getSupplyDepService().findByFieldOfStudy(fieldOfStudy).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }
    public String findByUniversity(String university){
        try {
            return RecruitmentSupplyDepService.getSupplyDepService().findByUniversity(university).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }
}
