package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.repository.CrudRepository;

public class LogService {

    //  ---------SINGLETON---------------------------------------------------------
    private static LogService logService = new LogService();

    private LogService() {
    }

    public static LogService getLogService() {
        return logService;
    }

    //  ---------INSERT-LOG---------------------------------------------------------
    public Log save(Log log) throws Exception {
        try(CrudRepository<Log, Long> logDa = new CrudRepository<>()) {
            logDa.save(log);
            return log;
        }
    }

}

