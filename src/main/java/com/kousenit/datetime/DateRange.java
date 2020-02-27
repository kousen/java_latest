package com.kousenit.datetime;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class DateRange {
    public List<LocalDate> getDays_java8(LocalDate start, LocalDate end) {
        Period period = start.until(end);
        // Trap! For dates with the same day, period.getDays() returns 0 (whoa)
        return LongStream.range(0, ChronoUnit.DAYS.between(start, end))
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }

    public List<LocalDate> getDaysByIterate(LocalDate start, int days) {
        return Stream.iterate(start, date -> date.plusDays(1))
                .limit(days)
                .collect(Collectors.toList());
    }

    public List<LocalDate> getDays_java9(LocalDate start, LocalDate end) {
        return start.datesUntil(end)
                .collect(Collectors.toList());
    }

    public List<LocalDate> getMonths_java8(LocalDate start, LocalDate end) {
        Period period = start.until(end);

        // Trap! For dates one year apart, period.getMonths() returns 0 (whoa)
        return LongStream.range(0, ChronoUnit.MONTHS.between(start, end))
                .mapToObj(start::plusMonths)
                .collect(Collectors.toList());
    }

    public List<LocalDate> getMonths_java9(LocalDate start, LocalDate end) {
        return start.datesUntil(end, Period.ofMonths(1))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        DateRange dateRange = new DateRange();
        LocalDate start = LocalDate.of(2017, Month.JUNE, 10);
        LocalDate end = LocalDate.of(2017, Month.JULY, 10);

        System.out.println(dateRange.getDays_java8(start, end));
        System.out.println(dateRange.getDays_java9(start, end));

        end = start.plusYears(1);
        System.out.println(dateRange.getMonths_java8(start, end));
        System.out.println(dateRange.getMonths_java9(start, end));
    }
}
