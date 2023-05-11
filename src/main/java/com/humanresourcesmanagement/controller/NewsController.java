package com.humanresourcesmanagement.controller;

import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.controller.validation.Validation;
import com.humanresourcesmanagement.model.entity.Attachment;
import com.humanresourcesmanagement.model.entity.ErrorsTO;
import com.humanresourcesmanagement.model.entity.News;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.service.NewsService;

import java.util.HashMap;
import java.util.Map;

public class NewsController {
    Map<Boolean, Object> result = new HashMap<>();
    ErrorsTO errorsTO = new ErrorsTO();
    //  ---------SINGLETON---------------------------------------------------------------
    private static NewsController newsController = new NewsController();

    private NewsController() {
    }

    public static NewsController getNewsController() {
        return newsController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Map<Boolean,Object> save(
            String subject,
            String title,
            String newsReport,
            Attachment mainAttachment,
            Attachment secondAttachment,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        News news = new News(
                subject,
                title,
                newsReport,
                mainAttachment,
                secondAttachment);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(news);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, NewsService.getNewsService().save(news, user));
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
            String title,
            String newsReport,
            Attachment mainAttachment,
            Attachment secondAttachment,
            User user) {
        //  ---------CREATE-OBJECT-----------------
        News news = new News(
                id,
                subject,
                title,
                newsReport,
                mainAttachment,
                secondAttachment);

        result.clear();
        //  ---------VALIDATING-DATA---------------
        Map<String, String> errors = Validation.getValidation().doValidation(news);
        if (errors != null) {
            errorsTO.setErrors(errors);
            result.put(false, errorsTO);
        } else {
            try {
                result.put(true, NewsService.getNewsService().edit(news, user));
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
            result.put(true, NewsService.getNewsService().delete(id,user));
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
            result.put(true, NewsService.getNewsService().deactivate(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public Map<Boolean,Object> activate(Long id,User user) {
        result.clear();
        try {
            result.put(true, NewsService.getNewsService().activate(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public String findAll(User user) {
        try {
            return NewsService.getNewsService().findAll(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public String findAllActive(User user) {
        try {
            return NewsService.getNewsService().findAllActive(user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Map<Boolean,Object> findById(Long id,User user) {
        result.clear();
        try {
            result.put(true, NewsService.getNewsService().findById(id,user));
        } catch (Exception e) {
            result.put(false, ExceptionWrapper.getExceptionWrapper().getMessage(e));
        }finally {
            return result;
        }
    }
}
