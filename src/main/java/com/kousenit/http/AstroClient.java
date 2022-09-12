package com.kousenit.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpRequest.newBuilder;

public class AstroClient {

    public String getJsonResponse() {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build();

        HttpRequest request = newBuilder()
                .uri(URI.create("http://api.open-notify.org/astros.json"))
                .GET() // default (could leave that out)
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Headers: " + response.headers());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public HttpResponse<Void> getResponseToHeadRequest(String site) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = newBuilder()
                .uri(URI.create(site))
                .method("HEAD", BodyPublishers.noBody()) // NOTE: .HEAD() in Java 18+
                .build();
        HttpResponse<Void> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public AstroResponse getAstroResponse() {
        // Gson does not work yet
        // return new Gson().fromJson(getJsonResponse(), AstroResponse.class);

        // Moshi works!
//        Moshi moshi = new Moshi.Builder().build();
//        JsonAdapter<AstroResponse> adapter = moshi.adapter(AstroResponse.class);
//        try {
//            return adapter.fromJson(getJsonResponse());
//        } catch (IOException e) {
//            throw new RuntimeException(e.getCause());
//        }

        // Jackson 2 works
        JsonMapper jsonMapper = new JsonMapper();
        try {
            return jsonMapper.readValue(getJsonResponse(), AstroResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
