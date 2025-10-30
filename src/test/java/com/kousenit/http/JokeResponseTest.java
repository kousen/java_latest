package com.kousenit.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JokeResponseTest {

    @Test
    void testJokeResponseGettersAndSetters() {
        JokeResponse response = new JokeResponse();
        response.setType("success");

        assertEquals("success", response.getType());

        JokeResponse.Value value = response.new Value();
        value.setId(123);
        value.setJoke("Test joke");
        value.setCategories(new String[]{"nerdy"});

        response.setValue(value);

        assertNotNull(response.getValue());
        assertEquals(123, response.getValue().getId());
        assertEquals("Test joke", response.getValue().getJoke());
        assertArrayEquals(new String[]{"nerdy"}, response.getValue().getCategories());
    }
}
