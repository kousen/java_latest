package com.kousenit.sealed;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShapesTest {
    private final Circle circle = new Circle(1);
    private final Square square = new Square(1);
    private final Rectangle rectangle = new Rectangle(1, 2);
    private final TransparentRectangle transparentRectangle =
            new TransparentRectangle(1, 2);
    private final FilledRectangle filledRectangle =
            new FilledRectangle(1, 2);

    @Test
    void getAreas() {
        assertAll(
                () -> assertEquals(Math.PI, circle.getArea(), 0.001),
                () -> assertEquals(1, square.getArea(), 0.001),
                () -> assertEquals(2, rectangle.getArea(), 0.001),
                () -> assertEquals(2, transparentRectangle.getArea(), 0.001),
                () -> assertTrue(transparentRectangle.isTransparent()),  // not about area
                () -> assertEquals(2, filledRectangle.getArea(), 0.001),
                () -> assertFalse(filledRectangle.isTransparent())      // not about area
        );
    }

    @Test
    void enhancedSwitch() {
        List<Shape> shapes = List.of(circle, square, rectangle,
                transparentRectangle, filledRectangle);

        // Pattern matching for switch (preview of Java 17)
        for (Shape shape : shapes) {
              // enhanced switch
            double value = switch (shape) {
                case Circle c -> c.getRadius();
                case Square sq -> sq.getSide();
                case TransparentRectangle trect -> trect.getLength();
                case FilledRectangle filledRect -> filledRect.getWidth();
                case Rectangle rect -> rect.getWidth();
                   // no default clause needed
            };

//            double value = 0.;
//            // Pattern matching works
//            if (shape instanceof Circle c) {
//                value = c.getRadius();
//            } else if (shape instanceof Square sq) {
//                value = sq.getSide();
//            } else if (shape instanceof TransparentRectangle tRect) {
//                value = tRect.getLength();
//            } else if (shape instanceof FilledRectangle filledRect) {
//                value = filledRect.getWidth();
//            } else if (shape instanceof Rectangle rect) {
//                value = rect.getWidth();
//            }
        }
    }
}