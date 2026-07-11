package com.kousenit.http;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JokeResponseTest {

    @Test
    void gsonMapsJsonToRecord() {
        String json = """
                {
                    "categories": ["dev"],
                    "id": "abc123",
                    "value": "Chuck Norris writes code that optimizes itself."
                }
                """;

        JokeResponse response = new Gson().fromJson(json, JokeResponse.class);

        assertAll(
                () -> assertEquals("abc123", response.id()),
                () -> assertEquals("Chuck Norris writes code that optimizes itself.",
                        response.value()),
                () -> assertEquals(List.of("dev"), response.categories())
        );
    }
}
