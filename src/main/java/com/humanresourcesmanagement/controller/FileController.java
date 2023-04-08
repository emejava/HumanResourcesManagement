package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.FileService;


public class FileController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static FileController fileController = new FileController();

    private FileController() {
    }

    public static FileController getFileController() {
        return fileController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            String name,
            User uploader,
            String path,
            User user) {

        File file = new File(
                name,
                uploader,
                path);

        try {
            return FileService.getFileService().save(file,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            String name,
            User uploader,
            String path,
            User user) {

        File file = new File(
                id,
                name,
                uploader,
                path);

        try {
            return FileService.getFileService().edit(file,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------DELETE-------------------------------------------------------------
    public String delete(Long id,User user) {
        try {
            return FileService.getFileService().delete(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long id,User user) {
        try {
            return FileService.getFileService().deactivate(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return FileService.getFileService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return FileService.getFileService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public String findById(Long id,User user) {

        try {
            return FileService.getFileService().findById(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-BY-UPLOADER--------------------------------------------
    public String findAllByUploader(User uploader,User user) {

        try {
            return FileService.getFileService().findAllByUploader(uploader,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-NAME-AND-UPLOADER-----------------------------------------
    public String findByNameAndUploader(String name,User uploader,User user) {

        try {
            return FileService.getFileService().findByNameAndUploader(name,uploader,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
