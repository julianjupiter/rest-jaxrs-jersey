package io.github.julianjupiter.rest.jaxrs.jersey.config;

import io.github.julianjupiter.rest.jaxrs.jersey.model.repository.ContactRepository;
import io.github.julianjupiter.rest.jaxrs.jersey.model.repository.ContactRepositoryImpl;
import io.github.julianjupiter.rest.jaxrs.jersey.service.ContactService;
import io.github.julianjupiter.rest.jaxrs.jersey.service.ContactServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.persistence.EntityManager;

public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindFactory(ApplicationEntityManagerFactory.class).to(EntityManager.class).in(RequestScoped.class);
        bind(ContactServiceImpl.class).to(ContactService.class).in(RequestScoped.class);
        bind(ContactRepositoryImpl.class).to(ContactRepository.class).in(RequestScoped.class);
    }

}
