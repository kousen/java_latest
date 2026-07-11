package com.kousenit.structuredconcurrency;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

/**
 * Structured Concurrency (preview): treat related concurrent tasks
 * as a single unit of work with automatic cancellation and cleanup.
 * <p>
 * Uses the redesigned API from JEP 505 (Java 25, 5th preview),
 * unchanged in JEP 525 (Java 26, 6th preview): the static factory
 * {@code StructuredTaskScope.open()} replaced the old public
 * constructors like {@code new StructuredTaskScope.ShutdownOnFailure()}.
 * Requires {@code --enable-preview}.
 */
public class StructuredConcurrencyDemo {

    public record UserOrder(String user, int orderNumber) {
    }

    // Default policy: wait for all subtasks; if one fails,
    // cancel the rest and throw
    public UserOrder getUserOrder() throws InterruptedException {
        try (var scope = StructuredTaskScope.open()) {
            Subtask<String> user = scope.fork(this::findUser);
            Subtask<Integer> order = scope.fork(this::fetchOrderNumber);

            scope.join();  // wait for both, propagating any failure

            return new UserOrder(user.get(), order.get());
        }  // scope close cancels any subtasks still running
    }

    // Alternate policy: first successful result wins
    public String getFastestQuote() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(
                StructuredTaskScope.Joiner.<String>anySuccessfulResultOrThrow())) {
            scope.fork(() -> fetchQuote("fast server", 50));
            scope.fork(() -> fetchQuote("slow server", 200));

            return scope.join();  // result of the first subtask to succeed
        }
    }

    private String findUser() throws InterruptedException {
        Thread.sleep(100);
        return "Frodo Baggins";
    }

    private Integer fetchOrderNumber() throws InterruptedException {
        Thread.sleep(150);
        return 42;
    }

    private String fetchQuote(String source, long delayMillis) throws InterruptedException {
        Thread.sleep(delayMillis);
        return "Quote from " + source;
    }

    static void main() throws InterruptedException {
        var demo = new StructuredConcurrencyDemo();
        System.out.println(demo.getUserOrder());
        System.out.println(demo.getFastestQuote());
    }
}
