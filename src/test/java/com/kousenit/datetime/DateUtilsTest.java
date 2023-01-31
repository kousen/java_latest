package com.kousenit.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilsTest {

    @Test
    void daysBetweenSameDay() {
        long daysBetween = DateUtils.daysBetween(
                LocalDate.of(2023, 2, 2),
                LocalDate.of(2023, 2, 2));
        assertEquals(0, daysBetween);
    }

    @Test
    void daysBetweenSameMonth() {
        long daysBetween = DateUtils.daysBetween(
                LocalDate.of(2023, 2, 2),
                LocalDate.of(2023, 2, 3));
        assertEquals(1, daysBetween);
    }

    @Test
    void daysBetweenSameYear() {
        long daysBetween = DateUtils.daysBetween(
                LocalDate.of(2023, 2, 2),
                LocalDate.of(2023, 3, 2));
        assertEquals(28, daysBetween);
    }

    @Test
    void daysBetweenDifferentYears() {
        long daysBetween = DateUtils.daysBetween(
                LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2));
        assertEquals(365, daysBetween);
    }

    @Test
    void daysBetweenDifferentYearsLeapYear() {
        long daysBetween = DateUtils.daysBetween(
                LocalDate.of(2020, 2, 2),
                LocalDate.of(2021, 2, 2));
        assertEquals(366, daysBetween);
    }

    @Test
    void groundHogDayToFirstDayOfSpring() {
        LocalDate groundHogDay = LocalDate.of(2023, 2, 2);
        LocalDate firstDayOfSpring = LocalDate.of(2023, 3, 20);
        long daysBetween = DateUtils.daysBetween(groundHogDay, firstDayOfSpring);
        assertEquals(46, daysBetween);

        long weeks = DateUtils.weeksBetween(groundHogDay, firstDayOfSpring);
        assertEquals(6, weeks);

        long days = daysBetween - weeks * 7;
        System.out.println("There are " + weeks + " weeks and " + days +
                " days between Groundhog Day and the first day of spring");
    }
}