package com.kousenit.enhancedswitch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DivisibleBy3Test {

    @Test
    @DisplayName("DivisibleBy3 demo executes without errors")
    void testDivisibleBy3Demo() {
        // Test the demo showing enhanced switch expressions
        // Demonstrates both return-value and void switch statements
        assertDoesNotThrow(() -> DivisibleBy3.main(new String[]{}));
    }
}
