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

        return this.contactService.findById(id)
                .map(contact -> Response.ok().entity(contact).build())
                .orElseThrow(() -> new ContactNotFoundException("Contact with ID " + id + " not found."));
    }

    @POST
    public Response create(Contact contact) throws Exception {
        LOGGER.info("Creating a contact...");

        if (null != contact) {
            LocalDateTime createdAt = contact.getCreatedAt() != null ? contact.getCreatedAt() : LocalDateTime.now();
            contact.setCreatedAt(createdAt);
            return this.contactService.save(contact)
                    .map(contact1 -> Response.status(Response.Status.CREATED).entity(contact).build())
                    .orElseThrow(() -> new RuntimeException("Unable to create resource."));
        } else {
            throw new BadRequestException("Unable to create resource.");
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) throws Exception {
        LOGGER.info("Deleting a contact...");

        return this.contactService.findById(id)
                .map(contact1 -> {
                    try {
                        this.contactService.delete(id);
                    } catch (Exception exception) {
                        LOGGER.error(exception.getMessage());
                        throw new RuntimeException("Error encountered in deleting the resource.");
                    }

                    return Response.noContent().build();
                })
                .orElseThrow(() -> new BadRequestException("Resource with ID " + id + " does not exist."));
    }

}