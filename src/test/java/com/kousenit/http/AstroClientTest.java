package com.kousenit.http;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AstroClientTest {
    private static final String DATA_URL =
            "https://raw.githubusercontent.com/kousen/java_latest/main/data/astro_data.json";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private final AstroClient client = new AstroClient();

    @Test
    void getResponse() {
        HttpResponse<String> response = client.sendRequest(client.createRequest());
        System.out.printf("Status code: %d%n", response.statusCode());
        System.out.printf("Headers: %s%n", response.headers());
        System.out.printf("Body: %s%n", response.body());

        assertEquals(200, response.statusCode());
        assertThat(response.body()).contains("success");
    }

    @Test
    void headRequest() throws IOException, InterruptedException {
        HttpRequest req = newBuilder()
                .uri(URI.create(DATA_URL))
                .HEAD()
                .build();
        HttpResponse<Void> response = HTTP_CLIENT.send(req, HttpResponse.BodyHandlers.discarding());
        System.out.printf("Status code: %d%n", response.statusCode());
        response.headers()
                .map()
                .forEach((key, values) -> System.out.printf("%s: %s%n", key, values));
        assertEquals(200, response.statusCode());
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
                () -> assertEquals("success", response.message()),
                () -> assertTrue(response.number() > 0),
                () -> assertEquals(response.people().size(), response.number())
        );
        System.out.printf("There are %d people in space%n", response.number());
        response.people().forEach(System.out::println);
    }

    @Test
    void getAstroResponseAsync() {
        String response = client.getJsonResponseAsync()
                .thenApply(json -> {
                    System.out.println(json);
                    return json;
                })
                .join();

        assertThat(response).isNotNull().contains("success");
    }
}
