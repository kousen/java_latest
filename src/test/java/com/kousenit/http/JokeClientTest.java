package com.kousenit.http;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JokeClientTest {
    private final Logger logger = Logger.getLogger(JokeClientTest.class.getName());

    private final JokeClient client = new JokeClient();
    private final String heroFirstName = "Chris";
    private final String heroLastName = "Hayes";

    @BeforeEach
    void setUp() throws IOException {
        Assumptions.assumeTrue(
                InetAddress.getByName("icndb.com").isReachable(2000),
                "ICNDB API site is down");
    }

    @Test
    void getJokeSync() throws IOException, InterruptedException {
        String joke = client.getJokeSync(heroFirstName, heroLastName);
        logger.info(joke);
        assertTrue(joke.contains(heroFirstName) ||
                           joke.contains(heroLastName));
    }

    @Test
    void getJokeAsync() throws ExecutionException, InterruptedException {
        String joke = client.getJokeAsync(heroFirstName, heroLastName);
        logger.info(joke);
        assertTrue(joke.contains(heroFirstName) ||
                           joke.contains(heroLastName));
    }
}