package com.kousenit.interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrivateVirtualThreadsDemoTest {
    private final PrivateDemo demo = new PrivateDemo();

    @Test
    public void addEvens() {
        int sum = demo.addEvens(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(30, sum);
    }

    @Test
    public void addOdds() {
        int sum = demo.addOdds(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(25, sum);
    }
}