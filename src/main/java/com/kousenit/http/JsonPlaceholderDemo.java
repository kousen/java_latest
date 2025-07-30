package com.kousenit.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class JsonPlaceholderDemo {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public record BlogPost(int userId, int id, String title, String body) {}

    private final Gson gson = new Gson();

    public BlogPost getPost(int id) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("%s/posts/%d".formatted(BASE_URL, id)))
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            var response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to get post: " + response.body());
            }
            return gson.fromJson(response.body(), BlogPost.class);
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException(e);
        }
    }

    // This method blocks on join(), so it's not truly async from the caller's perspective
    // Consider renaming to getPostUsingAsyncInternally() or returning CompletableFuture<BlogPost>
    public BlogPost getPostAsync(int id) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("%s/posts/%d".formatted(BASE_URL, id)))
                .header("Accept", "application/json")
                .GET()
                .build();
        return HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> gson.fromJson(body, BlogPost.class))
                .join();
    }

    // Truly async method that returns CompletableFuture
    public CompletableFuture<BlogPost> getPostTrulyAsync(int id) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("%s/posts/%d".formatted(BASE_URL, id)))
                .header("Accept", "application/json")
                .GET()
                .build();
        return HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println("Status code: " + response.statusCode());
                    if (response.statusCode() != 200) {
                        throw new RuntimeException("Failed to get post: " + response.body());
                    }
                    return response.body();
                })
                .thenApply(body -> gson.fromJson(body, BlogPost.class));
    }

    public BlogPost addPost(BlogPost post) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("%s/posts".formatted(BASE_URL)))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(post)))
                    .build();
            var response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            if (response.statusCode() != 201) {
                throw new RuntimeException("Failed to add post: " + response.body());
            }
            return gson.fromJson(response.body(), BlogPost.class);
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException(e);
        }
    }
}
