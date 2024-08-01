package com.kousenit.records;

// Example of a compact constructor for validation and transformation
public record Product(String name, double price) {
    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        name = name.toUpperCase();
        price = Math.round(price * 100.0) / 100.0;
    }
}