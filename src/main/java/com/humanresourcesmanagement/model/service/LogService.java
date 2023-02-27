package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.repository.CrudRepository;

public class LogService {
    private static LogService logService = new LogService();

    private LogService() {
    }

    public static LogService getLogService() {
        return logService;
    }
    public Log save(Log log){
            CrudRepository<Log,Long> logDA = new CrudRepository<>();
            return logDA.save(log);
    }
}
