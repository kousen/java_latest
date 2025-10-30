package com.kousenit.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.UnresolvedAddressException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class ISSPositionTest {

    @BeforeEach
    void setUp() {
        // Check if the API is available before running tests
        try (HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build()) {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.open-notify.org"))
                    .method("HEAD", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<Void> response = client.send(req, HttpResponse.BodyHandlers.discarding());
            assumeTrue(response.statusCode() == 200, "Open Notify API site is down");
        } catch (UnresolvedAddressException | IOException e) {
            assumeFalse(true, "Site is unreachable: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            assumeFalse(true, "Site is unreachable: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("ISSPosition demo executes without errors")
    void testISSPositionDemo() {
        // Test the demo that fetches current ISS position
        // Uses Gson with FieldNamingPolicy for snake_case deserialization
        assertDoesNotThrow(() -> ISSPosition.main(new String[]{}));
    }
}
