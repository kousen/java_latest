package com.kousenit.enhancedswitch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.Month;

import static java.time.Month.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DaysInMonthTest {
    @ParameterizedTest(name = "Checking days in {0}")
    @EnumSource(Month.class)
    void daysIn2021(Month month) {
        int days = DaysInMonth.getDays(month, 2021);
        switch (month) {
            case FEBRUARY:
                assertEquals(28, days);
                break;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                assertEquals(30, days);
                break;
            default:
                assertEquals(31, days);
        }
    }

    @ParameterizedTest(name = "Checking days in {0}")
    @EnumSource(Month.class)
    void daysIn2021Enhanced(Month month) {
        int days = DaysInMonth.getDays(month, 2021);
        switch (month) {
            case FEBRUARY -> assertEquals(28, days);
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> assertEquals(30, days);
            default -> assertEquals(31, days);
        }
    }

    @ParameterizedTest(name = "Checking days in {0}")
    @EnumSource(Month.class)
    void daysIn2021Exhaustive(Month month) {
        int days = DaysInMonth.getDays(month, 2021);
        switch (month) {
            case FEBRUARY -> assertEquals(28, days);
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> assertEquals(30, days);
            case JANUARY, MARCH, MAY, JULY, OCTOBER, DECEMBER -> assertEquals(31, days);
        }
    }
}