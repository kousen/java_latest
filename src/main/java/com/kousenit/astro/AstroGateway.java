package com.kousenit.astro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kousenit.http.AstroResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AstroGateway implements Gateway<AstroResponse> {
    private static final String DEFAULT_URL = "http://api.open-notify.org/astros.json";
    private final String url;

    public AstroGateway() {
        this(DEFAULT_URL);
    }

    public AstroGateway(String url) {
        this.url = url;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Result<AstroResponse> getResult() {
        // As of Java 21, HttpClient implements AutoCloseable
        try (var client = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .GET() // default
                    .build();
            HttpResponse<String> httpResponse =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.statusCode());
            return new Success<>(
                    objectMapper.readValue(httpResponse.body(), AstroResponse.class));
        } catch (IOException | InterruptedException e) {
            return new Failure<>(new RuntimeException(e));
        }
    }
}
