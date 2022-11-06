package com.kousenit.http;

import com.google.gson.Gson;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ISSPosition {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Records:
        // - are immutable
        // - autogenerate equals, hashCode, and toString
        // - do NOT have a default constructor
        String url = "http://api.open-notify.org/iss-now.json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.headers());
        System.out.println(response.body());

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

//        ISSResponse issResponse = getResponseMoshi(response);
//        System.out.println(issResponse);
//        System.out.printf("The ISS is currently at (%s, %s)%n",
//                issResponse.iss_position().latitude(),
//                issResponse.iss_position().longitude());
    }

    @Nullable
    private static ISSResponse getResponseMoshi(HttpResponse<String> response) throws IOException {
        return new Moshi.Builder()
                .build()
                .adapter(ISSResponse.class)
                .fromJson(response.body());
    }

    private static ISSResponse getResponseGson(HttpResponse<String> response) {
        return new Gson().fromJson(response.body(), ISSResponse.class);
    }
}
