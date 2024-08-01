package com.kousenit.records;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void testProduct() {
        var product = new Product("widget", 29.95);
        assertAll(
                () -> assertEquals("WIDGET", product.name()),
                () -> assertEquals(29.95, product.price(), 0.001)
        );
    }

    @Test
    void testProductNegativePrice() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Product("widget", -29.95));
        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    void testProductPriceRounding() {
        var product = new Product("widget", 29.999);
        assertEquals(30.0, product.price(), 0.001);
    }

}