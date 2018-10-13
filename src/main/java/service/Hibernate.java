package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Hibernate {
    private static EntityManager entityManager;

    private Hibernate() {

    }

    public static EntityManager getInstance() {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("configuratorPC");
        entityManager=entityManagerFactory.createEntityManager();
        return entityManager;
    }
}
