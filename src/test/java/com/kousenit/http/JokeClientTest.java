package com.kousenit.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class JokeClientTest {
    private final Logger logger = Logger.getLogger(JokeClientTest.class.getName());

    private final JokeClient client = new JokeClient();
    private final String heroFirstName = "Venkat";
    private final String heroLastName = "Subramaniam";

    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        // Sometimes this works
        assumeTrue(
                InetAddress.getByName("icndb.com")
                        .isReachable(2000),
                "ICNDB API site is down");

        // But this is more reliable (requires Java 18 for HEAD request method)
        HttpResponse<Void> response = HttpClient.newHttpClient()
                .send(HttpRequest.newBuilder()
                                .uri(URI.create("http://icndb.com"))
                                .HEAD()
                        // alternative is ".method("HEAD", BodyPublishers.noBody())"
                                .build(),
                        HttpResponse.BodyHandlers.discarding());
        assumeTrue(response.statusCode() == 200, "ICNDB API site is down");
    }

    @Test
    void getJokeSync() throws IOException, InterruptedException {
        String joke = client.getJokeSync(heroFirstName, heroLastName);
        logger.info(joke);
        assertTrue(joke.contains(heroFirstName) ||
                joke.contains(heroLastName));
    }

    @Test
    void getJokeAsync() {
        String joke = client.getJokeAsync(heroFirstName, heroLastName);
        logger.info(joke);
        assertTrue(joke.contains(heroFirstName) ||
                joke.contains(heroLastName));
    }
}