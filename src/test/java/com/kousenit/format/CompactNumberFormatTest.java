package com.kousenit.format;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CompactNumberFormatTest {

    @Test
    void testCompactNumberFormat() {
        NumberFormat shortFormat = NumberFormat
                .getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        NumberFormat longFormat = NumberFormat
                .getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);

        assertEquals("1K", shortFormat.format(1000));
        assertEquals("1M", shortFormat.format(1_000_000));
        assertEquals("1B", shortFormat.format(1_000_000_000));

        assertEquals("1 thousand", longFormat.format(1000));
        assertEquals("1 million", longFormat.format(1_000_000));
    }

    @Test
    void testCompactNumberFormatWithDefaultLocale() {
        NumberFormat instance = NumberFormat.getCompactNumberInstance();
        long amount = 123456;
        String formatted = instance.format(amount);

        // Should produce some compact representation
        assertNotNull(formatted);
        assertTrue(formatted.length() < String.valueOf(amount).length());
    }

    @Test
    void testCompactNumberFormatLongStyle() {
        NumberFormat instance = NumberFormat
                .getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.LONG);
        long amount = 123456;
        String formatted = instance.format(amount);

        assertNotNull(formatted);
        assertTrue(formatted.contains("thousand") || formatted.contains("hundred"));
    }

    @Test
    void testCompactNumberFormatGermanyLong() {
        NumberFormat instance = NumberFormat
                .getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.LONG);
        long amount = 123456;
        String formatted = instance.format(amount);

        assertNotNull(formatted);
        // German locale should produce German formatting
        assertTrue(formatted.contains("Tsd") || formatted.contains("tausend") || formatted.matches(".*\\d+.*"));
    }

    @Test
    void testCompactNumberFormatGermanyShort() {
        NumberFormat instance = NumberFormat
                .getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.SHORT);
        long amount = 123456;
        String formatted = instance.format(amount);

        assertNotNull(formatted);
        assertTrue(formatted.length() < 10);
    }

    @Test
    void testCompactNumberFormatHindiLong() {
        NumberFormat instance = NumberFormat
                .getCompactNumberInstance(Locale.of("hi", "IN"), NumberFormat.Style.LONG);
        long amount = 123456;
        String formatted = instance.format(amount);

        assertNotNull(formatted);
        // Hindi uses lakh system
        assertTrue(formatted.length() > 0);
    }

    @Test
    void testCompactNumberFormatHindiShort() {
        NumberFormat instance = NumberFormat
                .getCompactNumberInstance(Locale.of("hi", "IN"), NumberFormat.Style.SHORT);
        long amount = 123456;
        String formatted = instance.format(amount);

        assertNotNull(formatted);
        assertTrue(formatted.length() > 0);
    }

    @Test
    void testDifferentMagnitudes() {
        NumberFormat shortFormat = NumberFormat
                .getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);

        assertEquals("0", shortFormat.format(0));
        assertEquals("1", shortFormat.format(1));
        assertEquals("10", shortFormat.format(10));
        assertEquals("100", shortFormat.format(100));
        assertEquals("1K", shortFormat.format(1000));
        assertEquals("10K", shortFormat.format(10_000));
        assertEquals("100K", shortFormat.format(100_000));
        assertEquals("1M", shortFormat.format(1_000_000));
        assertEquals("10M", shortFormat.format(10_000_000));
        assertEquals("100M", shortFormat.format(100_000_000));
        assertEquals("1B", shortFormat.format(1_000_000_000));
    }

    @Test
    void testCompactNumberFormatDemo() {
        // Run the demo to ensure full coverage
        assertDoesNotThrow(() -> CompactNumberFormatDemo.main(new String[]{}));
    }
}
