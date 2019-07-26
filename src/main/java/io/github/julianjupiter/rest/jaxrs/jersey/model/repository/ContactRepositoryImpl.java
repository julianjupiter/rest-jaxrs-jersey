package io.github.julianjupiter.rest.jaxrs.jersey.model.repository;

import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;
import io.github.julianjupiter.rest.jaxrs.jersey.util.JpaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
    public Contact findById(long id) throws Exception {
        try {
            return em.find(Contact.class, id);
        } catch (Exception exception) {
            LOGGER.error("Unable to retrieve contact with ID {}.", id);
            throw new Exception("Unable to retrieve contact with ID " + id + ".");
        }
    }

    @Override
    public Contact save(Contact contact) throws Exception {
        try {
            JpaUtil.beginTransaction(em);
            em.persist(contact);
            JpaUtil.commitTransaction(em);
            LOGGER.info("Contact created.");
            return contact;
        } catch (Exception exception) {
            JpaUtil.rollbackTransaction(em);
            LOGGER.error("Unable to create contact.");
            throw new Exception("Unable to create contact");
        }
    }

    @Override
    public Contact update(Contact contact) throws Exception {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void delete(long id) throws Exception {
        Contact contact = this.findById(id);
        try {
            JpaUtil.beginTransaction(em);
            em.remove(contact);
            JpaUtil.commitTransaction(em);
            LOGGER.info("Contact deleted.");
        } catch (Exception exception) {
            JpaUtil.rollbackTransaction(em);
            LOGGER.error("Unable to delete contact with ID {}.", id);
            throw new Exception("Unable to delete contact with ID " + id + ".");
        }
    }

}