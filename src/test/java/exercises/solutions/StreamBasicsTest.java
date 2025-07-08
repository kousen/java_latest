package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class StreamBasicsTest {
    
    private final List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
    
    @Test
    public void filterAndCount() {
        // Count how many numbers are greater than 4
        long count = numbers.stream()
                .filter(n -> n > 4)
                .count();
        
        assertEquals(5, count);
    }
    
    @Test
    public void mapAndSum() {
        // Square each number and sum the results
        int sum = numbers.stream()
                .mapToInt(n -> n * n)
                .sum();
        
        assertEquals(232, sum);
    }
    
    @Test
    public void findFirstEven() {
        // Find the first even number
        Optional<Integer> firstEven = numbers.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();
        
        assertTrue(firstEven.isPresent());
        assertEquals(4, firstEven.get());
    }
    
    @Test
    public void distinctAndSorted() {
        // Get distinct numbers in sorted order
        List<Integer> result = numbers.stream()
                .distinct()
                .sorted()
                .toList();
        
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 9), result);
    }
    
    @Test
    public void maxValue() {
        // Find the maximum value
        Optional<Integer> max = numbers.stream()
                .max(Integer::compareTo);
        
        assertEquals(9, max.orElse(0));
    }
}