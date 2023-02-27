package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private static UserService userService = new UserService();

    private UserService() {
    }

    public static UserService getUserService() {
        return userService;
    }

    public User save(User user) throws Exception {
        CrudRepository<User, Long> userDa = new CrudRepository<>();
        user.setStatus(Status.Active);
        user = userDa.save(user);
        Log log = new Log(Action.Insert, user.toString(), null);
        LogService.getLogService().save(log);
        return user;
    }

    public User edit(User user) throws Exception {
        CrudRepository<User, Long> userDa = new CrudRepository<>();
        user = userDa.edit(user);
        Log log = new Log(Action.Update, user.toString(), null);
        LogService.getLogService().save(log);
        return user;
    }

    public User deactivate(Long id) throws Exception {
        CrudRepository<User, Long> userDa = new CrudRepository<>();
            User user = userDa.deactivate(User.class,id);
            Log log = new Log(Action.Change_Status, user.toString(), null);
        LogService.getLogService().save(log);
        return userDa.deactivate(User.class, id);
    }

    public List<User> findAll() throws Exception {
        CrudRepository<User, Long> userDa = new CrudRepository<>();
        List<User> userList = userDa.findAll(User.class);
        Log log = new Log(Action.Search,"Select All", null);
        LogService.getLogService().save(log);
        return userList;

    }

    public User findById(Long id) throws Exception {
        CrudRepository<User, Long> userDa = new CrudRepository<>();
        User user =  userDa.findById(User.class,id);
        Log log = new Log(Action.Search,user.toString(), null);
        LogService.getLogService().save(log);
        return user;
    }


    public User isValidate(String userName,String password){
        CrudRepository<User,Long> userDa = new CrudRepository<>();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("username",userName);
        paramMap.put("password",password);
        if(userDa.executeQuery("User.isValidate",paramMap)!=null){
            User user = userDa.executeQuery("User.isValidate",paramMap).get(0);
            Log log = new Log(Action.Success_Login,userName+"/"+password,null);
            LogService.getLogService().save(log);
            return user;
        }else{
            Log log = new Log(Action.Failed_Login,userName+"/"+password,null);
            LogService.getLogService().save(log);
            return null;
        }

    }
}
