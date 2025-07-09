package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

public class HttpClientTest {
    
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    @Test
    public void synchronousGet() throws Exception {
        // Create GET request to JSONPlaceholder
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();
        
        // Send request and get response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("userId"));
        assertTrue(response.body().contains("title"));
    }
    
    @Test
    public void asynchronousGet() throws Exception {
        // Create async GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/1"))
                .GET()
                .build();
        
        // Send async and handle response
        CompletableFuture<String> future = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
        
        String body = future.get(5, TimeUnit.SECONDS);
        assertTrue(body.contains("Leanne Graham"));
        assertTrue(body.contains("Bret"));
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
        
        // Create POST request with JSON body
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(201, response.statusCode()); // 201 Created
        assertTrue(response.body().contains("Learning Modern Java"));
        assertTrue(response.body().contains("id")); // JSONPlaceholder adds an ID
    }
    
    @Test
    public void customClient() {
        // Create client with timeout and redirect policy
        HttpClient customClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        
        assertNotNull(customClient);
    }
}