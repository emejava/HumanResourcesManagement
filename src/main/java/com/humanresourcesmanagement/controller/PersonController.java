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
import java.util.HashMap;
import java.util.Map;

public class PersonController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();

    //  ---------SINGLETON---------------------------------------------------------------
    private static final PersonController personController = new PersonController();

    private PersonController() {
    }

    public static PersonController getPersonController() {
        return personController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object> save(
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

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(person);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        }else{
            try {
                result.put(true, PersonService.getPersonService().save(person, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean,Object> edit(
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

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(person);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, PersonService.getPersonService().edit(person, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ----------DELETE-----------------------------------------------------
    public Map<Boolean,Object> delete(Long personnelCode, User user) {
        result.clear();
        try {
            result.put(true, PersonService.getPersonService().delete(personnelCode, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Map<Boolean,Object> deactivate(Long personnelCode, User user) {
        result.clear();
        try {
            result.put(true, PersonService.getPersonService().deactivate(personnelCode, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public Map<Boolean,Object> activate(Long personnelCode, User user) {
        result.clear();
        try {
            result.put(true, PersonService.getPersonService().activate(personnelCode, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
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
    public Map<Boolean,Object> findById(Long id, User user) {
        result.clear();
        try {
            result.put(true, PersonService.getPersonService().findById(id, user));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-FIRSTNAME-AND-LASTNAME-------------------------------------
    public Map<Boolean,Object> findByFirstAndLastName(String firstName, String lastName, User user) {
        result.clear();
        try {
            result.put(true, PersonService.getPersonService().findByFirstAndLastName(firstName, lastName, user));
        } catch (Exception e) {
            result.put(false,ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-NATIONAL-CODE----------------------------------------------
    public Map<Boolean,Object> findByNationalCode(String nationalCode, User user) {
        result.clear();
        try {
            result.put(true, PersonService.getPersonService().findByNationalCode(nationalCode, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-PHONE-NO---------------------------------------------------
    public Map<Boolean,Object> findByPhoneNo(String phoneNo, User user) {
        result.clear();
        try {
            result.put(true, PersonService.getPersonService().findByPhoneNo(phoneNo, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-BIRTHDATE--------------------------------------------------
    public Map<Boolean,Object> findByBirthDay(Timestamp birthDay, User user) {
        result.clear();
        try {
            result.put(true, PersonService.getPersonService().findByBirthDay(birthDay, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE----------------------------------------------
    public Map<Boolean,Object> findByPersonnelCode(Long personnelCode, User user) {
        result.clear();
        try {
            result.put(true, PersonService.getPersonService().findByPersonnelCode(personnelCode, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SET-EMPLOYMENT--------------------------------------------------------
    public Map<Boolean,Object> setPersonEmployment(Person person,Employment employment, User user) {
        result.clear();
        try {
            person.setEmployment(employment);
            result.put(true, PersonService.getPersonService().edit(person, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------CHANGE-STATUS---------------------------------------------------------
    public Map<Boolean,Object> changeStatus(Person person, Status status, User user) {
        result.clear();
        try {
            result.put(true, PersonService.getPersonService().changeStatus(person,status, user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
