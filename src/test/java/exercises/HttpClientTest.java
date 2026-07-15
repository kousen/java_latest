package exercises;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpClientTest {

    // Note: In production code, prefer static final for HttpClient
    // For tests, instance fields are acceptable
    private final HttpClient client = HttpClient.newHttpClient();

    @Test
    public void synchronousGet() throws Exception {
        // TODO: Create GET request to JSONPlaceholder
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET() // Default
                .build();

        // TODO: Send request and get response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body()
                .contains("userId"));
        assertTrue(response.body()
                .contains("title"));

        System.out.println("Response body: " + response.body());
    }

    @Test
    public void asynchronousGet() throws Exception {
        // TODO: Create async GET request for user data
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/1"))
                .GET()
                .build();

        // TODO: Send async and handle response
        CompletableFuture<String> future = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);

        String body = future.get(5, TimeUnit.SECONDS);
        assertTrue(body.contains("Leanne Graham"));
        assertTrue(body.contains("Bret"));
        System.out.println(body);
    }

    @Test
    public void postWithJson() throws Exception {
        String json = """
                {
                    "title": "Learning Modern Java",
                    "body": "Java 21 has many great features",
                    "userId": 1
                }
                """;

        // TODO: Create POST request with JSON body
         HttpRequest request = HttpRequest.newBuilder()
             .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
             .header("Content-Type", "application/json")
             .POST(HttpRequest.BodyPublishers.ofString(json))
             .build();

         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
         assertEquals(201, response.statusCode()); // 201 Created
         assertTrue(response.body().contains("Learning Modern Java"));
         assertTrue(response.body().contains("id")); // JSONPlaceholder adds an ID
            System.out.println("Response body: " + response.body());
    }

    @Test
    public void customClient() {
        // TODO: Create client with timeout and redirect policy
        // HttpClient customClient = HttpClient.newBuilder()
        //     ...
        //     .build();

        // assertNotNull(customClient);
    }
}