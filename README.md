# cucumber-micronaut-bean-failure

This repo contains an implementation of a gRPC service using Micronaut and a Cucumber integration test that fails to start the embedded Micronaut gRPC server.

In addition, there is a disabled unit test demonstrating that the service actually works without Cucumber.

## Environment setup

Clone this repo.

## To run the integration test

This reproduces the issue reported in the StackOverflow article [Cucumber Micronaut test: Embedded gRPC server not starting](https://stackoverflow.com/questions/76220473/cucumber-micronaut-test-embedded-grpc-server-not-starting)

`mvn clean verify`

## To run the unit test
1. In one terminal session, run the gRPC server: `mvn compile exec:exec`
2. In another terminal session
   1. Remove, or comment out, the `@Disabled` annotation on the unit test in `src/test/java/com/example/helloworld/HelloWorldTest.java`
   2. Run the unit test, `mvn install`
