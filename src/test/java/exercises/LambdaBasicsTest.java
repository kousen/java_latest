package exercises;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaBasicsTest {
    @Test
    public void sortWithLambda() {
        List<String> words = Arrays.asList("lambda", "expression", "java", "functional");

        //noinspection ComparatorCombinators
        words.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

        System.out.println(words);
        assertEquals("java", words.get(0));
        assertEquals("expression", words.get(2));
    }

    @Test
    public void sortWithMethodReference() {
        List<String> words = Arrays.asList("lambda", "expression", "java", "functional");

        words.sort(Comparator.comparingInt(String::length));

        assertEquals("java", words.getFirst());
    }

    @Test
    public void implementConsumer() {
        List<String> result = new ArrayList<>();

        // TODO: Create a Consumer<String> that adds strings to result list
        // Consumer<String> consumer = ...;

        // consumer.accept("Hello");
        // consumer.accept("World");

        assertEquals(Arrays.asList("Hello", "World"), result);
    }

    @Test
    public void implementSupplier() {
        // TODO: Create a Supplier<Double> using Math.random
        // Supplier<Double> randomSupplier = ...;

        // double value = randomSupplier.get();
        // assertTrue(value >= 0.0 && value < 1.0);
    }

    @Test
    public void implementPredicate() {
        // TODO: Create a Predicate<String> that tests if length > 5
        // Predicate<String> isLong = ...;

        // assertTrue(isLong.test("functional"));
        // assertFalse(isLong.test("java"));
    }
}
