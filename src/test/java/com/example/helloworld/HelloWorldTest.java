package com.example.helloworld;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.example.proto.HelloReply;
import com.example.proto.HelloRequest;

import io.grpc.stub.StreamObserver;

class HelloWorldTest {

    @Disabled("Requires a running gRPC server")
    @Test
    void testHelloWorld() {
        var helloWorldEndpoint = new HelloWorldEndpoint();
        helloWorldEndpoint.sayHello(HelloRequest.newBuilder().setName("Fred").build(),
                new StreamObserver<>() {

                    @Override
                    public void onNext(HelloReply value) {
                        System.out.println("onNext: " + value);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t);
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }
                });
    }
}
