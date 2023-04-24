package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.News;
import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsService {
    //  ---------SINGLETON---------------------------------------------------------
    private static NewsService newsService = new NewsService();

    private NewsService() {
    }

    public static NewsService getNewsService() {
        return newsService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public News save(News news, User user) throws Exception {
        try (CrudRepository<News, Long> newsDa = new CrudRepository<>()) {
            news = newsDa.save(news);
            Log log = new Log(Action.Insert, news.toString(), user);
            LogService.getLogService().save(log);
            return news;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public News edit(News news, User user) throws Exception {
        try (CrudRepository<News, Long> newsDa = new CrudRepository<>()) {
            news = newsDa.edit(news);
            Log log = new Log(Action.Update, news.toString(), user);
            LogService.getLogService().save(log);
            return news;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public News delete(Long id, User user) throws Exception {
        try (CrudRepository<News, Long> newsDa = new CrudRepository<>()) {
            News news = newsDa.delete(News.class, id);
            Log log = new Log(Action.Delete, news.toString(), user);
            LogService.getLogService().save(log);
            return news;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public News deactivate(Long id, User user) throws Exception {
        try (CrudRepository<News, Long> newsDa = new CrudRepository<>()) {
            News news = newsDa.findById(News.class, id);
            news.setStatus(Status.Inactive);
            newsDa.edit(news);
            Log log = new Log(Action.Deactivate, news.toString(), user);
            LogService.getLogService().save(log);
            return news;
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public News activate(Long id, User user) throws Exception {
        try (CrudRepository<News, Long> newsDa = new CrudRepository<>()) {
            News news = newsDa.findById(News.class, id);
            news.setStatus(Status.Active);
            newsDa.edit(news);
            Log log = new Log(Action.Activate, news.toString(), user);
            LogService.getLogService().save(log);
            return news;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<News> findAll(User user) throws Exception {
        try (CrudRepository<News, Long> newsDa = new CrudRepository<>()) {
            List<News> newsList = newsDa.findAll(News.class);
            Log log = new Log(Action.Search, "All News", user);
            LogService.getLogService().save(log);
            return newsList;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<News> findAllActive(User user) throws Exception {
        try (CrudRepository<News, Long> newsDa = new CrudRepository<>()) {
            Map<String,Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("status",status);
            List<News> newsList = newsDa.executeQuery("news.findAllActive", params);
            Log log = new Log(Action.Search, "All Active News", user);
            LogService.getLogService().save(log);
            return newsList;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public News findById(Long id, User user) throws Exception {
        try (CrudRepository<News, Long> newsDa = new CrudRepository<>()) {
            News news = newsDa.findById(News.class, id);
            Log log = new Log(Action.Search, news.toString(), user);
            LogService.getLogService().save(log);
            return news;
        }
    }
}
