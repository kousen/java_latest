package com.kousenit.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static long daysBetween(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date1, date2);
    }

    public static long weeksBetween(LocalDate date1, LocalDate date2) {
        return ChronoUnit.WEEKS.between(date1, date2);
    }

    public static Period periodBetween(LocalDate date1, LocalDate date2) {
        return Period.between(date1, date2);
    }
}
