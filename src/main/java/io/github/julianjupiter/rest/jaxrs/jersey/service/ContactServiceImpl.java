package io.github.julianjupiter.rest.jaxrs.jersey.service;

import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;
import io.github.julianjupiter.rest.jaxrs.jersey.model.repository.ContactRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Inject
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAll() {
        return this.contactRepository.findAll();
    }

    @Override
    public Optional<Contact> findById(long id) {
        return this.contactRepository.findById(id);
    }

    @Override
    public Optional<Contact> save(Contact user) {
        return this.contactRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        this.contactRepository.deleteById(id);
    }

}