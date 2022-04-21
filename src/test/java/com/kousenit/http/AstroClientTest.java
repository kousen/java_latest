package com.kousenit.http;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class AstroClientTest {
    private final AstroClient client = new AstroClient();

    @BeforeEach
    void setUp() {
        // Works for a ping, but that may not be reliable
//        Assumptions.assumeTrue(
//                InetAddress.getByName("api.open-notify.org").isReachable(2000),
//                "api.open-notify.org is down");

        // Check response to an HTTP HEAD request
        // statusCode() returns an int, not an enum
        Assumptions.assumeTrue(
                client.getResponseToHeadRequest("http://api.open-notify.org")
                        .statusCode() == HttpURLConnection.HTTP_OK
        );
    }

    @Test
    void headRequest() {
        HttpResponse<Void> response = client.getResponseToHeadRequest("http://api.open-notify.org");
        System.out.println("Status code: " + response.statusCode());
        response.headers().map()
                .forEach((key, values) -> System.out.println(key + ": " + values));
    }

    @Test
    void checkJsonOutput() {
        System.out.println(client.getJsonResponse());
    }

    @Test
    void testDeserializeToRecords() {
        AstroResponse response = client.getAstroResponse();
        System.out.println(response);
        assertAll(
                () -> assertEquals(response.message(), "success"),
                () -> assertTrue(response.number() >= 0),
                () -> assertEquals(response.people().size(), response.number())
        );
    }
}