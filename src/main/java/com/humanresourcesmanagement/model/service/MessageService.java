package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.Message;
import com.humanresourcesmanagement.model.entity.MessageFile;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageService{

    //  ---------SINGLETON---------------------------------------------------------
    private static MessageService messageService = new MessageService();

    private MessageService() {
    }

    public static MessageService getMessageService() {
        return messageService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Message save(Message message,User user) throws Exception {
        try(CrudRepository<Message, Long> messageDa = new CrudRepository<>()) {
            message = messageDa.save(message);
            Log log = new Log(Action.Insert, message.toString(), user);
            LogService.getLogService().save(log);
            return message;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Message edit(Message message,User user) throws Exception {
        try(CrudRepository<Message, Long> messageDa = new CrudRepository<>()) {
            message = messageDa.edit(message);
            Log log = new Log(Action.Update, message.toString(), user);
            LogService.getLogService().save(log);
            return message;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public Message delete(Long id,User user) throws Exception {
        try(CrudRepository<Message, Long> messageDa = new CrudRepository<>()) {

            //  ------GET-FILES-ID-FROM-MESSAGE-----
            Message message = MessageService.getMessageService().findById(id,user);
            List<MessageFile> mfdList = message.getMessageFiles();

            //  ------DEACTIVATE-FILES--------------
            for (MessageFile messageFile : mfdList) {
                Long mfdId = messageFile.getId();
                MessageFileService.getFileDirectionService().delete(mfdId,user);
            }

            //  ------DEACTIVATE-MESSAGE------------
            messageDa.delete(Message.class, id);
            Log log = new Log(Action.Delete, message.toString(), user);
            LogService.getLogService().save(log);
            return message;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Message deactivate(Long id,User user) throws Exception {
        try(CrudRepository<Message, Long> messageDa = new CrudRepository<>()) {

            //  ------GET-FILES-ID-FROM-MESSAGE-----
            Message message = MessageService.getMessageService().findById(id,user);
            List<MessageFile> mfdList = message.getMessageFiles();

            //  ------DEACTIVATE-FILES--------------
            for (MessageFile messageFile : mfdList) {
                messageFile.setStatus(Status.Inactive);
                MessageFileService.getFileDirectionService().edit(messageFile,user);
            }

            //  ------DEACTIVATE-MESSAGE------------
            message.setStatus(Status.Inactive);
            messageDa.edit(message);
            Log log = new Log(Action.Deactivate, message.toString(), user);
            LogService.getLogService().save(log);
            return message;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Message> findAll(User user) throws Exception {
        try(CrudRepository<Message, Long> messageDa = new CrudRepository<>()) {
            List<Message> messageList = messageDa.findAll(Message.class);
            Log log = new Log(Action.Search, "All Messages", user);
            LogService.getLogService().save(log);
            return messageList;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<Message> findAllActive(User user) throws Exception {
        try(CrudRepository<Message, Long> messageDa = new CrudRepository<>()) {
            Map<String,Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("status",status);
            List<Message> messageList = messageDa.executeQuery("message.findAllActive", params);
            Log log = new Log(Action.Search, "All Active Messages", user);
            LogService.getLogService().save(log);
            return messageList;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Message findById(Long id,User user) throws Exception {
        try(CrudRepository<Message, Long> messageDa = new CrudRepository<>()) {
            Message message = messageDa.findById(Message.class, id);
            Log log = new Log(Action.Search, message.toString(), user);
            LogService.getLogService().save(log);
            return message;
        }
    }

    //  ---------SELECT-BY-SENDER-AND-RECEIVER--------------------------------------
    public List<Message> findBySenderAndReceiver(User sender,User receiver,User user) throws Exception {
        try(CrudRepository<Message, Long> messageDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("sender", sender);
            params.put("receiver", receiver);
            params.put("status",status);
            List<Message> messageList = messageDa.executeQuery("message.findBySenderAndReceiver", params);
            Log log = new Log(Action.Search, "All Messages By Sender & Receiver", user);
            LogService.getLogService().save(log);
            return messageList;
        }
    }

    //  ---------SELECT-ALL-BY-SENDER--------------------------------------SENT-BOX
    public List<Message> findAllBySender(User sender,User user) throws Exception {
        try(CrudRepository<Message, Long> messageDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("sender", sender);
            params.put("status",status);
            List<Message> messageList = messageDa.executeQuery("message.findAllBySender", params);
            Log log = new Log(Action.Search, "All Messages By Sender", user);
            LogService.getLogService().save(log);
            return messageList;
        }
    }

    //  ---------SELECT-ALL-BY-RECEIVER----------------------------------------INBOX
    public List<Message> findAllByReceiver(User receiver,User user) throws Exception {
        try(CrudRepository<Message, Long> messageDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("receiver", receiver);
            params.put("status",status);
            List<Message> messageList = messageDa.executeQuery("message.findAllByReceiver", params);
            Log log = new Log(Action.Search, "All Messages By Sender & Receiver", user);
            LogService.getLogService().save(log);
            return messageList;
        }
    }

    //  ---------RUN-NAMED-QUERIES----------------------------------------------------
    public List<Message> executeQuery(String namedQuery, Map<String, Object> params) throws Exception {
        return null;
    }


}
