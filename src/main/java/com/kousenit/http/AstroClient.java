package com.kousenit.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class AstroClient {
    private static final String REQUEST_URL = "http://api.open-notify.org/astros.json";

    public HttpRequest createRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(REQUEST_URL))
                .header("Accept", "application/json")
                .GET()
                .build();
    }

    public HttpResponse<String> sendRequest(HttpRequest request) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getJsonResponse() {
        HttpResponse<String> response = sendRequest(createRequest());
        System.out.printf("Status code: %d%n", response.statusCode());
        System.out.printf("Headers: %s%n", response.headers());
        return response.body();
    }

    public AstroResponse getAstroResponse() {
        return parseJson(getJsonResponse());
    }

    private AstroResponse parseJson(String jsonResponse) {
        // Use your favorite JSON library here
        return new Gson().fromJson(jsonResponse, AstroResponse.class);
    }

    public CompletableFuture<String> getJsonResponseAsync() {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            return httpClient.sendAsync(createRequest(), HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        System.out.printf("Status code: %d%n", response.statusCode());
                        System.out.printf("Headers: %s%n", response.headers());
                        return response.body();
                    });
        }
    }

}