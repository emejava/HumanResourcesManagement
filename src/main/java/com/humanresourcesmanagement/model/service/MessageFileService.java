package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public class MessageFileService {

    //  ---------SINGLETON---------------------------------------------------------
    private static MessageFileService messageFileService = new MessageFileService();

    private MessageFileService() {
    }

    public static MessageFileService getFileDirectionService() {
        return messageFileService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public MessageFile save(MessageFile messageFile, User user) throws Exception {
        try(CrudRepository<MessageFile, Long> messageFileDirectionDa = new CrudRepository<>()) {
            messageFile = messageFileDirectionDa.save(messageFile);
            Log log = new Log(Action.Insert, messageFile.toString(), user);
            LogService.getLogService().save(log);
            return messageFile;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public MessageFile edit(MessageFile messageFile, User user) throws Exception {
        try(CrudRepository<MessageFile, Long> messageFileDirectionDa = new CrudRepository<>()) {
            messageFile = messageFileDirectionDa.edit(messageFile);
            Log log = new Log(Action.Update, messageFile.toString(), user);
            LogService.getLogService().save(log);
            return messageFile;
        }
    }

    //  ----------DELETE-----------------------------------------------------------
    public MessageFile delete(Long id, User user) throws Exception {
        try(CrudRepository<MessageFile, Long> messageFileDirectionDa = new CrudRepository<>()) {
            MessageFile messageFile = messageFileDirectionDa.delete(MessageFile.class, id);
            Log log = new Log(Action.Delete, messageFile.toString(), user);
            LogService.getLogService().save(log);
            return messageFile;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public MessageFile deactivate(Long id, User user) throws Exception {
        try(CrudRepository<MessageFile, Long> messageFileDirectionDa = new CrudRepository<>()) {
            MessageFile messageFile = messageFileDirectionDa.findById(MessageFile.class, id);
            messageFile.setStatus(Status.Inactive);
            messageFileDirectionDa.edit(messageFile);
            Log log = new Log(Action.Deactivate, messageFile.toString(), user);
            LogService.getLogService().save(log);
            return messageFile;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<MessageFile> findAll(User user) throws Exception {
        try(CrudRepository<MessageFile, Long> messageFileDirectionDa = new CrudRepository<>()) {
            List<MessageFile> messageFileList = messageFileDirectionDa.findAll(MessageFile.class);
            Log log = new Log(Action.Search, "All FileDirection", user);
            LogService.getLogService().save(log);
            return messageFileList;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<MessageFile> findAllActive(User user) throws Exception {
        try(CrudRepository<MessageFile, Long> messageFileDirectionDa = new CrudRepository<>()) {
            List<MessageFile> messageFileList = messageFileDirectionDa.executeQuery("messageFileDirection.findAllActive", null);
            Log log = new Log(Action.Search, "All Active FileDirection", user);
            LogService.getLogService().save(log);
            return messageFileList;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public MessageFile findById(Long id, User user) throws Exception {
        try(CrudRepository<MessageFile, Long> messageFileDirectionDa = new CrudRepository<>()) {
            MessageFile messageFile = messageFileDirectionDa.findById(MessageFile.class, id);
            Log log = new Log(Action.Search, messageFile.toString(), user);
            LogService.getLogService().save(log);
            return messageFile;
        }
    }

    //  ---------RUN-NAMED-QUERIES----------------------------------------------------
    public List<MessageFile> executeQuery(String namedQuery, Map<String, Object> params) throws Exception {
        return null;
    }


}
