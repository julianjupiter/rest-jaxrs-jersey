package io.github.julianjupiter.rest.jaxrs.jersey.model.repository;

import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;
import io.github.julianjupiter.rest.jaxrs.jersey.util.JpaUtil;
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
    public List<Contact> findAll() throws Exception {
        try {
            TypedQuery<Contact> query = em.createNamedQuery("Contact.findAll", Contact.class);
            return query.getResultList();
        } catch (Exception exception) {
            LOGGER.error("Unable to retrieve all contacts. {}", exception.getMessage());
            throw new Exception("Unable to retrieve all contacts. " + exception.getMessage());
        }
    }

    @Override
    public Optional<Contact> findById(long id) throws Exception {
        try {
            return Optional.ofNullable(em.find(Contact.class, id));
        } catch (Exception exception) {
            LOGGER.error("Unable to retrieve contact with ID {}.", id);
            throw new Exception("Unable to retrieve contact with ID " + id + ".");
        }
    }

    @Override
    public Optional<Contact> save(Contact contact) throws Exception {
        try {
            JpaUtil.beginTransaction(em);
            em.persist(contact);
            JpaUtil.commitTransaction(em);
            LOGGER.info("Contact created.");
            return Optional.ofNullable(contact);
        } catch (Exception exception) {
            JpaUtil.rollbackTransaction(em);
            LOGGER.error("Unable to create contact.");
            throw new Exception("Unable to create contact");
        }
    }

    @Override
    public Optional<Contact> update(Contact contact) throws Exception {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void delete(long id) throws Exception {
        Optional<Contact> contactOptional = this.findById(id);
        if (contactOptional.isPresent()) {
            try {
                JpaUtil.beginTransaction(em);
                em.remove(contactOptional.get());
                JpaUtil.commitTransaction(em);
                LOGGER.info("Contact deleted.");
            } catch (Exception exception) {
                JpaUtil.rollbackTransaction(em);
                LOGGER.error("Unable to delete contact with ID {}.", id);
                throw new Exception("Unable to delete contact with ID " + id + ".");
            }
        }
    }

}