package com.kousenit.sealed;

// non-sealed subclass -- can make any subclass of Square
non-sealed class Square extends Shape {
    private final double side;

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }
}