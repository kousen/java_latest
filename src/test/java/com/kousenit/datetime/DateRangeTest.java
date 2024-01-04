package com.kousenit.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateRangeTest {
    private final DateRange range = new DateRange();

    @Test
    public void dateRange_oneWeekInDays() {
        LocalDate now = LocalDate.now();
        LocalDate then = now.plusWeeks(1);

        List<LocalDate> dates = range.getDays_java9(now, then);

        assertEquals(7, dates.size());
        assertEquals(dates, range.getDays_java8(now, then));
    }

    @Test
    public void dateRange_oneYearInMonths() {
        LocalDate now = LocalDate.now();
        LocalDate then = now.plusYears(1);

        List<LocalDate> dates = range.getMonths_java9(now, then);

        assertEquals(12, dates.size());
        assertEquals(dates, range.getMonths_java8(now, then));
    }

}