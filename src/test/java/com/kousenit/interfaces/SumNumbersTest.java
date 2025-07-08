package com.kousenit.interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumNumbersTest {
    private final SumNumbers demo = new PrivateDemo();

    @Test
    public void addEvens() {
        assertEquals(2 + 4 + 6, demo.addEvens(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void addOdds() {
        assertEquals(1 + 3 + 5, demo.addOdds(1, 2, 3, 4, 5, 6));
    }



}