package com.humanresourcesmanagement.model.service;

import java.util.List;
import java.util.Map;

public interface ServiceImpl <T,I>{
   T save(T t) throws Exception;
   T edit(T t) throws Exception;
   T deactivate(I id) throws Exception;
   T fire(I id) throws Exception;
   T resign(I id) throws Exception;
   List<T> findAll() throws Exception;
   T findById(I id) throws Exception;
   List<T> executeQuery(String namedQuery, Map<String,Object> params) throws Exception;
}
