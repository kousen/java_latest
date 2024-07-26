package com.kousenit.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class TakeWhileDemo implements Runnable {
    private final AtomicBoolean running = new AtomicBoolean(true);

    @Override
    public void run() {
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(System.in));
             Stream<String> lines = reader.lines()) {
            System.out.println("Running...");
            //lines.takeWhile(s -> running.get())
            lines
                    .takeWhile(s -> running.get())
                    .forEach(s -> System.out.printf("%s %s%n", running.get(), s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        running.set(false);
    }
}
