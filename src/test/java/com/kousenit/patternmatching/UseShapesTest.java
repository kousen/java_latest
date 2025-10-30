package com.kousenit.patternmatching;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UseShapesTest {

    @Test
    @DisplayName("Calculate area of square using pattern matching")
    void testSquareArea() {
        double area = UseShapes.getArea(new Square(5.0));
        assertEquals(25.0, area, 0.001);
    }

    @Test
    @DisplayName("Calculate area of circle using pattern matching")
    void testCircleArea() {
        double area = UseShapes.getArea(new Circle(1.0));
        assertEquals(Math.PI, area, 0.001);
    }

    @Test
    @DisplayName("Calculate area of rectangle using record pattern")
    void testRectangleArea() {
        double area = UseShapes.getArea(new Rectangle(2.0, 3.0));
        assertEquals(6.0, area, 0.001);
    }

    @Test
    @DisplayName("Throw exception for invalid circle with negative radius")
    void testInvalidCircle() {
        assertThrows(IllegalArgumentException.class,
                () -> UseShapes.getArea(new Circle(-1.0)));
    }

    @Test
    @DisplayName("Throw exception for unsupported shape type")
    void testUnsupportedShape() {
        assertThrows(IllegalArgumentException.class,
                () -> UseShapes.getArea("not a shape"));
    }

    @Test
    @DisplayName("UseShapes main method executes (catches exception)")
    void testMainMethod() {
        // The main method prints areas and ends with an exception for negative radius
        // We expect it to throw, so we catch and verify
        assertThrows(IllegalArgumentException.class,
                () -> UseShapes.main(new String[]{}));
    }
}
