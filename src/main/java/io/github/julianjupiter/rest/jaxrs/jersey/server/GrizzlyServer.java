package io.github.julianjupiter.rest.jaxrs.jersey.server;

import io.github.julianjupiter.rest.jaxrs.jersey.config.ApplicationResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class GrizzlyServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrizzlyServer.class);
    private static final String SERVICE_NAME = "Contact Services API";
    private static URI baseUri;
    private static final String PROTOCOL = "http://";
    private static final String HOST = "localhost";
    private static final String PATH = "rest-jaxrs-jersey/api/";
    private static final int DEFAULT_PORT = 8080;

    private GrizzlyServer() {

    }

    private static int port(String[] args) {
        if (args.length > 0) {
            String port = args[0];
            try {
                return Integer.valueOf(port);
            } catch (NumberFormatException exception) {
                LOGGER.error("Invalid port number {}", port);
                LOGGER.error("Default port number {} will be used", DEFAULT_PORT);
            }
        }

        return DEFAULT_PORT;
    }

    public static HttpServer startServer(int port) {
        final ResourceConfig rc = new ApplicationResourceConfig();
        baseUri = UriBuilder.fromUri(PROTOCOL + HOST).port(port).path(PATH).build();
        return GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
    }


    public static void run(String[] args) throws IOException {
        int port = port(args);
        try {
            final HttpServer server = startServer(port);
            LOGGER.info("{} started with WADL available at {}application.wadl", SERVICE_NAME, baseUri);
            LOGGER.info("Hit Enter to stop it...");
            System.in.read();
            server.shutdown();
        } catch (Exception exception) {
            LOGGER.error("{}", exception.getMessage());
            LOGGER.error("Exit...");
            System.exit(1);
        }
    }
}