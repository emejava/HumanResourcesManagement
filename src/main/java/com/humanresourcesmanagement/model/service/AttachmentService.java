package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.Attachment;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttachmentService {
    //  ---------SINGLETON---------------------------------------------------------
    private static AttachmentService attachmentService = new AttachmentService();

    private AttachmentService() {
    }

    public static AttachmentService getFileService() {
        return attachmentService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Attachment save(Attachment attachment, User user) throws Exception {
        try (CrudRepository<Attachment, Long> fileDa = new CrudRepository<>()) {
            attachment = fileDa.save(attachment);
            Log log = new Log(Action.Insert, attachment.toString(), user);
            LogService.getLogService().save(log);
            return attachment;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Attachment edit(Attachment attachment, User user) throws Exception {
        try (CrudRepository<Attachment, Long> fileDa = new CrudRepository<>()) {
            attachment = fileDa.edit(attachment);
            Log log = new Log(Action.Update, attachment.toString(), user);
            LogService.getLogService().save(log);
            return attachment;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public Attachment delete(Long id, User user) throws Exception {
        try (CrudRepository<Attachment, Long> fileDa = new CrudRepository<>()) {
            Attachment attachment = fileDa.delete(Attachment.class, id);
            Log log = new Log(Action.Delete, attachment.toString(), user);
            LogService.getLogService().save(log);
            return attachment;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Attachment deactivate(Long id, User user) throws Exception {
        try (CrudRepository<Attachment, Long> fileDa = new CrudRepository<>()) {
            Attachment attachment = fileDa.findById(Attachment.class, id);
            attachment.setStatus(Status.Inactive);
            fileDa.edit(attachment);
            Log log = new Log(Action.Deactivate, attachment.toString(), user);
            LogService.getLogService().save(log);
            return attachment;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Attachment> findAll(User user) throws Exception {
        try (CrudRepository<Attachment, Long> fileDa = new CrudRepository<>()) {
            List<Attachment> attachmentList = fileDa.findAll(Attachment.class);
            Log log = new Log(Action.Search, "All Files", user);
            LogService.getLogService().save(log);
            return attachmentList;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<Attachment> findAllActive(User user) throws Exception {
        try (CrudRepository<Attachment, Long> fileDa = new CrudRepository<>()) {
            Map<String,Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("status",status);
            List<Attachment> attachmentList = fileDa.executeQuery("file.findAllActive", params);
            Log log = new Log(Action.Search, "All Active Files", user);
            LogService.getLogService().save(log);
            return attachmentList;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Attachment findById(Long id, User user) throws Exception {
        try (CrudRepository<Attachment, Long> fileDa = new CrudRepository<>()) {
            Attachment attachment = fileDa.findById(Attachment.class, id);
            Log log = new Log(Action.Search, attachment.toString(), user);
            LogService.getLogService().save(log);
            return attachment;
        }
    }

    //  ---------SELECT-ALL-BY-UPLOADER--------------------------------------------
    public List<Attachment> findAllByUploader(User uploader, User user) throws Exception {
        try (CrudRepository<Attachment, Long> fileDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("uploader", uploader);
            List<Attachment> attachments = fileDa.executeQuery("file.findAllByUploader", params);
            Log log = new Log(Action.Search, uploader.toString() +" "+ attachments.toString(), user);
            LogService.getLogService().save(log);
            return attachments;
        }
    }

    //  ---------SELECT-BY-NAME-AND-UPLOADER-----------------------------------------
    public Attachment findByNameAndUploader(String name, User uploader, User user) throws Exception {
        try (CrudRepository<Attachment, Long> fileDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("name", name);
            params.put("uploader", uploader);
            params.put("status",status);
            List<Attachment> attachments = fileDa.executeQuery("file.findByNameAndUploader", params);
            Log log = new Log(Action.Search, uploader.toString() +" "+ attachments.toString(), user);
            LogService.getLogService().save(log);
            if (attachments.size() == 1) {
                return attachments.get(0);
            } else {
                return null;
            }
        }
    }
}
