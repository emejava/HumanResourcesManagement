package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.MessageFileService;

import java.util.Map;

public class MessageFileController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static MessageFileController messageFileController = new MessageFileController();

    private MessageFileController() {
    }

    public static MessageFileController getFileDirectionController() {
        return messageFileController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            User sender,
            String filePath,
            Message msg,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        MessageFile messageFile = new MessageFile(
                sender,
                filePath,
                msg);
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(messageFile);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return MessageFileService.getFileDirectionService().save(messageFile, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------UNUSED
    public String edit() {
        return null;
    }

    //  ----------DELETE-----------------------------------------------------------
    public String delete(Long id,
                             User user) {
        try {
            return MessageFileService.getFileDirectionService().delete(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE----------------------------------------------------
    public String deactivate(Long id,
                             User user) {
        try {
            return MessageFileService.getFileDirectionService().deactivate(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return MessageFileService.getFileDirectionService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return MessageFileService.getFileDirectionService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public String findById(Long id,User user) {

        try {
            return MessageFileService.getFileDirectionService().findById(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

}
