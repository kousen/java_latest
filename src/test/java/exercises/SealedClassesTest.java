package exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SealedClassesTest {

    // TODO: Create sealed class hierarchy for shapes
    sealed abstract class Shape
            permits Circle, Rectangle, Triangle {
        abstract double area();
    }

    final class Circle extends Shape {
        private final double radius;

        // Constructor and area() implementation
        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        double area() {
            return Math.PI * radius * radius;
        }
    }

    final class Rectangle extends Shape {
        private final double width, height;

        // Constructor and area() implementation
        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        double area() {
            return width * height;
        }
    }

    final class Triangle extends Shape {
        private final double base, height;

        // Constructor and area() implementation
        Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        @Override
        double area() {
            return 0.5 * base * height;
        }
    }

    @Test
    public void createShapes() {
        // TODO: Create instances and calculate areas
         Shape circle = new Circle(5);
         Shape rectangle = new Rectangle(4, 6);
         Shape triangle = new Triangle(3, 4);

         assertEquals(Math.PI * 25, circle.area(), 0.001);
         assertEquals(24, rectangle.area());
         assertEquals(6, triangle.area());
    }

    @Test
    public void exhaustiveSwitchWithSealed() {
        // TODO: Use exhaustive switch with sealed classes
         Shape shape = new Circle(10);

         String description = switch (shape) {
             case Circle c -> "Circle with radius " + c.radius;
             case Rectangle r -> "Rectangle " + r.width + "x" + r.height;
             case Triangle t -> "Triangle with base " + t.base;
             // No default needed!
         };

         assertTrue(description.contains("Circle"));
    }

    // TODO: Create sealed interface hierarchy
     sealed interface Result<T> permits Success, Failure {}
     record Success<T>(T value) implements Result<T> {}
     record Failure<T>(RuntimeException exception) implements Result<T> {}

    @Test
    public void sealedInterfaces() {
        // TODO: Use Result pattern
         Result<Integer> success = new Success<>(42);
         Result<Integer> failure = new Failure<>(new RuntimeException("Not found"));

         String message1 = switch (success) {
             case Success(var value) -> "Got: " + value;
             case Failure(var ex) -> "Error: " + ex.getMessage();
         };

         assertEquals("Got: 42", message1);
    }
}