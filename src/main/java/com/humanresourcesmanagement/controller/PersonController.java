package com.humanresourcesmanagement.controller;


import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Gender;
import com.humanresourcesmanagement.model.entity.enums.MaritalStatus;
import com.humanresourcesmanagement.model.service.PersonService;
import java.sql.Timestamp;
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
            Timestamp birthday,
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
            String children,
            String email,
            File nationalCardPicture,
            File birthCertificatePicture,
            User user) {
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
                birthCertificatePicture);

        try {
                return PersonService.getPersonService().save(person, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            String firstName,
            String lastName,
            String nationalCode,
            String birthCertificateCode,
            Timestamp birthday,
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
            String children,
            String email,
            File nationalCardPicture,
            File birthCertificatePicture,
            User user) {

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

        try {
            return PersonService.getPersonService().edit(person, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
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
    public String findById(Long personnelCode, User user) {
        try {
            return PersonService.getPersonService().findById(personnelCode, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------SELECT-BY-FIRSTNAME-AND-LASTNAME-------------------------------------
    public String findByFirstAndLastName(String firstName,String lastName, User user) {
        try {
            return PersonService.getPersonService().findByFirstAndLastName(firstName,lastName, user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
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
}
