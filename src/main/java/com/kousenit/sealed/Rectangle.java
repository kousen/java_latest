package com.kousenit.sealed;

sealed class Rectangle extends Shape
        permits TransparentRectangle, FilledRectangle {

    protected boolean transparent;
    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return length * width;
    }
}

final class TransparentRectangle extends Rectangle {
    public TransparentRectangle(double length, double width) {
        super(length, width);
        transparent = true;
    }
}

final class FilledRectangle extends Rectangle {
    public FilledRectangle(double length, double width) {
        super(length, width);
    }
}

