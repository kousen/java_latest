package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.*;
import static org.junit.jupiter.api.Assertions.*;

public class LambdaBasicsTest {
    
    @Test
    public void sortWithLambda() {
        List<String> words = Arrays.asList("lambda", "expression", "java", "functional");
        
        // Sort words by length using a lambda expression
        words.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        
        assertEquals("java", words.get(0));
        assertEquals("functional", words.get(3));
    }
    
    @Test
    public void sortWithMethodReference() {
        List<String> words = Arrays.asList("lambda", "expression", "java", "functional");
        
        // Sort words by length using Comparator.comparingInt and method reference
        words.sort(Comparator.comparingInt(String::length));
        
        assertEquals("java", words.getFirst());
    }
    
    @Test
    public void implementConsumer() {
        List<String> result = new ArrayList<>();
        
        // Create a Consumer<String> that adds strings to result list
        Consumer<String> consumer = result::add;
        
        consumer.accept("Hello");
        consumer.accept("World");
        
        assertEquals(Arrays.asList("Hello", "World"), result);
    }
    
    @Test
    public void implementSupplier() {
        // Create a Supplier<Double> using Math.random
        Supplier<Double> randomSupplier = Math::random;
        
        double value = randomSupplier.get();
        assertTrue(value >= 0.0 && value < 1.0);
    }
    
    @Test
    public void implementPredicate() {
        // Create a Predicate<String> that tests if length > 5
        Predicate<String> isLong = s -> s.length() > 5;
        
        assertTrue(isLong.test("functional"));
        assertFalse(isLong.test("java"));
    }
}