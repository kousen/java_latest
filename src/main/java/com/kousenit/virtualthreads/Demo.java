package com.kousenit.virtualthreads;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

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
        System.out.println("Elapsed time: " + (endTime - startTime) / 1_000_000 + " ms");
    }
}
