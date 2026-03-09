package com.kousenit.streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TeeingCollectorDemo {

    // Simple record to hold min/max results
    record MinMax(int min, int max) {}

    // Record for sum-and-count style averaging
    record SumAndCount(long sum, long count) {
        double average() {
            return count == 0 ? 0 : (double) sum / count;
        }
    }

    // Record for comprehensive stats computed in a single pass
    record Stats(long count, double average, String longest, String shortest) {}

    /**
     * Compute min and max of a range in a single pass.
     */
    public static MinMax minMax(int from, int to) {
        return IntStream.rangeClosed(from, to).boxed()
                .collect(Collectors.teeing(
                        Collectors.<Integer>minBy(Comparator.naturalOrder()),
                        Collectors.<Integer>maxBy(Comparator.naturalOrder()),
                        (min, max) -> new MinMax(
                                min.orElseThrow(), max.orElseThrow())
                ));
    }

    /**
     * Compute sum and count to derive an average manually,
     * demonstrating teeing as an alternative to summarizing collectors.
     */
    public static SumAndCount sumAndCount(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.summingLong(Integer::longValue),
                        Collectors.counting(),
                        SumAndCount::new
                ));
    }

    /**
     * Compute multiple string statistics in one pass using nested teeing.
     * Outer teeing: counting + inner teeing
     * Inner teeing: averagingDouble + (nested teeing for longest/shortest)
     */
    public static Stats stringStats(List<String> words) {
        return words.stream()
                .collect(Collectors.teeing(
                        // Left: count + average via teeing
                        Collectors.teeing(
                                Collectors.counting(),
                                Collectors.averagingDouble(String::length),
                                (count, avg) -> new Stats(count, avg, "", "")
                        ),
                        // Right: longest + shortest via teeing
                        Collectors.teeing(
                                Collectors.maxBy(Comparator.comparingInt(String::length)),
                                Collectors.minBy(Comparator.comparingInt(String::length)),
                                (longest, shortest) -> new Stats(0, 0,
                                        longest.orElse(""), shortest.orElse(""))
                        ),
                        // Merge both halves into a single Stats record
                        (left, right) -> new Stats(
                                left.count(), left.average(),
                                right.longest(), right.shortest())
                ));
    }

    /**
     * Partition-style example: split strings into two groups
     * (short vs long) using teeing with filtering collectors.
     */
    public static String shortVsLong(List<String> words, int threshold) {
        return words.stream()
                .collect(Collectors.teeing(
                        Collectors.filtering(
                                w -> w.length() <= threshold, Collectors.toList()),
                        Collectors.filtering(
                                w -> w.length() > threshold, Collectors.toList()),
                        (shortWords, longWords) ->
                                "Short (<=%d): %s, Long (>%d): %s".formatted(
                                        threshold, shortWords, threshold, longWords)
                ));
    }

    public static void main(String[] args) {
        System.out.println("=== Teeing Collector Examples ===\n");

        // 1. Min/Max
        var minMax = minMax(1, 100);
        System.out.printf("Min/Max of 1..100: %s%n%n", minMax);

        // 2. Sum and Count
        var sc = sumAndCount(List.of(3, 7, 2, 8, 5));
        System.out.printf("Sum: %d, Count: %d, Average: %.1f%n%n",
                sc.sum(), sc.count(), sc.average());

        // 3. Nested teeing for string stats
        var words = List.of("Java", "streams", "teeing", "collector", "demo");
        var stats = stringStats(words);
        System.out.printf("Words: %s%n", words);
        System.out.printf("Count: %d, Avg length: %.1f, Longest: %s, Shortest: %s%n%n",
                stats.count(), stats.average(), stats.longest(), stats.shortest());

        // 4. Short vs Long partitioning
        System.out.println(shortVsLong(words, 5));
    }
}
