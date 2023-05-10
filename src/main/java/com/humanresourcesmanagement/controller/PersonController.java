package com.humanresourcesmanagement.controller;


import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Gender;
import com.humanresourcesmanagement.model.entity.enums.MaritalStatus;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.service.PersonService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;

public class PersonController {

    //  ---------SINGLETON---------------------------------------------------------------
    private static final PersonController personController = new PersonController();

    private PersonController() {
    }

    public static PersonController getPersonController() {
        return personController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            String firstName,
            String lastName,
            String nationalCode,
            String birthCertificateCode,
            LocalDate birthday,
            Gender gender,
            Integer age,
            String father,
            String state,
            String city,
            String address,
            String postCode,
            MaritalStatus maritalStatus,
            String phoneNo,
            String landLineNo,
            Short children,
            String email,
            Attachment nationalCardPicture,
            Attachment birthCertificatePicture,
            User user) {

        //  ---------CREATE-OBJECT-----------------
        Person person = new Person(
                firstName,
                lastName,
                nationalCode,
                birthCertificateCode,
                birthday,
                gender,
                age,
                father,
                state,
                city,
                address,
                postCode,
                maritalStatus,
                phoneNo,
                landLineNo,
                children,
                email,
                nationalCardPicture,
                birthCertificatePicture
        );
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(person);
        if (errors != null) {
            return new Gson().toJson(errors);
        }else{
            try {
              Person newPerson = PersonService.getPersonService().save(person, user);
              return newPerson.getId().toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            String firstName,
            String lastName,
            String nationalCode,
            String birthCertificateCode,
            LocalDate birthday,
            Gender gender,
            Integer age,
            String father,
            String state,
            String city,
            String address,
            String postCode,
            MaritalStatus maritalStatus,
            String phoneNo,
            String landLineNo,
            Short children,
            String email,
            Attachment nationalCardPicture,
            Attachment birthCertificatePicture,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Person person = new Person(
                id,
                firstName,
                lastName,
                nationalCode,
                birthCertificateCode,
                birthday,
                gender,
                age,
                father,
                state,
                city,
                address,
                postCode,
                maritalStatus,
                phoneNo,
                landLineNo,
                children,
                email,
                nationalCardPicture,
                birthCertificatePicture);
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(person);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return PersonService.getPersonService().edit(person, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String delete(Long personnelCode, User user) {
        try {
            return PersonService.getPersonService().delete(personnelCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long personnelCode, User user) {
        try {
            return PersonService.getPersonService().deactivate(personnelCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public String activate(Long personnelCode, User user) {
        try {
            return PersonService.getPersonService().activate(personnelCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return PersonService.getPersonService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return PersonService.getPersonService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Person findById(Long id, User user) {
        try {
            return PersonService.getPersonService().findById(id, user);
        } catch (Exception e) {
            return null; // TODO: How return error with returning object
        }
    }

    //  ---------SELECT-BY-FIRSTNAME-AND-LASTNAME-------------------------------------
    public Person findByFirstAndLastName(String firstName, String lastName, User user) {
        try {
            return PersonService.getPersonService().findByFirstAndLastName(firstName, lastName, user);
        } catch (Exception e) {
            return null; // TODO: How return error with returning object
        }
    }

    //  ---------SELECT-BY-NATIONAL-CODE----------------------------------------------
    public String findByNationalCode(String nationalCode, User user) {
        try {
            return PersonService.getPersonService().findByNationalCode(nationalCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-PHONE-NO---------------------------------------------------
    public String findByPhoneNo(String phoneNo, User user) {
        try {
            return PersonService.getPersonService().findByPhoneNo(phoneNo, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-BIRTHDATE--------------------------------------------------
    public String findByBirthDay(Timestamp birthDay, User user) {
        try {
            return PersonService.getPersonService().findByBirthDay(birthDay, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE----------------------------------------------
    public String findByPersonnelCode(Long personnelCode, User user) {
        try {
            return PersonService.getPersonService().findByPersonnelCode(personnelCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SET-EMPLOYMENT--------------------------------------------------------
    public String setPersonEmployment(Person person,Employment employment, User user) {
        try {
            person.setEmployment(employment);
            return PersonService.getPersonService().edit(person, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------CHANGE-STATUS---------------------------------------------------------
    public String changeStatus(Person person, Status status, User user) {
        try {
            return PersonService.getPersonService().changeStatus(person,status, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
