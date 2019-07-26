package io.github.julianjupiter.rest.jaxrs.jersey.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class JpaUtil {
    private static Logger logger = LoggerFactory.getLogger(JpaUtil.class);

    private JpaUtil() {

    }

    public static void beginTransaction(EntityManager entityManager) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
            logger.info("Transaction started.");
        }
    }

    public static void commitTransaction(EntityManager entityManager) {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
            logger.info("Transaction committed.");
        }
    }

    public static void rollbackTransaction(EntityManager entityManager) {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
            logger.info("Transaction rolled back.");
        }
    }
}