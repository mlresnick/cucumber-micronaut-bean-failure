package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import com.example.proto.HelloRequest;
import com.example.proto.HelloWorldGrpc;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.grpc.ManagedChannelBuilder;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

@Singleton
public class ServiceStepDefinitions {

    @Value("${grpc.server.port}")
    private Integer grpcServerPort;

    private HelloRequest request;

    private String result;

    @Given("a new request")
    public void a_new_request() {
        request = HelloRequest.newBuilder().setName("Fred").build();
    }

    @When("the request is sent")
    public void the_request_is_sent() throws InterruptedException {
        var channel = ManagedChannelBuilder.forAddress("localhost", grpcServerPort).usePlaintext()
                .build();
        try {
            var blockingStub = HelloWorldGrpc.newBlockingStub(channel);
            var response = blockingStub.sayHello(request);
            result = response.getMessage();
        }
        finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    @Then("the response should be successful")
    public void the_response_should_be_successful() {
        assertEquals("Hello Fred", result);
    }
}
