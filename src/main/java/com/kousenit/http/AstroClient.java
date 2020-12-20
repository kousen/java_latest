package com.kousenit.http;

import com.google.gson.*;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AstroClient {

    public String getJsonResponse() {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.open-notify.org/astros.json"))
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.headers());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

//    public AstroResponse getAstroResponse() {
        // Gson does not work yet
//        return new Gson().fromJson(getJsonResponse(), AstroResponse.class);

        // Neither does Moshi
//        Moshi moshi = new Moshi.Builder().build();
//        JsonAdapter<AstroResponse> adapter = moshi.adapter(AstroResponse.class);
//        try {
//            return adapter.fromJson(getJsonResponse());
//        } catch (IOException e) {
//            throw new RuntimeException(e.getCause());
//        }
//    }
}
