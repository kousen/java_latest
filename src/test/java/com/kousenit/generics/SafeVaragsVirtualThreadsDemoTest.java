package com.kousenit.generics;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SafeVaragsVirtualThreadsDemoTest {
    private final SafeVaragsDemo demo = new SafeVaragsDemo();
    private final LocalDate now = LocalDate.now();

    @Test
    void replaceFirstStringWithDate() {
        String[] strings = "s1 s2 s3".split("\\s");
        demo.replaceFirstStringWithDate(now, strings);

        assertThat(strings, arrayContaining(now.toString(), "s2", "s3"));
    }

    @Test
    void replaceFirstValueInArray() {
        String[] strings = "s1 s2 s3".split("\\s");

        assertThrows(ArrayStoreException.class,
                     () -> demo.replaceFirstValueInArray(now, strings));
    }

    @Test
    void replaceFirstValueInArrayUsingVarargs() {
        assertThrows(ClassCastException.class,
                     () -> {
                         Comparable<? extends Comparable<?>>[] comparables =
                                 demo.replaceFirstValueInArray(now, "s1", "s2", "s3");
                         System.out.println(Arrays.toString(comparables));
                     });
    }

    @Test
    void tickingTimeBomb() {
        // No exception thrown until you access any element
        demo.replaceFirstValueInArray(now, "s1", "s2", "s2");
    }
}