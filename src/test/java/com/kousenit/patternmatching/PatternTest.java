package com.kousenit.patternmatching;

import org.junit.jupiter.api.Test;

import java.util.List;

public class PatternTest {
    record Triangle(double base, double height) {}
    record Rectangle(double length, double width) {}
    record Sphere(double radius) {}

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

    @SuppressWarnings("IfCanBeSwitch")
    private double getArea(Object shape) {
        if (shape instanceof Square s) {
            return s.getSide() * s.getSide();
        } else if (shape instanceof Circle c && c.getRadius() > 0) {
            return c.getRadius() * c.getRadius() * Math.PI;
        } else if (shape instanceof Triangle t) {
            return 0.5 * t.base * t.height;
        } else if (shape instanceof Rectangle r) {
            return r.length * r.width;
        } else {
            throw new IllegalArgumentException("Not supported");
        }
    }

    private double getAreaEnhancedSwitch(Object shape) {
        return switch (shape) {
            case Square s -> s.getSide() * s.getSide();
            case Circle c when c.getRadius() > 0 -> c.getRadius() * c.getRadius() * Math.PI;
            case Triangle t -> 0.5 * t.base * t.height;
            case Rectangle r -> r.length * r.width;
            case Sphere s -> 4 * Math.PI * s.radius * s.radius;
            default -> throw new IllegalArgumentException("Not supported");
        };
    }

    @Test
    void getAreaTest() {
        Square square = new Square(1);
        Circle circle = new Circle(1);
        Triangle triangle = new Triangle(1, 1);
        Rectangle rectangle = new Rectangle(1, 1);
        Sphere sphere = new Sphere(1);
        List<Object> shapes = List.of(square, circle, triangle, rectangle, sphere);
        shapes.stream()
                .map(this::getAreaEnhancedSwitch)
                .forEach(System.out::println);
    }
}
