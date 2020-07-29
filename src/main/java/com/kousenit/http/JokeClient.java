package com.kousenit.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

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
                HttpResponse.BodyHandlers.ofString());
        return getJoke(response.body());
    }


    public String getJokeAsync(String first, String last) throws ExecutionException, InterruptedException {
        String json = client.sendAsync(buildRequest(first, last),
                                       HttpResponse.BodyHandlers.ofString())
                            .thenApply(HttpResponse::body)
                            .get();
        return getJoke(json);
    }

    private String getJoke(String jsonData) {
        return gson.fromJson(jsonData, JokeResponse.class)
                   .getValue().getJoke();
    }
}
