package com.kousenit.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonPlaceholderDemo {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public record BlogPost(int userId, int id, String title, String body) {}

    private final Gson gson = new Gson();

    public BlogPost getPost(int id) {
        try (var client = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("%s/posts/%d".formatted(BASE_URL, id)))
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to get post: " + response.body());
            }
            return gson.fromJson(response.body(), BlogPost.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public BlogPost addPost(BlogPost post) {
        try (var client = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("%s/posts".formatted(BASE_URL)))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(post)))
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            if (response.statusCode() != 201) {
                throw new RuntimeException("Failed to add post: " + response.body());
            }
            return gson.fromJson(response.body(), BlogPost.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
