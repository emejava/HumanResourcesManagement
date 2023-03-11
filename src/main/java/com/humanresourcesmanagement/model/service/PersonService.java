package com.humanresourcesmanagement.model.service;



import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService implements ServiceImpl<Person,Long>{
    private static  PersonService personService = new PersonService();

    private PersonService() {
    }

    public static PersonService getPersonService() {
        return personService;
    }


    @Override
    public Person save(Person person) throws Exception {
        CrudRepository<Person,Long> personDA = new CrudRepository<>();
        person = personDA.save(person);
        Log log =new Log(Action.Insert,person.toString(),  null);
        LogService.getLogService().save(log);
        return person;
    }

    @Override
    public Person edit(Person person) throws Exception {
        CrudRepository<Person,Long> userDA = new CrudRepository<>();
        return userDA.edit(person);
    }

    @Override
    public Person deactivate(Long id) throws Exception {
        CrudRepository<Person,Long> userDA = new CrudRepository<>();
        return userDA.deactivate(Person.class, id);
    }

    @Override
    public Person fire(Long id) throws Exception {
        return null;
    }

    @Override
    public Person resign(Long id) throws Exception {
        return null;
    }

    @Override
    public Person remove(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Person> findAll() throws Exception {
        CrudRepository<Person,Long> personDA = new CrudRepository<>();
        List<Person> personList  = personDA.findAll(Person.class);
        Log log =new Log(Action.Search,"SELECT ALL",  null);
        LogService.getLogService().save(log);
        return personList;
    }

    @Override
    public Person findById(Long id) throws Exception {
        CrudRepository<Person,Long> personDA = new CrudRepository<>();
        return personDA.findById(Person.class,id);
    }

    @Override
    public List<Person> executeQuery(String namedQuery, Map<String, Object> params) throws Exception {
        return null;
    }
//    @Override
//    public Person findByFirstName(String firstName) throws Exception {
//        CrudRepository<Person,String> personDA = new CrudRepository<>();
//        return personDA.findByFirstName(Person.class,firstName);
//    }
//
//    @Override
//    public Person findByLastName(String lastName) throws Exception {
//        CrudRepository<Person,String> personDA = new CrudRepository<>();
//        return personDA.selectByLastName(Person.class,lastName);
//    }
//
//    @Override
//    public Person findByNationalCode(String nationalCode) throws Exception {
//        CrudRepository<Person,String> personDA = new CrudRepository<>();
//        return personDA.selectByNationalCode(Person.class,nationalCode);
//    }
//
//    @Override
//    public Person findByPhoneNo(String phoneNo) throws Exception {
//        CrudRepository<Person,String> personDA = new CrudRepository<>();
//        return personDA.selectByPhoneNo(Person.class,phoneNo);
//    }
//
//    @Override
//    public Person findByBirthDay(LocalDate birthDay) throws Exception {
//        CrudRepository<Person,LocalDate> personDA = new CrudRepository<>();
//        return personDA.selectByBirthDay(Person.class,birthDay);
//    }



}
