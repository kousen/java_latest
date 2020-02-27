package com.kousenit.enhancedswitch;

import com.kousenit.enhancedswitch.DaysInMonth;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DaysInMonthTest {
    @ParameterizedTest(name = "Checking days in {0}")
    @EnumSource(Month.class)
    void daysIn2020(Month month) {
        int days = DaysInMonth.getDays(month, 2020);
        switch (month) {
            case FEBRUARY:
                assertEquals(29, days);
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
    void daysIn2020Enhanced(Month month) {
        int days = DaysInMonth.getDays(month, 2020);
        switch (month) {
            case FEBRUARY:
                assertEquals(29, days);
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
}