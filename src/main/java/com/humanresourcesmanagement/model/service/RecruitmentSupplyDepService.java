package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.RecruitmentSupplyDep;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.repository.CrudRepository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecruitmentSupplyDepService implements ServiceImpl<RecruitmentSupplyDep , Long> {

    private static RecruitmentSupplyDepService recruitmentSupplyDepService = new RecruitmentSupplyDepService();

    public RecruitmentSupplyDepService() {
    }

    public static RecruitmentSupplyDepService getSupplyDepService() {
        return recruitmentSupplyDepService;
    }

    @Override
    public RecruitmentSupplyDep save(RecruitmentSupplyDep recruitmentSupplyDep) throws Exception {
        CrudRepository<RecruitmentSupplyDep , Long> repository = new CrudRepository<>();
        recruitmentSupplyDep = repository.save(recruitmentSupplyDep);
        Log log = new Log(Action.Insert , recruitmentSupplyDep.toString(),null);
        LogService.getLogService().save(log);
        return recruitmentSupplyDep;
    }

    @Override
    public RecruitmentSupplyDep edit(RecruitmentSupplyDep recruitmentSupplyDep) throws Exception {
        CrudRepository<RecruitmentSupplyDep , Long> repository = new CrudRepository<>();
        recruitmentSupplyDep = repository.edit(recruitmentSupplyDep);
        Log log = new Log(Action.Update , recruitmentSupplyDep.toString(),null);
        LogService.getLogService().save(log);
        return recruitmentSupplyDep;
    }

    @Override
    public RecruitmentSupplyDep deactivate(Long id) throws Exception {
        CrudRepository<RecruitmentSupplyDep , Long> repository = new CrudRepository<>();
        RecruitmentSupplyDep recruitmentSupplyDep = repository.deactivate(RecruitmentSupplyDep.class , id);
        Log log = new Log(Action.Change_Status ,recruitmentSupplyDep.toString(),null);
        LogService.getLogService().save(log);
        return recruitmentSupplyDep;
    }

    @Override
    public RecruitmentSupplyDep fire(Long id) throws Exception {
        return null;
    }

    @Override
    public RecruitmentSupplyDep resign(Long id) throws Exception {
        return null;
    }

    @Override
    public List<RecruitmentSupplyDep> findAll() throws Exception {
        CrudRepository<RecruitmentSupplyDep , Long> repository = new CrudRepository<>();
        List<RecruitmentSupplyDep> recruitmentSupplyDepList = repository.findAll(RecruitmentSupplyDep.class);
        Log log = new Log(Action.Search , "Select All",null);
        LogService.getLogService().save(log);
        return recruitmentSupplyDepList;
    }

    @Override
    public RecruitmentSupplyDep findById(Long id) throws Exception {
        CrudRepository<RecruitmentSupplyDep , Long> repository = new CrudRepository<>();
        RecruitmentSupplyDep recruitmentSupplyDep = repository.findById(RecruitmentSupplyDep.class , id);
        Log log = new Log(Action.Search ,recruitmentSupplyDep.toString(),null);
        LogService.getLogService().save(log);
        return recruitmentSupplyDep;
    }
    @Override
    public List<RecruitmentSupplyDep> executeQuery(String namedQuery, Map<String, Object> params) throws Exception {
        CrudRepository<RecruitmentSupplyDep ,Long> crudRepository = new CrudRepository<>();
        return crudRepository.executeQuery(namedQuery,params);
    }
//NamedQuery
    public List<RecruitmentSupplyDep> findByPersonId(Long id) throws Exception{
        Map<String , Object> params = new HashMap<>();
        params.put("person" , id);
        return executeQuery("findByPersonId",params);
    }
    public List<RecruitmentSupplyDep> findByFieldOfStudy(String fieldOfStudy) throws Exception{
        Map<String , Object> params = new HashMap<>();
        params.put("fieldOfStudy" ,fieldOfStudy);
        return executeQuery("findByFieldOfStudy",params);
    }
    public List<RecruitmentSupplyDep> findByUniversity(String university) throws Exception{
        Map<String , Object> params = new HashMap<>();
        params.put("university" ,university);
        return executeQuery("findByUniversity",params);
    }
}
