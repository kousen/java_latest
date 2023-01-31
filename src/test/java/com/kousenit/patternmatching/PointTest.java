package com.kousenit.patternmatching;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @SuppressWarnings({"UnnecessaryLocalVariable", "ConstantValue"})
    @Test
    void patternMatchingEquals() {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);  // p1, p2 are equal but different
        Point p3 = p1;               // p1, p3 both point to same instance
        Point p4 = new Point(5, 6);  // p4 different from p1, p2, p3
        Point p5 = null;             // p5 is null

        assertAll(
                () -> assertEquals(p1, p2, "equal but different objects"),
                () -> assertEquals(p1, p3, "two references to same object are equal"),
                () -> assertSame(p1, p3, "two references to same object"),
                () -> assertNotSame(p2, p3, "equal but different points"),
                () -> assertNotEquals(p1, p4, "not equal"),
                () -> assertNotEquals(p1, p5)
        );
    }
}