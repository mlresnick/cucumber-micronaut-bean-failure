package com.example.helloworld;

import com.example.proto.HelloReply;
import com.example.proto.HelloRequest;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Singleton;
import com.example.proto.HelloWorldGrpc;

@Singleton
public class HelloWorldEndpoint extends HelloWorldGrpc.HelloWorldImplBase {
    @Override
    public void sayHello(HelloRequest request,
                         StreamObserver<HelloReply> responseObserver) {
        final HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
