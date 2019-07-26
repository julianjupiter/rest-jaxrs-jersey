package io.github.julianjupiter.rest.jaxrs.jersey.exception;

import java.time.LocalDateTime;

public class ErrorMessage {
    private String code;
    private String message;
    private transient LocalDateTime timestamp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorMessage withCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessage withMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorMessage withTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}