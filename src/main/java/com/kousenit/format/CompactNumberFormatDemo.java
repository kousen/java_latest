package com.kousenit.format;

import java.text.NumberFormat;
import java.util.Locale;

// See https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/text/CompactNumberFormat.html
// Note: Added in JDK 12
public class CompactNumberFormatDemo {
    public static void main(String[] args) {
        NumberFormat instance = NumberFormat.getCompactNumberInstance();
        long amount = 123456;
        System.out.println(instance.format(amount));

        instance = NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.LONG);
        System.out.println(instance.format(amount));

        instance = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.LONG);
        System.out.println(instance.format(amount));
        instance = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.SHORT);
        System.out.println(instance.format(amount));


        instance = NumberFormat.getCompactNumberInstance(new Locale("hi", "IN"), NumberFormat.Style.LONG);
        System.out.println(instance.format(amount));
        instance = NumberFormat.getCompactNumberInstance(new Locale("hi", "IN"), NumberFormat.Style.SHORT);
        System.out.println(instance.format(amount));
    }
}
