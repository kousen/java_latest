package com.kousenit.walker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StackWalkerDemoTest {

    @Test
    @DisplayName("StackWalkerDemo executes without errors")
    void testStackWalkerDemo() {
        // Test the demo that shows how to use StackWalker API
        // to capture and print stack frames
        assertDoesNotThrow(() -> StackWalkerDemo.main(new String[]{}));
    }
}
