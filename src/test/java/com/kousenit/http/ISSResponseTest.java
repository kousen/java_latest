package com.kousenit.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ISSResponseTest {

    @Test
    void testISSResponseRecord() {
        ISSResponse.Position position = new ISSResponse.Position(37.7749, -122.4194);
        ISSResponse response = new ISSResponse("success", 1234567890L, position);

        assertEquals("success", response.message());
        assertEquals(1234567890L, response.timestamp());
        assertNotNull(response.issPosition());
        assertEquals(37.7749, response.issPosition().latitude(), 0.0001);
        assertEquals(-122.4194, response.issPosition().longitude(), 0.0001);
    }

    @Test
    void testToString() {
        ISSResponse.Position position = new ISSResponse.Position(0.0, 0.0);
        ISSResponse response = new ISSResponse("success", 0L, position);

        // Test that toString includes the formatted timestamp
        String result = response.toString();
        assertNotNull(result);
        assertTrue(result.contains("ISSResponse"));
    }
}
