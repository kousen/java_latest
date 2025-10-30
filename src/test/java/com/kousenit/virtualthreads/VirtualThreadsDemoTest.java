package com.kousenit.virtualthreads;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class VirtualThreadsDemoTest {

    @Test
    @DisplayName("VirtualThreadsDemo executes without errors")
    void testVirtualThreadsDemo() {
        // Test the demo that creates 10,000 virtual threads
        // Each thread sleeps for 1 second - demonstrates virtual threads' efficiency
        assertDoesNotThrow(() -> VirtualThreadsDemo.main(new String[]{}));
    }
}
