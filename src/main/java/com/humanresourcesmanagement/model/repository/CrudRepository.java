package com.humanresourcesmanagement.model.repository;

import com.humanresourcesmanagement.model.utils.JpaProvider;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Map;


public class CrudRepository<T, I> implements AutoCloseable{
    private EntityManager entityManager;

    //  ---------INSERT-DATA--------------------------------------------------------
    public T save(T t) {
        EntityManager entityManager = JpaProvider.getJpaProvider().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(t);
        entityTransaction.commit();
        return t;
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public T edit(T t) {
        entityManager = JpaProvider.getJpaProvider().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(t);
        entityTransaction.commit();
        return t;
    }

    //  ----------DELETE-----------------------------------------------------
    public T delete(Class<T> tClass, I id) {
        entityManager = JpaProvider.getJpaProvider().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        T t = entityManager.find(tClass, id);
        entityManager.remove(t);
        entityTransaction.commit();
        return t;
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<T> findAll(Class<T> tClass) {
        entityManager = JpaProvider.getJpaProvider().getEntityManager();
        Query query = entityManager.createQuery(
                "select oo from " + tClass.getAnnotation(Entity.class).name() + " oo "
        );
        List<T> tList = query.getResultList();
        return tList;
    }

    //  ---------SELECT-BY-ID--------------------------------------------------------
    public T findById(Class<T> tClass, long id) {
        entityManager = JpaProvider.getJpaProvider().getEntityManager();
        T t = entityManager.find(tClass, id);
        return t;
    }

    //  ---------RUN-NAMED-QUERIES----------------------------------------------------
    public List<T> executeQuery(String namedQuery, Map<String, Object> params) {
        entityManager = JpaProvider.getJpaProvider().getEntityManager();
        Query query = entityManager.createNamedQuery(namedQuery);
        if (params != null)
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        return (List<T>) query.getResultList();
    }


    //  ---------CLOSE-CONNECTION----------------------------------------------------
    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}
