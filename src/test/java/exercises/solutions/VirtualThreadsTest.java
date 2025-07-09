package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class VirtualThreadsTest {
    
    @Test
    public void createVirtualThread() throws InterruptedException {
        // Create and start a virtual thread
        Thread vThread = Thread.ofVirtual()
            .name("my-virtual-thread")
            .start(() -> System.out.println("Running in: " + Thread.currentThread()));
        
        vThread.join();
        assertTrue(vThread.isVirtual());
    }
    
    @Test
    public void virtualThreadExecutor() throws Exception {
        // Use virtual thread executor
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> future = executor.submit(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "Done in " + Thread.currentThread();
            });
            
            String result = future.get();
            assertTrue(result.startsWith("Done"));
        }
    }
    
    @Test
    public void massiveConcurrency() throws Exception {
        int taskCount = 10_000;
        CountDownLatch latch = new CountDownLatch(taskCount);
        
        // Create many virtual threads
        long start = System.currentTimeMillis();
        
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(100); // Simulate I/O
                        latch.countDown();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
        
        assertTrue(latch.await(5, TimeUnit.SECONDS));
        long elapsed = System.currentTimeMillis() - start;
        // Virtual threads should handle this efficiently
        assertTrue(elapsed < 2000);
    }
    
    @Test
    public void platformVsVirtual() throws Exception {
        // Compare platform threads vs virtual threads
        
        // Platform threads (limited scalability)
        try (var platformExecutor = Executors.newFixedThreadPool(100)) {
            CountDownLatch platformLatch = new CountDownLatch(100);
            
            for (int i = 0; i < 100; i++) {
                platformExecutor.submit(() -> {
                    try {
                        Thread.sleep(10);
                        platformLatch.countDown();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            assertTrue(platformLatch.await(1, TimeUnit.SECONDS));
        }
        
        // Virtual threads (massive scalability)  
        try (var virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            CountDownLatch virtualLatch = new CountDownLatch(1000);
            
            for (int i = 0; i < 1000; i++) {
                virtualExecutor.submit(() -> {
                    try {
                        Thread.sleep(10);
                        virtualLatch.countDown();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            assertTrue(virtualLatch.await(1, TimeUnit.SECONDS));
        }
        
        // Virtual threads handle 10x more tasks just as easily
    }
}