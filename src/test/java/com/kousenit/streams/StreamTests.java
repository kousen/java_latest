package com.kousenit.streams;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamTests {
    @Test
    public void ofNullable() {
        Stream<String> stream = Stream.ofNullable("abc");
        assertEquals(1, stream.count());

        stream = Stream.ofNullable(null);
        assertEquals(0, stream.count());
    }

    @Test
    public void iterate() {
        List<BigDecimal> bigDecimals =
                Stream.iterate(BigDecimal.ZERO, bd -> bd.add(BigDecimal.ONE))
                .limit(10)
                .toList();

        assertEquals(10, bigDecimals.size());

        bigDecimals = Stream.iterate(BigDecimal.ZERO,
                bd -> bd.longValue() < 10L,
                bd -> bd.add(BigDecimal.ONE))
                .toList();

        assertEquals(10, bigDecimals.size());
    }

    @Test
    public void takeWhile() {
        List<String> strings = Stream.of("this is a list of strings".split(" "))
                .takeWhile(s -> !s.equals("of"))
                .toList();
        List<String> correct = Arrays.asList("this", "is", "a", "list");
        assertEquals(correct, strings);
    }

    @Test
    public void dropWhile() {
        List<String> strings = Stream.of("this is a list of strings".split(" "))
                .dropWhile(s -> !s.equals("of"))
                .toList();
        List<String> correct = Arrays.asList("of", "strings");
        assertEquals(correct, strings);
    }

    @Test
    public void takeWhileRandom() {
        Random random = new Random();
        List<Integer> nums = random.ints(50, 0, 100)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .takeWhile(n -> n > 90)
                .toList();

        System.out.println(nums);
        nums.forEach(n -> assertTrue(n > 70));
    }

    @Test
    public void dropWhileRandom() {
        Random random = new Random();
        List<Integer> nums = random.ints(50, 0, 100)
                .sorted()
                .dropWhile(n -> n < 90)
                .boxed()
                .toList();

        System.out.println(nums);
        nums.forEach(n -> assertTrue(n > 70));
    }

    @Test
    public void testTakeWhileDemoCanBeCreated() {
        // TakeWhileDemo is a Runnable that demonstrates takeWhile with System.in
        TakeWhileDemo demo = new TakeWhileDemo();

        // Test that stop() method works
        demo.stop();

        // Verify the demo can be instantiated
        assertTrue(demo instanceof Runnable);
    }

    @Test
    public void testRunDemoMain() {
        // RunDemo executes TakeWhileDemo in an ExecutorService
        // It immediately calls stop(), so this should complete quickly
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() ->
            RunDemo.main(new String[]{})
        );
    }

}
