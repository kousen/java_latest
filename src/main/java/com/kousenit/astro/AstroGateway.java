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
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
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
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .GET() // default
                    .build();
            HttpResponse<String> httpResponse =
                    HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.statusCode());
            if (httpResponse.statusCode() != 200) {
                return new Failure<>(new RuntimeException("HTTP error: " + httpResponse.statusCode()));
            }
            return new Success<>(
                    objectMapper.readValue(httpResponse.body(), AstroResponse.class));
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            return new Failure<>(new RuntimeException(e));
        }
    }
}
