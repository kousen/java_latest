package com.kousenit.datetime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void daysBetweenSameDay() {
        long daysBetween = DateUtils.daysBetween(
                LocalDate.of(2023, Month.FEBRUARY, 2),
                LocalDate.of(2023, Month.FEBRUARY, 2));
        assertEquals(0, daysBetween);
    }

    @Test
    void daysBetweenSameMonth() {
        long daysBetween = DateUtils.daysBetween(
                LocalDate.of(2023, Month.FEBRUARY, 2),
                LocalDate.of(2023, Month.FEBRUARY, 3));
        assertEquals(1, daysBetween);
    }

    @Test
    void daysBetweenSameYear() {
        long daysBetween = DateUtils.daysBetween(
                LocalDate.of(2023, Month.FEBRUARY, 2),
                LocalDate.of(2023, Month.MARCH, 2));
        assertEquals(28, daysBetween);
    }

    @Test
    void daysBetweenDifferentYears() {
        long daysBetween = DateUtils.daysBetween(
                LocalDate.of(2022, Month.FEBRUARY, 2),
                LocalDate.of(2023, Month.FEBRUARY, 2));
        assertEquals(365, daysBetween);
    }

    @Test
    void daysBetweenDifferentYearsLeapYear() {
        long daysBetween = DateUtils.daysBetween(
                LocalDate.of(2020, Month.FEBRUARY, 2),
                LocalDate.of(2021, Month.FEBRUARY, 2));
        assertEquals(366, daysBetween);
    }

    @Test
    void periodBetween() {
        Period period = DateUtils.periodBetween(
                LocalDate.of(2023, Month.FEBRUARY, 2),
                LocalDate.of(2023, Month.MARCH, 20));
        assertAll(
                () -> assertEquals(0, period.getYears()),
                () -> assertEquals(1, period.getMonths()),
                () -> assertEquals(18, period.getDays()),  // Careful! 18 days, not 46
                () -> assertThrows(UnsupportedTemporalTypeException.class,
                        () -> period.get(ChronoUnit.WEEKS))  // No getWeeks() method
        );
    }

    @Test @DisplayName("Groundhog Day to first day of spring")
    void groundHogDayToFirstDayOfSpring() {
        LocalDate groundHogDay = LocalDate.of(2023, Month.FEBRUARY, 2);
        LocalDate firstDayOfSpring = LocalDate.of(2023, Month.MARCH, 20);
        long daysBetween = DateUtils.daysBetween(groundHogDay, firstDayOfSpring);
        assertEquals(46, daysBetween);

        long weeks = DateUtils.weeksBetween(groundHogDay, firstDayOfSpring);
        assertEquals(6, weeks);

        long days = daysBetween - weeks * 7;
        System.out.println("There are " + weeks + " weeks and " + days +
                " days between Groundhog Day and the first day of Spring");
    }

}