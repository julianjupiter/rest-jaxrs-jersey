package io.github.julianjupiter.rest.jaxrs.jersey.exception;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
