package com.humanresourcesmanagement.model.repository;

import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.utils.JpaProvider;

import jakarta.persistence.*;
import java.util.List;
import java.util.Map;

public class CrudRepository <T,I>{
    public T save(T t){
        EntityManager entityManager = JpaProvider.getJpaProvider().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(t);
        entityTransaction.commit();
        entityManager.close();
        return t;
    }
    public T edit(T t){EntityManager entityManager = JpaProvider.getJpaProvider().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(t);
        entityTransaction.commit();
        entityManager.close();
        return t;}
    public T deactivate(Class<T> tClass, I id){
        EntityManager entityManager = JpaProvider.getJpaProvider().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        T t = entityManager.find(tClass,id);
        entityManager.merge(Status.Inactive);
        entityTransaction.commit();
        entityManager.close();
        return t;}

    public T fire(Class<T> tClass, I id){
        EntityManager entityManager = JpaProvider.getJpaProvider().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        T t = entityManager.find(tClass,id);
        entityManager.merge(Status.Fired);
        entityTransaction.commit();
        entityManager.close();
        return t;}

    public T resign(Class<T> tClass, I id){
        EntityManager entityManager = JpaProvider.getJpaProvider().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        T t = entityManager.find(tClass,id);
        entityManager.merge(Status.Resigned);
        entityTransaction.commit();
        entityManager.close();
        return t;}

    public List<T> findAll(Class<T> tClass){
        EntityManager entityManager = JpaProvider.getJpaProvider().getEntityManager();
        Query query = entityManager.createQuery("select oo from " + tClass.getAnnotation(Entity.class).name() + " oo ");
        List<T> tList = query.getResultList();
        entityManager.close();
        return tList;
    }
    public T findById(Class<T> tClass,long id){
        EntityManager entityManager = JpaProvider.getJpaProvider().getEntityManager();
        T t = entityManager.find(tClass,id);
        entityManager.close();
        return t;
    }

    public List<T> executeQuery(String namedQuery, Map<String,Object> params){
        EntityManager entityManager = JpaProvider.getJpaProvider().getEntityManager();
        Query query = entityManager.createQuery(namedQuery);
        for (String key : params.keySet()) {
            query.setParameter(key,params.get(key));
        }
        List<T> tList = query.getResultList();
        entityManager.close();
        return tList;
    }


}
