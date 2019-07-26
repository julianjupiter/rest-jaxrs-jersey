package io.github.julianjupiter.rest.jaxrs.jersey.resource;

import io.github.julianjupiter.rest.jaxrs.jersey.exception.BadRequestException;
import io.github.julianjupiter.rest.jaxrs.jersey.exception.ContactNotFoundException;
import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;
import io.github.julianjupiter.rest.jaxrs.jersey.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("contacts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactResource.class);
    private final ContactService contactService;

    @Inject
    public ContactResource(ContactService contactService) {
        this.contactService = contactService;
    }

    @GET
    public List<Contact> findAll() throws Exception {
        LOGGER.info("Getting all contacts...");

        return this.contactService.findAll();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") long id) throws Exception {
        LOGGER.info("Getting a contact...");

        Contact contact = this.contactService.findById(id);

        if (null == contact) {
            throw new ContactNotFoundException("Contact with ID " + id + " not found.");
        }

        return Response
                .ok()
                .entity(contact)
                .build();
    }

    @POST
    public Response create(Contact contact) throws Exception {
        LOGGER.info("Creating a contact...");

        if (null != contact) {
            LocalDateTime createdAt = contact.getCreatedAt() != null ? contact.getCreatedAt() : LocalDateTime.now();
            contact.setCreatedAt(createdAt);
            this.contactService.save(contact);

            return Response
                    .status(Response.Status.CREATED)
                    .entity(contact)
                    .build();
        } else {
            throw new BadRequestException("Unable to create resource.");
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) throws Exception {
        Contact contact = this.contactService.findById(id);

        if (null != contact) {
            this.contactService.delete(id);

            return Response
                    .noContent()
                    .build();
        } else {
            throw new BadRequestException("Resource with ID " + id + " does not exist.");
        }
    }

}