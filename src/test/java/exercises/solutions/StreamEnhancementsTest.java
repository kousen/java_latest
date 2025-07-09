package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class StreamEnhancementsTest {
    
    @Test
    public void takeWhileExample() {
        List<Integer> numbers = List.of(2, 4, 6, 7, 8, 9, 10);
        
        // Take elements while they are even
        List<Integer> result = numbers.stream()
                .takeWhile(n -> n % 2 == 0)
                .toList();
        
        assertEquals(List.of(2, 4, 6), result);
    }
    
    @Test
    public void dropWhileExample() {
        List<Integer> numbers = List.of(2, 4, 6, 7, 8, 9, 10);
        
        // Drop elements while they are even
        List<Integer> result = numbers.stream()
                .dropWhile(n -> n % 2 == 0)
                .toList();
        
        assertEquals(List.of(7, 8, 9, 10), result);
    }
    
    /** */
    @Test
    public void ofNullableExample() {
        String value = null;
        
        // Create stream from nullable value
        long count = Stream.ofNullable(value).count();
        
        assertEquals(0, count);
        
        value = "hello";
        count = Stream.ofNullable(value).count();
        assertEquals(1, count);
    }
    
    @Test
    public void iterateWithPredicate() {
        // Generate powers of 2 less than 100
        List<Integer> powers = Stream.iterate(1, n -> n < 100, n -> n * 2)
                .toList();
        
        assertEquals(List.of(1, 2, 4, 8, 16, 32, 64), powers);
    }
    
    @Test
    public void toListConvenience() {
        // Use Java 16's toList() instead of collect(Collectors.toList())
        List<String> result = Stream.of("a", "b", "c")
                .toList();
        
        assertEquals(3, result.size());
    }
}