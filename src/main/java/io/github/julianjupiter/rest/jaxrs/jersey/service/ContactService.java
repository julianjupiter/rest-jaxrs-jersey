package io.github.julianjupiter.rest.jaxrs.jersey.service;

import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    List<Contact> findAll() throws Exception;

    Optional<Contact> findById(long id) throws Exception;

    Optional<Contact> save(Contact user) throws Exception;

    Optional<Contact> update(Contact user) throws Exception;

    void delete(long id) throws Exception;

}
