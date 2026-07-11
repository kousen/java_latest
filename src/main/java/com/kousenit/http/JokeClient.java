package com.kousenit.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.net.http.HttpResponse.*;

public class JokeClient {
    public static final String JOKE_URL = "https://api.chucknorris.io/jokes/random?category=dev";

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(2))
            .build();
    private final Gson gson = new Gson();

    private HttpRequest buildRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(JOKE_URL))
                .GET()
                .build();
    }

    public String getJokeSync(String first, String last) throws IOException, InterruptedException {
        HttpResponse<String> response = HTTP_CLIENT.send(
                buildRequest(),
                BodyHandlers.ofString());
        System.out.println("Status code: " + response.statusCode());
        return getJoke(response.body(), first, last);
    }


    public String getJokeAsync(String first, String last) {
        String json = HTTP_CLIENT.sendAsync(buildRequest(),
                        BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println("Status code: " + response.statusCode());
                    return response;
                })
                .thenApply(HttpResponse::body)
                .join();
        return getJoke(json, first, last);
    }

    // The old ICNDB service substituted hero names on the server;
    // chucknorris.io doesn't, so replace "Chuck Norris" locally
    private String getJoke(String jsonData, String first, String last) {
        return gson.fromJson(jsonData, JokeResponse.class)
                .value()
                .replaceAll("Chuck Norris", "%s %s".formatted(first, last));
    }
}
