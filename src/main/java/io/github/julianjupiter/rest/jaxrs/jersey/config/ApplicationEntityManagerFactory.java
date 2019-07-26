package io.github.julianjupiter.rest.jaxrs.jersey.config;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.CloseableService;

public class ApplicationEntityManagerFactory implements Factory<EntityManager> {
    private EntityManagerFactory entityManagerFactory;
    private final CloseableService closeableService;
    private static final String PERSISTENCE_UNIT_NAME = "rest-jaxrs-jersey";

    @Inject
    public ApplicationEntityManagerFactory(CloseableService closeableService) {
        this.closeableService = closeableService;
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @Override
    public EntityManager provide() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        closeableService.add(() -> {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        });

        return entityManager;
    }

    @Override
    public void dispose(EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}