package com.kousenit.streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TeeingCollectorDemoTest {

    @Test
    void minMaxOfRange() {
        var result = TeeingCollectorDemo.minMax(1, 100);
        assertEquals(1, result.min());
        assertEquals(100, result.max());
    }

    @Test
    void minMaxSingleElement() {
        var result = TeeingCollectorDemo.minMax(42, 42);
        assertEquals(42, result.min());
        assertEquals(42, result.max());
    }

    @Test
    void sumAndCount() {
        var result = TeeingCollectorDemo.sumAndCount(List.of(3, 7, 2, 8, 5));
        assertEquals(25, result.sum());
        assertEquals(5, result.count());
        assertEquals(5.0, result.average(), 0.001);
    }

    @Test
    void sumAndCountSingleElement() {
        var result = TeeingCollectorDemo.sumAndCount(List.of(10));
        assertEquals(10, result.sum());
        assertEquals(1, result.count());
        assertEquals(10.0, result.average(), 0.001);
    }

    @Test
    void stringStats() {
        var words = List.of("Java", "streams", "teeing", "collector", "demo");
        var stats = TeeingCollectorDemo.stringStats(words);

        assertEquals(5, stats.count());
        assertEquals("collector", stats.longest());
        assertEquals("Java", stats.shortest());
        // (4 + 7 + 6 + 9 + 4) / 5 = 6.0
        assertThat(stats.average()).isCloseTo(6.0, org.assertj.core.data.Offset.offset(0.01));
    }

    @Test
    void shortVsLong() {
        var words = List.of("Java", "streams", "teeing", "collector", "demo");
        var result = TeeingCollectorDemo.shortVsLong(words, 5);

        assertThat(result).contains("Short (<=5): [Java, demo]");
        assertThat(result).contains("Long (>5): [streams, teeing, collector]");
    }
}
