package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.AttachmentService;

import java.util.HashMap;
import java.util.Map;


public class AttachmentController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();
    //  ---------SINGLETON---------------------------------------------------------------
    private static AttachmentController attachmentController = new AttachmentController();

    private AttachmentController() {
    }

    public static AttachmentController getFileController() {
        return attachmentController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean, Object> save(
            String name,
            User uploader,
            String path,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Attachment attachment = new Attachment(
                name,
                uploader,
                path);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(attachment);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, AttachmentService.getFileService().save(attachment, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }return result;
    }
    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean, Object> edit(
            Long id,
            String name,
            User uploader,
            String path,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Attachment attachment = new Attachment(
                id,
                name,
                uploader,
                path);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(attachment);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true,AttachmentService.getFileService().edit(attachment, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }return result;
    }
    //  ---------DELETE-------------------------------------------------------------
    public Map<Boolean, Object> delete(Long id,User user) {
        result.clear();
        try {
            result.put(true, AttachmentService.getFileService().delete(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Map<Boolean, Object> deactivate(Long id,User user) {
        result.clear();
        try {
            result.put(true, AttachmentService.getFileService().deactivate(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return AttachmentService.getFileService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return AttachmentService.getFileService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Map<Boolean, Object> findById(Long id,User user) {
        result.clear();
        try {
            result.put(true, AttachmentService.getFileService().findById(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL-BY-UPLOADER--------------------------------------------
    public Map<Boolean, Object> findAllByUploader(User uploader,User user) {
        result.clear();
        try {
            result.put(true, AttachmentService.getFileService().findAllByUploader(uploader,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-NAME-AND-UPLOADER-----------------------------------------
    public Map<Boolean, Object> findByNameAndUploader(String name,User uploader,User user) {
        result.clear();
        try {
            result.put(true, AttachmentService.getFileService().findByNameAndUploader(name,uploader,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
