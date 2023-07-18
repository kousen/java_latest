package com.kousenit.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpRequest.newBuilder;

public class AstroClient {
    private final HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build();

    private final HttpRequest request = newBuilder()
                .uri(URI.create("http://api.open-notify.org/astros.json"))
                .header("Accept", "application/json")
                .GET() // default (could leave that out)
                .build();

    public String getJsonResponse() {
        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Headers: " + response.headers());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<String> getJsonResponseAsync() {
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println("Status code: " + response.statusCode());
                    System.out.println("Headers: " + response.headers());
                    return response.body();
                });
    }

    public HttpResponse<Void> getResponseToHeadRequest(String site) {
        HttpRequest req = newBuilder()
                .uri(URI.create(site))
                .method("HEAD", BodyPublishers.noBody()) // NOTE: .HEAD() in Java 18+
                .build();
        HttpResponse<Void> response;
        try {
            response = client.send(req, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public AstroResponse getAstroResponse() {
        // Gson works as of version 2.10
        return new Gson().fromJson(getJsonResponse(), AstroResponse.class);

        // Moshi works!
//        Moshi moshi = new Moshi.Builder().build();
//        JsonAdapter<AstroResponse> adapter = moshi.adapter(AstroResponse.class);
//        try {
//            return adapter.fromJson(getJsonResponse());
//        } catch (IOException e) {
//            throw new RuntimeException(e.getCause());
//        }

        // Jackson 2 works
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return objectMapper.readValue(getJsonResponse(), AstroResponse.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e.getMessage());
//        }
    }
}