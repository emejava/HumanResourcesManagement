package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService{

    //  ---------SINGLETON---------------------------------------------------------
    private static UserService userService = new UserService();

    private UserService() {
    }

    public static UserService getUserService() {
        return userService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public User save(User newUser,User user) throws Exception {
        try(CrudRepository<User, Long> userDa = new CrudRepository<>()) {
            newUser = userDa.save(newUser);
            Log log = new Log(Action.Insert, newUser.toString(), user);
            LogService.getLogService().save(log);
            return newUser;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public User edit(User userToEdit,User user) throws Exception {
        try(CrudRepository<User, Long> userDa = new CrudRepository<>()) {
            userToEdit = userDa.edit(userToEdit);
            Log log = new Log(Action.Update, userToEdit.toString(), user);
            LogService.getLogService().save(log);
            return userToEdit;
        }
    }

    //  ----------DELETE------------------------------------------------------------
    public User delete(Long id,User user) throws Exception {
        try(CrudRepository<User, Long> userDa = new CrudRepository<>()) {
            User foundUser = userDa.delete(User.class, id);
            Log log = new Log(Action.Delete, foundUser.toString(), user);
            LogService.getLogService().save(log);
            return foundUser;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public User deactivate(Long id,User user) throws Exception {
        try(CrudRepository<User, Long> userDa = new CrudRepository<>()) {
            User foundUser = userDa.findById(User.class, id);
            foundUser.setStatus(Status.Inactive);
            userDa.edit(foundUser);
            Log log = new Log(Action.Deactivate, foundUser.toString(), user);
            LogService.getLogService().save(log);
            return foundUser;
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public User activate(Long id,User user) throws Exception {
        try(CrudRepository<User, Long> userDa = new CrudRepository<>()) {
            User foundUser = userDa.findById(User.class, id);
            foundUser.setStatus(Status.Active);
            userDa.edit(foundUser);
            Log log = new Log(Action.Activate, foundUser.toString(), user);
            LogService.getLogService().save(log);
            return foundUser;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<User> findAll(User user) throws Exception {
        try(CrudRepository<User, Long> userDa = new CrudRepository<>()) {
            List<User> users = userDa.findAll(User.class);
            Log log = new Log(Action.Search, "All Users", user);
            LogService.getLogService().save(log);
            return users;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<User> findAllActive(User user) throws Exception {
        try(CrudRepository<User, Long> userDa = new CrudRepository<>()) {
            Map<String,Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("status",status);
            List<User> activeUsers = userDa.executeQuery("user.findAllActive", params);
            Log log = new Log(Action.Search, "All Active Users", user);
            LogService.getLogService().save(log);
            return activeUsers;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------USER-HAS-NO-ID(LONG)
    public User findById(Long id) throws Exception {
        return null;
    }

    //  ---------SELECT-BY-USERNAME---------------------------------------------------
    public User findByUsername(String username,User user) throws Exception {
        try(CrudRepository<User, Long> userDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("username", username);
            params.put("status",status);
            List<User> users = userDa.executeQuery("user.findByUsername", params);
            Log log = new Log(Action.Search, users.toString(), user);
            LogService.getLogService().save(log);
            if (users.size() == 1) {
                return users.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------VALIDATE-LOGIN-DATA--------------------------------------------------
    public User isValidate(String username, String password) throws Exception {
        try (CrudRepository<User, Long> userDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("username", username);
            params.put("password", password);
            params.put("status",status);
            List<User> users = userDa.executeQuery("user.isValidate", params);
            User user = null;
            if (users.size() == 1) {
                return users.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------CHECK-USER-ACCESS---------------------------------------------------
    public String hasAccess(String username) throws Exception {
        try (CrudRepository<User, Long> userDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("username", username);
            params.put("status",status);
            List<User> users = userDa.executeQuery("user.hasAccess", params);
            User user1 = null;
            if (users.size() == 1) {
                return users.get(0).getAccessLevel().getTitle();
            } else {
                return null;
            }
        }
    }
}
