package io.github.julianjupiter.rest.jaxrs.jersey.model.repository;

import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ContactRepositoryImpl implements ContactRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactRepositoryImpl.class);
    @Inject
    private EntityManager em;

    @Override
    public List<Contact> findAll() throws RuntimeException {
        try {
            TypedQuery<Contact> query = em.createNamedQuery("Contact.findAll", Contact.class);
            return query.getResultList();
        } catch (RuntimeException exception) {
            LOGGER.error("Unable to retrieve all contacts. {}", exception.getMessage());
            throw new RuntimeException("Unable to retrieve all contacts. " + exception.getMessage());
        }
    }

    @Override
    public Optional<Contact> findById(long id) throws RuntimeException {
        try {
            return Optional.ofNullable(em.find(Contact.class, id));
        } catch (RuntimeException exception) {
            LOGGER.error("Unable to retrieve contact with ID {}.", id);
            throw new RuntimeException("Unable to retrieve contact with ID " + id + ".");
        }
    }

    @Override
    public Optional<Contact> save(Contact contact) throws RuntimeException {
        try {
            if (contact.getId() == 0) {
                em.persist(contact);
                LOGGER.info("Contact created.");
            } else {
                em.merge(contact);
            }

            return Optional.ofNullable(contact);
        } catch (RuntimeException exception) {
            LOGGER.error("Unable to create contact.");
            throw new RuntimeException("Unable to create contact");
        }
    }

    @Override
    public void deleteById(long id) throws RuntimeException {
        Optional<Contact> contactOptional = this.findById(id);
        if (contactOptional.isPresent()) {
            try {
                em.remove(contactOptional.get());
                LOGGER.info("Contact deleted.");
            } catch (RuntimeException exception) {
                LOGGER.error("Unable to delete contact with ID {}.", id);
                throw new RuntimeException("Unable to delete contact with ID " + id + ".");
            }
        }
    }

}