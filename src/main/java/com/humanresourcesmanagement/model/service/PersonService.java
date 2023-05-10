package com.humanresourcesmanagement.model.service;


import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService {
    //  ---------SINGLETON---------------------------------------------------------
    private static PersonService personService = new PersonService();

    private PersonService() {
    }

    public static PersonService getPersonService() {
        return personService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Person save(Person person, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            person = personDa.save(person);
            Log log = new Log(Action.Insert, person.toString(), user);
            LogService.getLogService().save(log);
            return person;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Person edit(Person person, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            person = personDa.edit(person);
            Log log = new Log(Action.Update, person.toString(), user);
            LogService.getLogService().save(log);
            return person;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public Person delete(Long id, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            Person person = personDa.delete(Person.class, id);
            Log log = new Log(Action.Delete, person.toString(), user);
            LogService.getLogService().save(log);
            return person;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Person deactivate(Long id, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            Person person = personDa.findById(Person.class, id);
            person.setStatus(Status.Inactive);
            personDa.edit(person);
            Log log = new Log(Action.Deactivate, person.toString(), user);
            LogService.getLogService().save(log);
            return person;
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public Person activate(Long id, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            Person person = personDa.findById(Person.class, id);
            person.setStatus(Status.Active);
            personDa.edit(person);
            Log log = new Log(Action.Activate, person.toString(), user);
            LogService.getLogService().save(log);
            return person;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Person> findAll(User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            List<Person> personList = personDa.findAll(Person.class);
            Log log = new Log(Action.Search, "All Persons", user);
            LogService.getLogService().save(log);
            return personList;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<Person> findAllActive(User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            Map<String,Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("status",status);
            List<Person> personList = personDa.executeQuery("person.findAllActive", params);
            Log log = new Log(Action.Search, "All Active Persons", user);
            LogService.getLogService().save(log);
            return personList;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Person findById(Long id, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            Person person = personDa.findById(Person.class, id);
            Log log = new Log(Action.Search, person.toString(), user);
            LogService.getLogService().save(log);
            return person;
        }
    }

    //  ---------SELECT-BY-FIRSTNAME-AND-LASTNAME-------------------------------------
    public Person findByFirstAndLastName(String firstName, String lastName, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("firstName", firstName);
            params.put("lastName", firstName);
            List<Person> persons = personDa.executeQuery("person.findByFirstAndLastName", params);
            Log log = new Log(Action.Search, "All Active Persons", user);
            LogService.getLogService().save(log);
            if (persons.size() == 1) {
                return persons.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------SELECT-BY-NATIONAL-CODE----------------------------------------------
    public Person findByNationalCode(String nationalCode, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("nationalCode", nationalCode);
            List<Person> persons = personDa.executeQuery("person.findByNationalCode", params);
            Log log = new Log(Action.Search, persons.toString(), user);
            LogService.getLogService().save(log);
            if (persons.size() == 1) {
                return persons.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------SELECT-BY-PHONE-NO---------------------------------------------------
    public Person findByPhoneNo(String phoneNo, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("phoneNo", phoneNo);
            List<Person> persons = personDa.executeQuery("person.findByPhoneNo", params);
            Log log = new Log(Action.Search, persons.toString(), user);
            LogService.getLogService().save(log);
            if (persons.size() == 1) {
                return persons.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------SELECT-BY-BIRTHDAY---------------------------------------------------
    public Person findByBirthDay(Timestamp birthDay, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("birthDay", birthDay);
            List<Person> persons = personDa.executeQuery("person.findByBirthday", params);
            Log log = new Log(Action.Search, persons.toString(), user);
            LogService.getLogService().save(log);
            if (persons.size() == 1) {
                return persons.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE---------------------------------------------
    public Person findByPersonnelCode(Long personnelCode, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("personnelCode", personnelCode);
            List<Person> persons = personDa.executeQuery("person.findByPersonnelCode", params);
            Log log = new Log(Action.Search, persons.toString(), user);
            LogService.getLogService().save(log);
            if (persons.size() == 1) {
                return persons.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------CHANGE-STATUS---------------------------------------------------------
    public Person changeStatus(Person person,Status status, User user) throws Exception {
        try (CrudRepository<Person, Long> personDa = new CrudRepository<>()) {
            person.setStatus(status);
            personDa.edit(person);
            Log log = new Log(Action.Update, person.toString(), user);
            LogService.getLogService().save(log);
            return person;
        }
    }


    //  ---------RUN-NAMED-QUERIES----------------------------------------------------
    public List<Person> executeQuery(String namedQuery, Map<String, Object> params) throws Exception {
        return null;
    }
}
