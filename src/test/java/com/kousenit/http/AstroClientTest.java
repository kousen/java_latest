package com.kousenit.http;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

// This service doesn't handle concurrent requests well
@Execution(ExecutionMode.SAME_THREAD)
class AstroClientTest {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private final AstroClient client = new AstroClient();

    @BeforeEach
    void setUp() {
        Assumptions.assumeTrue(
                getResponseToHeadRequest().statusCode() == HttpURLConnection.HTTP_OK
        );
    }

    @Test
    void getResponse() {
        HttpResponse<String> response = client.sendRequest(client.createRequest());
        System.out.printf("Status code: %d%n", response.statusCode());
        System.out.printf("Headers: %s%n", response.headers());
        System.out.printf("Body: %s%n", response.body());
        
        // Handle potential network issues gracefully
        if (response.statusCode() != 200) {
            System.out.printf("API returned status %d instead of 200. This can happen in CI environments.%n", 
                response.statusCode());
            assumeTrue(response.statusCode() == 200, 
                "API service unavailable (status: " + response.statusCode() + ")");
        }
        
        assertThat(response.body()).contains("success");
    }

    private HttpResponse<Void> getResponseToHeadRequest() {
        try {
            HttpRequest req = newBuilder()
                    .uri(URI.create("http://api.open-notify.org"))
                    .method("HEAD", HttpRequest.BodyPublishers.noBody()) // NOTE: .HEAD() in Java 18+
                    .build();
            return HTTP_CLIENT.send(req, HttpResponse.BodyHandlers.discarding());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    @Test
    void headRequest() {
        HttpResponse<Void> response = getResponseToHeadRequest();
        System.out.printf("Status code: %d%n", response.statusCode());
        response.headers()
                .map()
                .forEach((key, values) -> System.out.printf("%s: %s%n", key, values));
    }

    @Test
    void checkJsonOutput() {
        System.out.println(client.getJsonResponse());
    }

    @Test
    void testDeserializeToRecords() {
        try {
            AstroResponse response = client.getAstroResponse();
            System.out.println(response);
            assertAll(
                    () -> assertEquals("success", response.message()),
                    () -> assertTrue(response.number() >= 0),
                    () -> assertEquals(response.people().size(), response.number())
            );
            System.out.printf("There are %d people in space%n", response.number());
            response.people().forEach(System.out::println);
        } catch (Exception e) {
            assumeTrue(false, "Network or API issue: " + e.getMessage());
        }
    }

    @Test
    void getAstroResponseAsync() {
        client.getJsonResponseAsync()
                .thenAccept(System.out::println)
                .join();
    }
}