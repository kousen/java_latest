package com.kousenit.http;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.net.http.HttpRequest.newBuilder;
import static org.junit.jupiter.api.Assertions.*;

class AstroClientTest {
    private final AstroClient client = new AstroClient();

    @BeforeEach
    void setUp() {
        // Works for a ping, but that may not be reliable
//        Assumptions.assumeTrue(
//                InetAddress.getByName("api.open-notify.org").isReachable(2000),
//                "api.open-notify.org is down");

        // Check response to an HTTP HEAD request
        // statusCode() returns an int, not an enum
        Assumptions.assumeTrue(
                getResponseToHeadRequest().statusCode() == HttpURLConnection.HTTP_OK
        );
    }

    private HttpResponse<Void> getResponseToHeadRequest() {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build();
        HttpRequest req = newBuilder()
                .uri(URI.create("http://api.open-notify.org"))
                .method("HEAD", HttpRequest.BodyPublishers.noBody()) // NOTE: .HEAD() in Java 18+
                .build();
        HttpResponse<Void> response;
        try {
            response = client.send(req, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    @Test
    void headRequest() {
        HttpResponse<Void> response = getResponseToHeadRequest();
        System.out.println("Status code: " + response.statusCode());
        response.headers().map()
                .forEach((key, values) -> System.out.println(key + ": " + values));
    }

    @Test
    void checkJsonOutput() {
        System.out.println(client.getJsonResponse());
    }

    @Test
    void testDeserializeToRecords() {
        AstroResponse response = client.getAstroResponse();
        System.out.println(response);
        assertAll(
                () -> assertEquals(response.message(), "success"),
                () -> assertTrue(response.number() >= 0),
                () -> assertEquals(response.people().size(), response.number())
        );
    }

    @Test
    void getAstroResponseAsync() {
        client.getJsonResponseAsync()
                .thenAccept(System.out::println)
                .join();
    }
}