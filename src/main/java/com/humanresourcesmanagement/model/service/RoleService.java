package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.Role;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public class RoleService{

    //  ---------SINGLETON---------------------------------------------------------
    private static RoleService roleService = new RoleService();

    private RoleService() {
    }

    public static RoleService getRoleService() {
        return roleService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Role save(Role role) throws Exception {
        try(CrudRepository<Role,Long> roleDa = new CrudRepository<>()){
            role = roleDa.save(role);
            Log log = new Log(Action.Insert,role.toString(),null);
            LogService.getLogService().save(log);
            return role;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Role edit(Role role) throws Exception {
        try(CrudRepository<Role,Long> roleDa = new CrudRepository<>()){
            role = roleDa.edit(role);
            Log log = new Log(Action.Update,role.toString(),null);
            LogService.getLogService().save(log);
            return role;
        }
    }

    //  ----------DELETE------------------------------------------------------------
    public Role delete(Long id, User user) throws Exception {
        try(CrudRepository<Role, Long> userDa = new CrudRepository<>()) {
            Role role = userDa.delete(Role.class, id);
            Log log = new Log(Action.Delete, role.toString(), user);
            LogService.getLogService().save(log);
            return role;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Role deactivate(Long id,User user) throws Exception {
        try(CrudRepository<Role, Long> userDa = new CrudRepository<>()) {
            Role role = userDa.findById(Role.class, id);
            role.setStatus(Status.Inactive);
            userDa.edit(role);
            Log log = new Log(Action.Deactivate, role.toString(), user);
            LogService.getLogService().save(log);
            return role;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Role> findAll() throws Exception {
        try(CrudRepository<Role,Long> roleDa = new CrudRepository<>()){
            List<Role> roleList = roleDa.findAll(Role.class);
            Log log = new Log(Action.Search,"SELECT ALL",null);
            LogService.getLogService().save(log);
            return roleList;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Role findById(Long id) throws Exception {
        try(CrudRepository<Role,Long> roleDa = new CrudRepository<>()){
            Role role = roleDa.findById(Role.class,id);
            Log log = new Log(Action.Search,role.toString(),null);
            LogService.getLogService().save(log);
            return role;
        }
    }

    public List<Role> executeQuery(String namedQuery, Map<String, Object> params) throws Exception {
        return null;
    }
}
