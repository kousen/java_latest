package com.kousenit.patternmatching;

public class UseShapes {
    public static double getArea(Object shape) {
        if (shape instanceof Square s) {
            // Square s = (Square) shape;
            return s.getSide() * s.getSide();
        } else if (shape instanceof Circle c && c.getRadius() > 0) {
            return c.getRadius() * c.getRadius() * Math.PI;
        } else if (shape instanceof Circle c) {
            throw new IllegalArgumentException("Circle must have non-negative radius");
        } else if (shape instanceof Rectangle rect) {
            return rect.base() * rect.height();
        }
        throw new IllegalArgumentException("Only circles, squares, and rectangles work");
    }

    public static void main(String[] args) {
        System.out.println(getArea(new Circle(1.0)));
        System.out.println(getArea(new Square(1.0)));
        System.out.println(getArea(new Rectangle(1.0, 2.0)));
    }
}
