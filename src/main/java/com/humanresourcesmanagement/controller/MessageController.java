package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.service.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();
    //  ---------SINGLETON---------------------------------------------------------------
    private static MessageController messageController = new MessageController();

    private MessageController() {
    }

    public static MessageController getMessageController() {
        return messageController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object> save(
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

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(message);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, MessageService.getMessageService().save(message, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }
    //  ---------UPDATE-DATA--------------------------------------------------------
    public Map<Boolean,Object> edit(
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

        result.clear();
        Map<String, String> errors = Validation.getValidation().doValidation(message);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, MessageService.getMessageService().edit(message, user));
            } catch (Exception e) {
                result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
            }
        }
        return result;
    }
    //  ---------DELETE-------------------------------------------------------------
    public Map<Boolean,Object> delete(Long id,User user) {
        result.clear();
        try {
            result.put(true, MessageService.getMessageService().delete(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Map<Boolean,Object> deactivate(Long id,User user) {
        result.clear();
        try {
            result.put(true, MessageService.getMessageService().deactivate(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
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
    public Map<Boolean,Object> findById(Long id,User user) {
        result.clear();
        try {
            result.put(true, MessageService.getMessageService().findById(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-BY-SENDER-AND-RECEIVER--------------------------------------
    public Map<Boolean,Object> findBySenderAndReceiver(User sender, User receiver,User user) {
        result.clear();
        try {
            result.put(true, MessageService.getMessageService().findBySenderAndReceiver(sender, receiver,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL-BY-SENDER--------------------------------------SENT-BOX
    public Map<Boolean,Object> findAllBySender(User sender,User user) {
        result.clear();
        try {
            result.put(true, MessageService.getMessageService().findAllBySender(sender,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL-BY-RECEIVER----------------------------------------INBOX
    public Map<Boolean,Object> findAllByReceiver(User receiver,User user) {
        result.clear();
        try {
            result.put(true, MessageService.getMessageService().findAllByReceiver(receiver,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
