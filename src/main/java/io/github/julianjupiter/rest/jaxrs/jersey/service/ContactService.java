package io.github.julianjupiter.rest.jaxrs.jersey.service;

import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAll() throws Exception;

    Contact findById(long id) throws Exception;

    Contact save(Contact contact) throws Exception;

    Contact update(Contact contact) throws Exception;

    void delete(long id) throws Exception;

}
