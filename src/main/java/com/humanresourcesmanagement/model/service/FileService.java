package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.File;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileService {
    //  ---------SINGLETON---------------------------------------------------------
    private static FileService fileService = new FileService();

    private FileService() {
    }

    public static FileService getFileService() {
        return fileService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public File save(File file, User user) throws Exception {
        try (CrudRepository<File, Long> fileDa = new CrudRepository<>()) {
            file = fileDa.save(file);
            Log log = new Log(Action.Insert, file.toString(), user);
            LogService.getLogService().save(log);
            return file;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public File edit(File file, User user) throws Exception {
        try (CrudRepository<File, Long> fileDa = new CrudRepository<>()) {
            file = fileDa.edit(file);
            Log log = new Log(Action.Update, file.toString(), user);
            LogService.getLogService().save(log);
            return file;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public File delete(Long id, User user) throws Exception {
        try (CrudRepository<File, Long> fileDa = new CrudRepository<>()) {
            File file = fileDa.delete(File.class, id);
            Log log = new Log(Action.Delete, file.toString(), user);
            LogService.getLogService().save(log);
            return file;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public File deactivate(Long id, User user) throws Exception {
        try (CrudRepository<File, Long> fileDa = new CrudRepository<>()) {
            File file = fileDa.findById(File.class, id);
            file.setStatus(Status.Inactive);
            fileDa.edit(file);
            Log log = new Log(Action.Deactivate, file.toString(), user);
            LogService.getLogService().save(log);
            return file;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<File> findAll(User user) throws Exception {
        try (CrudRepository<File, Long> fileDa = new CrudRepository<>()) {
            List<File> fileList = fileDa.findAll(File.class);
            Log log = new Log(Action.Search, "All Files", user);
            LogService.getLogService().save(log);
            return fileList;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<File> findAllActive(User user) throws Exception {
        try (CrudRepository<File, Long> fileDa = new CrudRepository<>()) {
            List<File> fileList = fileDa.executeQuery("file.findAllActive", null);
            Log log = new Log(Action.Search, "All Active Files", user);
            LogService.getLogService().save(log);
            return fileList;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public File findById(Long id, User user) throws Exception {
        try (CrudRepository<File, Long> fileDa = new CrudRepository<>()) {
            File file = fileDa.findById(File.class, id);
            Log log = new Log(Action.Search, file.toString(), user);
            LogService.getLogService().save(log);
            return file;
        }
    }

    //  ---------SELECT-ALL-BY-UPLOADER--------------------------------------------
    public List<File> findAllByUploader(User uploader, User user) throws Exception {
        try (CrudRepository<File, Long> fileDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("uploader", uploader);
            List<File> files = fileDa.executeQuery("file.findAllByUploader", params);
            Log log = new Log(Action.Search, uploader.toString() +" "+ files.toString(), user);
            LogService.getLogService().save(log);
            return files;
        }
    }

    //  ---------SELECT-BY-NAME-AND-UPLOADER-----------------------------------------
    public File findByNameAndUploader(String name,User uploader, User user) throws Exception {
        try (CrudRepository<File, Long> fileDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("name", name);
            params.put("uploader", uploader);
            List<File> files = fileDa.executeQuery("file.findByNameAndUploader", params);
            Log log = new Log(Action.Search, uploader.toString() +" "+ files.toString(), user);
            LogService.getLogService().save(log);
            if (files.size() == 1) {
                return files.get(0);
            } else {
                return null;
            }
        }
    }
}
