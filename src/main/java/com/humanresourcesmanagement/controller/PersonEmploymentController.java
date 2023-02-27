package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.entity.PersonEmployment;
import com.humanresourcesmanagement.model.entity.enums.EmploymentType;
import com.humanresourcesmanagement.model.entity.enums.Position;
import com.humanresourcesmanagement.model.entity.enums.ShiftWork;
import com.humanresourcesmanagement.model.service.PersonEmploymentService;

import java.sql.Timestamp;
import java.util.List;

public class PersonEmploymentController {
    private static PersonEmploymentController personEmploymentController = new PersonEmploymentController();

    private PersonEmploymentController() {
    }

    public static PersonEmploymentController getPersonEmploymentController() {
        return personEmploymentController;
    }

    public String save(Person person, EmploymentType employmentType, Unit unit, List<Duty> duties,
                     Position position, Timestamp startWorkingDate, String workingTimePerDay, ShiftWork shiftWork) {
        try {
            Long personnelCode = Long.valueOf(String.valueOf(unit.getId()) + String.valueOf(position.getId()) + String.valueOf(person.getId()));
            PersonEmployment personEmployment = new PersonEmployment(personnelCode, person, employmentType, unit, duties, position, startWorkingDate, workingTimePerDay, shiftWork);
           return PersonEmploymentService.getPersonEmploymentService().save(personEmployment).toString();
        } catch (Exception e) {
           return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }

    public String edit(Long personnelCode, Person person, EmploymentType employmentType, Unit unit, List<Duty> duties,
                     Position position, Timestamp startWorkingDate, String workingTimePerDay, ShiftWork shiftWork) {
        try {
            PersonEmployment personEmployment = new PersonEmployment(personnelCode, person, employmentType, unit, duties, position, startWorkingDate, workingTimePerDay, shiftWork);
            return PersonEmploymentService.getPersonEmploymentService().edit(personEmployment).toString();
        } catch (Exception e) {
           return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }

    public String deactivate(Long personnelCode) {
        try {
            PersonEmployment personEmployment = new PersonEmployment();
           return PersonEmploymentService.getPersonEmploymentService().deactivate(personnelCode).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }

    }

    public String findAll() {
        try {
            List<PersonEmployment> personEmploymentList = PersonEmploymentService.getPersonEmploymentService().findAll();
            return personEmploymentList.toString();
        } catch (Exception e) {
           return ExceptionWrapper.getExceptionWrapper(e).toString();
        }

    }
}
