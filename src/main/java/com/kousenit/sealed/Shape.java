package com.kousenit.sealed;

// From JEP 360, https://openjdk.java.net/jeps/360
public abstract sealed class Shape
        permits Circle, Rectangle, Square {
    public abstract double getArea();
}
