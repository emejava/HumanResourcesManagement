package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.PersonEmployment;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public class PersonEmploymentService implements ServiceImpl<PersonEmployment, Long> {

    private static PersonEmploymentService personEmploymentService = new PersonEmploymentService();

    private PersonEmploymentService() {
    }

    public static PersonEmploymentService getPersonEmploymentService() {
        return personEmploymentService;
    }

    @Override
    public PersonEmployment save(PersonEmployment personEmployment) throws Exception {
        CrudRepository<PersonEmployment, Long> personEmploymentDa = new CrudRepository<>();
        personEmployment = personEmploymentDa.save(personEmployment);
        Log log = new Log(Action.Insert,personEmployment.toString(),null);
        LogService.getLogService().save(log);
        return personEmployment;

    }

    @Override
    public PersonEmployment edit(PersonEmployment personEmployment) throws Exception {
        CrudRepository<PersonEmployment, Long> personEmploymentDa = new CrudRepository<>();
        personEmployment = personEmploymentDa.edit(personEmployment);
        Log log = new Log(Action.Update,personEmployment.toString(),null);
        LogService.getLogService().save(log);
        return personEmployment;
    }

    @Override
    public PersonEmployment deactivate(Long id) throws Exception {
        CrudRepository<PersonEmployment, Long> personEmploymentDa = new CrudRepository<>();
        PersonEmployment personEmployment = personEmploymentDa.deactivate(PersonEmployment.class,id);
        Log log = new Log(Action.Change_Status,personEmployment.toString(),null);
        LogService.getLogService().save(log);
        return personEmployment;
    }

    @Override
    public PersonEmployment fire(Long id) throws Exception {
        return null;
    }

    @Override
    public PersonEmployment resign(Long id) throws Exception {
        return null;
    }


    @Override
    public List<PersonEmployment> findAll() throws Exception {
        CrudRepository<PersonEmployment, Long> personEmploymentDa = new CrudRepository<>();
        List<PersonEmployment> personEmploymentList = personEmploymentDa.findAll(PersonEmployment.class);
        Log log = new Log(Action.Search,"Select All",null);
        LogService.getLogService().save(log);
        return personEmploymentList;
    }

    @Override
    public PersonEmployment findById(Long id) throws Exception {
        CrudRepository<PersonEmployment, Long> personEmploymentDa = new CrudRepository<>();
        PersonEmployment personEmployment = personEmploymentDa.findById(PersonEmployment.class,id);
        Log log = new Log(Action.Search,personEmployment.toString(),null);
        LogService.getLogService().save(log);
        return personEmployment;
    }

    @Override
    public List<PersonEmployment> executeQuery(String namedQuery, Map<String, Object> params) throws Exception {
        return null;
    }
}