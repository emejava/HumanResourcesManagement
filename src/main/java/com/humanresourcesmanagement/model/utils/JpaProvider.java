package com.humanresourcesmanagement.model.utils;

import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaProvider {
    private static JpaProvider jpaProvider = new JpaProvider();
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eme");

    public static JpaProvider getJpaProvider() {
        return jpaProvider;
    }

    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

}
