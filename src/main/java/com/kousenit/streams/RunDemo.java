package com.kousenit.streams;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunDemo {
    public static void main(String[] args) {
        // ExecutorService doesn't implement AutoCloseable until Java 19
//        try (ExecutorService service = Executors.newCachedThreadPool()) {
        ExecutorService service = Executors.newCachedThreadPool();
        TakeWhileDemo demo = new TakeWhileDemo();
        service.execute(demo);
        service.execute(demo::stop);

        service.shutdown();
//        }
    }
}
