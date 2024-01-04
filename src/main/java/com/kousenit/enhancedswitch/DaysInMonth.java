package com.kousenit.enhancedswitch;

import java.time.Month;
import java.time.Year;

public class DaysInMonth {
    public static int getDays(Month month, int year) {
        return switch (month) {
            case SEPTEMBER, APRIL, JUNE, NOVEMBER -> 30;
            case FEBRUARY -> {
                System.out.printf("Checking if %d is a leap year%n", year);
                yield Year.isLeap(year) ? 29 : 28;
            }
//            case FEBRUARY -> Year.isLeap(year) ? 29 : 28;
            default -> 31;
        };
    }
}
