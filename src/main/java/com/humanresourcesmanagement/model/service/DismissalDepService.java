package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.DismissalDep;
import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.repository.CrudRepository;
import java.util.List;
import java.util.Map;

public class DismissalDepService implements ServiceImpl<DismissalDep , Long> {


    private static DismissalDepService dismissalDepService = new  DismissalDepService();

    private DismissalDepService() {
    }

    public static DismissalDepService getDismissalDepService() { return dismissalDepService;}

    @Override
    public DismissalDep save(DismissalDep dismissalDep) throws Exception {
        CrudRepository<DismissalDep, Long> dismissalDepDa = new CrudRepository<>();
        dismissalDep = dismissalDepDa.save(dismissalDep);
        Log log = new Log(Action.Insert,dismissalDep.toString(),null);
        LogService.getLogService().save(log);
        return dismissalDep;
    }

    @Override
    public DismissalDep edit(DismissalDep dismissalDep) throws Exception {
        CrudRepository<DismissalDep, Long> dismissalDepDa = new CrudRepository<>();
        dismissalDep = dismissalDepDa.save(dismissalDep);
        Log log = new Log(Action.Update,dismissalDep.toString(),null);
        LogService.getLogService().save(log);
        return dismissalDep;
    }


    @Override
    public DismissalDep fire(Long id) throws Exception {
        return null;
    }

    @Override
    public DismissalDep resign(Long id) throws Exception {
        return null;
    }

    @Override
    public DismissalDep deactivate(Long id) throws Exception {
        CrudRepository<DismissalDep, Long> dismissalDepDa = new CrudRepository<>();
        DismissalDep dismissalDep = dismissalDepDa.deactivate(DismissalDep.class,id);
        Log log = new Log(Action.Change_Status,dismissalDep.toString(),null);
        LogService.getLogService().save(log);
        return dismissalDep;
    }

    @Override
    public List<DismissalDep> findAll() throws Exception {
        CrudRepository<DismissalDep, Long> dismissalDepDa = new CrudRepository<>();
        List<DismissalDep> dismissalDepList = dismissalDepDa.findAll(DismissalDep.class);
        Log log = new Log(Action.Search,"Select All",null);
        LogService.getLogService().save(log);
        return dismissalDepList;
    }

    @Override
    public DismissalDep findById(Long id) throws Exception {
        CrudRepository<DismissalDep, Long> dismissalDepDa = new CrudRepository<>();
        DismissalDep dismissalDep = dismissalDepDa.findById(DismissalDep.class,id);
        Log log = new Log(Action.Search,dismissalDep.toString(),null);
        LogService.getLogService().save(log);
        return dismissalDep;
    }

    @Override
    public List<DismissalDep> executeQuery(String namedQuery, Map<String, Object> params) throws Exception {
        return null;
    }
}