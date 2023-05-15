package com.example.cucumber;

import io.cucumber.core.backend.ObjectFactory;
import io.micronaut.context.ApplicationContext;
import io.micronaut.grpc.server.GrpcEmbeddedServer;

public final class MicronautObjectFactory implements ObjectFactory {

    ApplicationContext applicationContext;

    @Override
    public void start() {
        if (applicationContext == null) {
            applicationContext = ApplicationContext.builder(GrpcEmbeddedServer.class)
                    .environments("it").start();
        }
    }

    @Override
    public void stop() {
        if (applicationContext != null) {
            applicationContext.stop();
            applicationContext = null;
        }
    }

    @Override
    public <T> T getInstance(Class<T> glueClass) {
        if (applicationContext != null) {
            return applicationContext.getBean(glueClass);
        }
        return null;
    }

    @Override
    public boolean addClass(Class<?> aClass) {
        return true;
    }
}
