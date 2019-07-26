package io.github.julianjupiter.rest.jaxrs.jersey.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof ContactNotFoundException) {
            return Response
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(new ErrorMessage().withCode(ErrorCode.E4004).withMessage(exception.getMessage()).withTimestamp(LocalDateTime.now()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else if (exception instanceof BadRequestException) {
            return Response
                    .status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity(new ErrorMessage().withCode(ErrorCode.E4001).withMessage(exception.getMessage()).withTimestamp(LocalDateTime.now()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                    .entity(new ErrorMessage().withCode(ErrorCode.E5000).withMessage(exception.getMessage()).withTimestamp(LocalDateTime.now()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}