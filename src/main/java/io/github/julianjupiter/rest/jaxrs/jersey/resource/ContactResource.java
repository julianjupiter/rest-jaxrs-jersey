package io.github.julianjupiter.rest.jaxrs.jersey.resource;

import io.github.julianjupiter.rest.jaxrs.jersey.exception.BadRequestException;
import io.github.julianjupiter.rest.jaxrs.jersey.exception.ContactNotFoundException;
import io.github.julianjupiter.rest.jaxrs.jersey.model.domain.Contact;
import io.github.julianjupiter.rest.jaxrs.jersey.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    public Response findById(@PathParam("id") long id) {
        LOGGER.info("Getting a contact...");

        return this.contactService.findById(id)
                .map(contact -> Response.ok().entity(contact).build())
                .orElseThrow(() -> new ContactNotFoundException("Contact with ID " + id + " not found."));
    }

    @POST
    public Response create(Contact contact) {
        LOGGER.info("Creating a contact...");

        if (null != contact) {
            LocalDateTime createdAt = contact.getCreatedAt() != null ? contact.getCreatedAt() : LocalDateTime.now();
            contact.setCreatedAt(createdAt);
            return this.contactService.save(contact)
                    .map(c -> Response.status(Response.Status.CREATED).entity(c).build())
                    .orElseThrow(() -> new RuntimeException("Unable to create resource."));
        } else {
            throw new BadRequestException("Unable to create resource.");
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") long id)  {
        LOGGER.info("Deleting a contact...");

        return this.contactService.findById(id)
                .map(c -> {
                    this.contactService.deleteById(id);
                    return Response.noContent().build();
                })
                .orElseThrow(() -> new BadRequestException("Resource with ID " + id + " does not exist."));
    }

}