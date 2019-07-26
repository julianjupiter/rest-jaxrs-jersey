package io.github.julianjupiter.rest.jaxrs.jersey.service;

import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;
import io.github.julianjupiter.rest.jaxrs.jersey.model.repository.ContactRepository;

import javax.inject.Inject;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Inject
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAll() throws Exception {
        return this.contactRepository.findAll();
    }

    @Override
    public Contact findById(long id) throws Exception {
        return this.contactRepository.findById(id);
    }

    @Override
    public Contact save(Contact user) throws Exception {
        return this.contactRepository.save(user);
    }

    @Override
    public Contact update(Contact user) throws Exception {
        return this.contactRepository.update(user);
    }

    @Override
    public void delete(long id) throws Exception {
        this.contactRepository.delete(id);
    }

}