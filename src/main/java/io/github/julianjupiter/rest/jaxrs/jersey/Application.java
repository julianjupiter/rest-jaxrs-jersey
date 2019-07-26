package io.github.julianjupiter.rest.jaxrs.jersey;

import io.github.julianjupiter.rest.jaxrs.jersey.server.GrizzlyServer;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        GrizzlyServer.run(args);
    }
}
