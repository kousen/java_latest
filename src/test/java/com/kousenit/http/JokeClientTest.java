package com.kousenit.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.UnresolvedAddressException;
import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class JokeClientTest {
    private final Logger logger = Logger.getLogger(JokeClientTest.class.getName());

    private final JokeClient client = new JokeClient();
    private final String heroFirstName = "Venkat";
    private final String heroLastName = "Subramaniam";

    @BeforeEach
    void setUp() {
        try (HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build()) {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(JokeClient.JOKE_URL))
                    .HEAD()
                    .build();
            HttpResponse<Void> response = client.send(req, HttpResponse.BodyHandlers.discarding());
            assumeTrue(response.statusCode() == 200, "chucknorris.io API is down");
        } catch (UnresolvedAddressException | IOException e) {
            assumeFalse(true, "Site is unreachable: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            assumeFalse(true, "Site is unreachable: " + e.getMessage());
        }
    }

    @Test
    void getJokeSync() throws IOException, InterruptedException {
        String joke = client.getJokeSync(heroFirstName, heroLastName);
        logger.info(joke);
        assertFalse(joke.isBlank());
        assertFalse(joke.contains("Chuck Norris"), "Hero name should replace Chuck Norris");
    }

    @Test
    void getJokeAsync() {
        String joke = client.getJokeAsync(heroFirstName, heroLastName);
        logger.info(joke);
        assertFalse(joke.isBlank());
        assertFalse(joke.contains("Chuck Norris"), "Hero name should replace Chuck Norris");
    }
}
