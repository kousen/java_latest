package com.kousenit.structuredconcurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StructuredConcurrencyDemoTest {
    private final StructuredConcurrencyDemo demo = new StructuredConcurrencyDemo();

    @Test
    void bothSubtasksContributeToResult() throws InterruptedException {
        var userOrder = demo.getUserOrder();
        assertEquals(new StructuredConcurrencyDemo.UserOrder("Frodo Baggins", 42), userOrder);
    }

    @Test
    void fastestQuoteWins() throws InterruptedException {
        assertEquals("Quote from fast server", demo.getFastestQuote());
    }
}
