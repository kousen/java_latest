package com.kousenit.patternmatching;

import org.junit.jupiter.api.Test;

import java.util.List;

public class PatternTest {
    record Triangle(double base, double height) {}
    record Rectangle(double length, double width) {}

    @Test
    void useRecordsInPatternMatching() {
        Square square = new Square(1);  // class
        Circle circle = new Circle(1);  // class
        Triangle triangle = new Triangle(1, 1); // record
        Rectangle rectangle = new Rectangle(1, 1); // record

        List<Object> objects = List.of(square, circle, triangle, rectangle);
        objects.stream()
                .map(this::getArea)
                .forEach(System.out::println);
    }

    private double getArea(Object shape) {
        if (shape instanceof Square s) {
            return s.getSide() * s.getSide();
        } else if (shape instanceof Circle c) {
            return c.getRadius() * c.getRadius() * Math.PI;
        } else if (shape instanceof Triangle t) {
            return 0.5 * t.base * t.height;
        } else if (shape instanceof Rectangle r) {
            return r.length * r.width;
        } else {
            throw new IllegalArgumentException("Not supported");
        }
    }

    // Pattern matching does not work with switch statements
//    private double getAreaUsingEnhancedSwitch(Object shape) {
//        return switch (shape) {
//            case shape instanceof Square s -> {
//                Square s = (Square) shape;
//                yield s.getSide() * s.getSide();
//            } // s.getSide() * s.getSide();
//            case shape instanceof Circle c -> {
//                Circle c = (Circle) shape;
//                yield c.getRadius() * c.getRadius() * Math.PI;
//            }// c.getRadius() * c.getRadius() * Math.PI;
//            case shape instanceof Triangle t -> {
//                Triangle t = (Triangle) shape;
//                yield 0.5 * t.base * t.height;
//            }// 0.5 * t.base * t.height;
//            case shape instanceof Rectangle r -> {
//                Rectangle r = (Rectangle) shape;
//                yield r.length * r.width;
//            }// r.length * r.width;
//            default -> 0;
//        };
//    }
}
