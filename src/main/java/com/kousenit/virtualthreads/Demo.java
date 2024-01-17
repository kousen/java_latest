package com.kousenit.virtualthreads;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

// Example from: https://openjdk.org/jeps/444 (Virtual Threads)
public class Demo {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000)
                    .forEach(i ->
                            executor.submit(() -> {
                                Thread.sleep(Duration.ofSeconds(1));
                                return i;
                            }));
        }
        long endTime = System.nanoTime();
        System.out.printf("Elapsed time: %d ms%n", (endTime - startTime) / 1_000_000);
    }
}
