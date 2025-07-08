package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

public class HttpClientTest {
    
    private final HttpClient client = HttpClient.newHttpClient();
    
    @Test
    public void synchronousGet() throws Exception {
        // Create GET request to httpbin.org/get
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .GET()
                .build();
        
        // Send request and get response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("httpbin.org"));
    }
    
    @Test
    public void asynchronousGet() throws Exception {
        // Create async GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .GET()
                .build();
        
        // Send async and handle response
        CompletableFuture<String> future = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
        
        String body = future.get(5, TimeUnit.SECONDS);
        assertTrue(body.contains("httpbin.org"));
    }
    
    @Test
    public void postWithJson() throws Exception {
        String json = """
            {
                "name": "Java",
                "version": 21
            }
            """;
        
        // Create POST request with JSON body
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("Java"));
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