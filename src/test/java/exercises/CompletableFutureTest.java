package exercises;

import org.junit.jupiter.api.Test;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

public class CompletableFutureTest {
    
    @Test
    public void createCompletableFuture() {
        // TODO: Create a CompletableFuture that returns "Hello"
        // CompletableFuture<String> future = CompletableFuture...;
        
        // assertEquals("Hello", future.get());
    }
    
    @Test
    public void asyncComputation() {
        // TODO: Create async computation that returns 42 after delay
        // CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
        //     // Simulate some work
        //     return 42;
        // });
        
        // assertEquals(42, future.get());
    }
    
    @Test
    public void chainOperations() {
        // TODO: Chain operations: get number, double it, convert to string
        // CompletableFuture<String> result = CompletableFuture
        //     .supplyAsync(() -> 21)
        //     .thenApply(...)
        //     .thenApply(...);
        
        // assertEquals("42", result.get());
    }
    
    @Test
    public void combineResults() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);
        
        // TODO: Combine both results by adding them
        // CompletableFuture<Integer> combined = future1.thenCombine(...);
        
        // assertEquals(30, combined.get());
    }
    
    @Test
    public void handleException() {
        // TODO: Handle exception in async computation
        // CompletableFuture<String> future = CompletableFuture
        //     .supplyAsync(() -> {
        //         throw new RuntimeException("Oops!");
        //     })
        //     .exceptionally(...);
        
        // assertEquals("Error occurred", future.get());
    }
}