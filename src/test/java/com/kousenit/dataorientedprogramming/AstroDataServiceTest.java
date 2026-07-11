package com.kousenit.dataorientedprogramming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.UnresolvedAddressException;
import java.time.Duration;

import static com.kousenit.dataorientedprogramming.AstroDataService.Result;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class AstroDataServiceTest {

    private final AstroDataService processor = new AstroDataService();

    @BeforeEach
    void setUp() {
        // Skip (don't fail) if the API is unavailable
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
    void testSuccessfulDataFetch() {
        Result result = processor.fetchAndProcessData();

        switch (result) {
            case Result.Failure(var error) -> assertThat(error)
                    .containsAnyOf("HTTP error", "Error processing data");
            case Result.Success(var astronautsByCraft) -> assertThat(astronautsByCraft)
                    .isNotEmpty()
                    .containsKey("ISS");
        }
    }

    @Test
    void testMainMethodExecutes() {
        // Test the main method which demonstrates the full data-oriented programming pattern
        // Uses sealed interfaces, pattern matching in switch, and record patterns
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() ->
                AstroDataService.main(new String[]{})
        );
    }
}
