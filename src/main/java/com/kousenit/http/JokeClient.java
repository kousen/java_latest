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
    private final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(2))
            .build();
    private final Gson gson = new Gson();

    private HttpRequest buildRequest(String first, String last) {
        String jokeUrl = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";
        String url = String.format("%s&firstName=%s&lastName=%s", jokeUrl, first, last);
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    public String getJokeSync(String first, String last) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(
                buildRequest(first, last),
                BodyHandlers.ofString());
        System.out.println("Status code: " + response.statusCode());
        return getJoke(response.body());
    }


    public String getJokeAsync(String first, String last) {
        String json = client.sendAsync(buildRequest(first, last),
                        BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println("Status code: " + response.statusCode());
                    return response;
                })
                .thenApply(HttpResponse::body)
                .join();
        return getJoke(json);
    }

    private String getJoke(String jsonData) {
        return gson.fromJson(jsonData, JokeResponse.class)
                .getValue()
                .getJoke();
    }
}
