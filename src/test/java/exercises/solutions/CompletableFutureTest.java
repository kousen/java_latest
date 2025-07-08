package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

public class CompletableFutureTest {
    
    @Test
    public void createCompletableFuture() throws Exception {
        // Create a CompletableFuture that returns "Hello"
        CompletableFuture<String> future = CompletableFuture.completedFuture("Hello");
        
        assertEquals("Hello", future.get());
    }
    
    @Test
    public void asyncComputation() throws Exception {
        // Create async computation that returns 42 after delay
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // Simulate some work
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 42;
        });
        
        assertEquals(42, future.get());
    }
    
    @Test
    public void chainOperations() throws Exception {
        // Chain operations: get number, double it, convert to string
        CompletableFuture<String> result = CompletableFuture
            .supplyAsync(() -> 21)
            .thenApply(n -> n * 2)
            .thenApply(Object::toString);
        
        assertEquals("42", result.get());
    }
    
    @Test
    public void combineResults() throws Exception {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);
        
        // Combine both results by adding them
        CompletableFuture<Integer> combined = future1.thenCombine(future2, Integer::sum);
        
        assertEquals(30, combined.get());
    }
    
    @Test
    public void handleException() throws Exception {
        // Handle exception in async computation
        CompletableFuture<String> future = CompletableFuture
            .<String>supplyAsync(() -> {
                throw new RuntimeException("Oops!");
            })
            .exceptionally(ex -> "Error occurred");
        
        assertEquals("Error occurred", future.get());
    }
}