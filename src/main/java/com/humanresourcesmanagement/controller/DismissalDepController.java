package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.DismissalDep;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.service.DismissalDepService;
import java.sql.Timestamp;
import java.util.List;

public class DismissalDepController {

    private static DismissalDepController dismissalDepController = new DismissalDepController();

    private DismissalDepController(){
    }
    public static DismissalDepController getDismissalDepController() {return dismissalDepController;}

    public String save(Person person, Long personnelCode, String reasonOfDismissal, Timestamp timestamp,
                       Status status, long checkOut) {
        try {
            DismissalDep dismissalDep = new DismissalDep(person, personnelCode, reasonOfDismissal, timestamp, status, checkOut);
            return DismissalDepService.getDismissalDepService().save(dismissalDep).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }
    public String edit(Person person, Long personnelCode, String reasonOfDismissal, Timestamp timestamp,
                       Status status, long checkOut) {
        try {
            DismissalDep dismissalDep = new DismissalDep(person, personnelCode, reasonOfDismissal, timestamp, status, checkOut);
            return DismissalDepService.getDismissalDepService().save(dismissalDep).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }
    public String remove(Long personnelCode) {
        try {
           DismissalDep dismissalDep = new DismissalDep();
            return DismissalDepService.getDismissalDepService().remove(personnelCode).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }

    }
    public String findAll() {
        try {
            List<DismissalDep> dismissalDepList = DismissalDepService.getDismissalDepService().findAll();
            return dismissalDepList.toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper(e).toString();
        }
    }
    }

