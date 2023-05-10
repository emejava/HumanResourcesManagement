package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.MessageService;

import java.util.List;
import java.util.Map;

public class MessageController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static MessageController messageController = new MessageController();

    private MessageController() {
    }

    public static MessageController getMessageController() {
        return messageController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            String subject,
            User sender,
            User receiver,
            String receiverRole,
            String msg,
            List<MessageFile> messageFiles,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Message message = new Message(
                subject,
                sender,
                receiver,
                receiverRole,
                msg,
                messageFiles);
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(message);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return MessageService.getMessageService().save(message, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }
    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            String subject,
            User sender,
            User receiver,
            String receiverRole,
            String msg,
            boolean forwarded,
            User forwardedTo,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        Message message = new Message(
                id,
                subject,
                sender,
                receiver,
                receiverRole,
                msg,
                forwarded,
                forwardedTo);
        Map<String, String> errors = Validation.getValidation().doValidation(message);
        if (errors != null) {
            return new Gson().toJson(errors);
        } else {
            try {
                return MessageService.getMessageService().edit(message, user).toString();
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionWrapper().getMessage(e);
            }
        }
    }
    //  ---------DELETE-------------------------------------------------------------
    public String delete(Long id,User user) {
        try {
            return MessageService.getMessageService().delete(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long id,User user) {
        try {
            return MessageService.getMessageService().deactivate(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return MessageService.getMessageService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return MessageService.getMessageService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public String findById(Long id,User user) {

        try {
            return MessageService.getMessageService().findById(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-SENDER-AND-RECEIVER--------------------------------------
    public String findBySenderAndReceiver(User sender, User receiver,User user) {

        try {
            return MessageService.getMessageService().findBySenderAndReceiver(sender, receiver,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-BY-SENDER--------------------------------------SENT-BOX
    public String findAllBySender(User sender,User user) {

        try {
            return MessageService.getMessageService().findAllBySender(sender,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-BY-RECEIVER----------------------------------------INBOX
    public String findAllByReceiver(User receiver,User user) {

        try {
            return MessageService.getMessageService().findAllByReceiver(receiver,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}
