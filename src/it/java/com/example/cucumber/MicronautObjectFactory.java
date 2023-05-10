package com.example.cucumber;

import io.cucumber.core.backend.ObjectFactory;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;

public class MicronautObjectFactory implements ObjectFactory {

    private EmbeddedServer embeddedServer;

    @Override
    public void start() {
        if (embeddedServer == null) {
            embeddedServer = ApplicationContext.run(EmbeddedServer.class);
        }
    }

    @Override
    public void stop() {
        if (embeddedServer != null) {
            embeddedServer.stop();
            embeddedServer = null;
        }
    }

    @Override
    public boolean addClass(Class<?> glueClass) {
        return true;
    }

    @Override
    public <T> T getInstance(Class<T> glueClass) {
        return embeddedServer.getApplicationContext().getBean(glueClass);
    }
}
