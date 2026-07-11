package com.kousenit.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AstroDemoTest {

    @Test
    @DisplayName("AstroDemo executes without errors")
    void testAstroDemo() {
        // Fetches the crew manifest hosted in this repository, so no
        // reachability guard is needed
        assertDoesNotThrow(() -> AstroDemo.main(new String[]{}));
    }
}
