package io.github.julianjupiter.rest.jaxrs.jersey.service;

import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    List<Contact> findAll();

    Optional<Contact> findById(long id);

    Optional<Contact> save(Contact user);

    void deleteById(long id);

}
