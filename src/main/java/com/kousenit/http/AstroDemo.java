package com.kousenit.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class AstroDemo {

    public static void main(String[] args) {
        record Response(String message, int number, List<NameAndCraft> people) {
            record NameAndCraft(String name, String craft) {}
        }

        Gson gson = new Gson();

        try (var client = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.open-notify.org/astros.json"))
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> httpResponse =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.statusCode());
            System.out.println(httpResponse.headers());
            Response response = gson.fromJson(httpResponse.body(), Response.class);
            System.out.println(response);
            System.out.println("There are " + response.number() + " people in space.");
            response.people().forEach(person -> System.out.println(person.name() + " is on the " + person.craft()));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
