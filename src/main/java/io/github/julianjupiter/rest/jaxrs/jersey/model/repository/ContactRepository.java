package io.github.julianjupiter.rest.jaxrs.jersey.model.repository;

import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    List<Contact> findAll() throws RuntimeException;

    Optional<Contact> findById(long id) throws RuntimeException;

    Optional<Contact> save(Contact user) throws RuntimeException;

    void deleteById(long id) throws RuntimeException;

}