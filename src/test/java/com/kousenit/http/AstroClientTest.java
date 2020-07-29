package com.kousenit.http;

import org.junit.jupiter.api.Test;

class AstroClientTest {
    private final AstroClient client = new AstroClient();

    @Test
    void checkJsonOutput() {
        System.out.println(client.getJsonResponse());
    }
}