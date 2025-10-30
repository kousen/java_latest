package com.kousenit.refactoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LoopsSortsAndIfsTest {

    @Test
    @DisplayName("Loops, sorts, and ifs (before refactoring) executes without errors")
    void testBeforeRefactoring() {
        // Test the demo showing old-style Java code with
        // explicit loops, anonymous inner classes, and verbose comparators
        assertDoesNotThrow(() ->
            com.kousenit.refactoring.before.LoopsSortsAndIfs.main(new String[]{})
        );
    }

    @Test
    @DisplayName("Loops, sorts, and ifs (after refactoring) executes without errors")
    void testAfterRefactoring() {
        // Test the demo showing modern Java code with
        // streams, method references, and functional style
        assertDoesNotThrow(() ->
            com.kousenit.refactoring.after.LoopsSortsAndIfs.main(new String[]{})
        );
    }
}
