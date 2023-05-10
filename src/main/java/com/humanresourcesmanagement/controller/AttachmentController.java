package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.AttachmentService;

import java.util.Map;


public class AttachmentController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static AttachmentController attachmentController = new AttachmentController();

    private AttachmentController() {
    }

    public static AttachmentController getFileController() {
        return attachmentController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            String name,
            User uploader,
            String path,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Attachment attachment = new Attachment(
                name,
                uploader,
                path);
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(attachment);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return AttachmentService.getFileService().save(attachment, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }
    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
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
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(attachment);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return AttachmentService.getFileService().edit(attachment, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }
    //  ---------DELETE-------------------------------------------------------------
    public String delete(Long id,User user) {
        try {
            return AttachmentService.getFileService().delete(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long id,User user) {
        try {
            return AttachmentService.getFileService().deactivate(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
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
    public String findById(Long id,User user) {

        try {
            return AttachmentService.getFileService().findById(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-BY-UPLOADER--------------------------------------------
    public String findAllByUploader(User uploader,User user) {

        try {
            return AttachmentService.getFileService().findAllByUploader(uploader,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-NAME-AND-UPLOADER-----------------------------------------
    public String findByNameAndUploader(String name,User uploader,User user) {

        try {
            return AttachmentService.getFileService().findByNameAndUploader(name,uploader,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
