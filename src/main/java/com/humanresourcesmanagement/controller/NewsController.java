package com.humanresourcesmanagement.controller;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.News;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.service.NewsService;

public class NewsController {
    //  ---------SINGLETON---------------------------------------------------------------
    private static NewsController newsController = new NewsController();

    private NewsController() {
    }

    public static NewsController getNewsController() {
        return newsController;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public String save(
            String subject,
            String title,
            String newsReport,
            User user) {

        News news = new News(
                subject,
                title,
                newsReport);

        try {
            return NewsService.getNewsService().save(news,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public String edit(
            Long id,
            String subject,
            String title,
            String newsReport,
            User user) {

        News news = new News(
                id,
                subject,
                title,
                newsReport);

        try {
            return NewsService.getNewsService().edit(news,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
    //  ---------DELETE-------------------------------------------------------------
    public String delete(Long id,User user) {
        try {
            return NewsService.getNewsService().delete(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public String deactivate(Long id,User user) {
        try {
            return NewsService.getNewsService().deactivate(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public String activate(Long id,User user) {
        try {
            return NewsService.getNewsService().activate(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
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
    public String findById(Long id,User user) {

        try {
            return NewsService.getNewsService().findById(id,user).toString();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
    }
}