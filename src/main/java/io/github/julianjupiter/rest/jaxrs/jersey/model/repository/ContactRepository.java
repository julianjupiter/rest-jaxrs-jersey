package io.github.julianjupiter.rest.jaxrs.jersey.model.repository;

import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;

import java.util.List;

public interface ContactRepository {

    List<Contact> findAll() throws Exception;

    Contact findById(long id) throws Exception;

    Contact save(Contact user) throws Exception;

    Contact update(Contact user) throws Exception;

    void delete(long id) throws Exception;

}