package com.kousenit.http;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.headers());
            System.out.println(response.body());

//            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                    .thenApply(HttpResponse::body)
//                    .thenAccept(System.out::println)
//                    .join();

            ISSResponse issResponse = getResponseGson(response);
            System.out.println(issResponse);
//            System.out.printf("The ISS is currently at (%s, %s)%n",
//                    issResponse.issPosition().latitude(),
//                    issResponse.issPosition().longitude());
        }
    }

//    @Nullable
//    private static ISSResponse getResponseMoshi(HttpResponse<String> response) throws IOException {
//        return new Moshi.Builder()
//                .build()
//                .adapter(ISSResponse.class)
//                .fromJson(response.body());
//    }

    private static ISSResponse getResponseGson(HttpResponse<String> response) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson.fromJson(response.body(), ISSResponse.class);
    }
}
