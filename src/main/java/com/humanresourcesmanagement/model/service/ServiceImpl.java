package com.humanresourcesmanagement.model.service;

import java.util.List;
import java.util.Map;

public interface ServiceImpl <T,U,I>{
   T save(T t,U u) throws Exception;
   T edit(T t,U u) throws Exception;
   T deactivate(I id,U u) throws Exception;
   T delete(I id,U u) throws Exception;
   T activate(I id,U u) throws Exception;
   List<T> findAll(U u) throws Exception;
   T findById(I id,U u) throws Exception;
   List<T> executeQuery(String namedQuery, Map<String,Object> params) throws Exception;
}
