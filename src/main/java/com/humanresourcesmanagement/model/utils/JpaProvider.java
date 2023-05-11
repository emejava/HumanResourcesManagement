package com.humanresourcesmanagement.model.utils;

import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaProvider {
    //  -------SINGLETON---------------------------
    private static final JpaProvider jpaProvider = new JpaProvider();

    private JpaProvider() {
    }
    public static JpaProvider getJpaProvider() {
        return jpaProvider;
    }


    //  -------CREATE-FACTORY----------------------
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eme");


    //  -------RETURN-AND-CREATE-ENTITY-MANAGER-----
    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

}
