package io.github.julianjupiter.rest.jaxrs.jersey.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationResourceConfig extends ResourceConfig {
    private static final String PACKAGES = "io.github.julianjupiter.rest.jaxrs.jersey";

    public ApplicationResourceConfig() {
        packages(PACKAGES);
        register(new ApplicationBinder());
        register(JacksonFeature.class);
        register(ObjectMapperContextResolver.class);
        register(CorsFilter.class);
    }
}