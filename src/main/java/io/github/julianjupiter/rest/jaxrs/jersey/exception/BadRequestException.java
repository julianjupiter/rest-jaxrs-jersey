package io.github.julianjupiter.rest.jaxrs.jersey.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
