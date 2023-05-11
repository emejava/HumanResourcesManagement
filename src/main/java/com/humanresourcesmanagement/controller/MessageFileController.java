package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.MessageFileService;

import java.util.HashMap;
import java.util.Map;

public class MessageFileController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();
    //  ---------SINGLETON---------------------------------------------------------------
    private static MessageFileController messageFileController = new MessageFileController();

    private MessageFileController() {
    }

    public static MessageFileController getFileDirectionController() {
        return messageFileController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object> save(
            User sender,
            String filePath,
            Message msg,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        MessageFile messageFile = new MessageFile(
                sender,
                filePath,
                msg);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(messageFile);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, MessageFileService.getFileDirectionService().save(messageFile, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }

    //  ---------UPDATE-DATA--------------------------------------------------UNUSED
    public Map<Boolean,Object> edit() {
        return null;
    }

    //  ----------DELETE-----------------------------------------------------------
    public Map<Boolean,Object> delete(Long id,
                             User user) {
        result.clear();
        try {
            result.put(true, MessageFileService.getFileDirectionService().delete(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------LOGICAL-DELETE----------------------------------------------------
    public Map<Boolean,Object> deactivate(Long id,
                             User user) {
        result.clear();
        try {
            result.put(true, MessageFileService.getFileDirectionService().deactivate(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        result.clear();
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
    public Map<Boolean,Object> findById(Long id,User user) {
        result.clear();
        try {
            result.put(true, MessageFileService.getFileDirectionService().findById(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

}
