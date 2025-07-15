package com.kousenit.streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StreamGatherersTest {

    @Test
    public void windowFixedExample() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        
        List<List<Integer>> windows = numbers.stream()
                .gather(Gatherers.windowFixed(3))
                .toList();
        
        assertEquals(3, windows.size());
        assertEquals(List.of(1, 2, 3), windows.get(0));
        assertEquals(List.of(4, 5, 6), windows.get(1));
        assertEquals(List.of(7, 8, 9), windows.get(2));
    }
    
    @Test
    public void windowSlidingExample() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        
        List<List<Integer>> windows = numbers.stream()
                .gather(Gatherers.windowSliding(3))
                .toList();
        
        assertEquals(3, windows.size());
        assertEquals(List.of(1, 2, 3), windows.get(0));
        assertEquals(List.of(2, 3, 4), windows.get(1));
        assertEquals(List.of(3, 4, 5), windows.get(2));
    }
    
    @Test
    public void scanExample() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        
        List<Integer> runningSum = numbers.stream()
                .gather(Gatherers.scan(() -> 0, Integer::sum))
                .toList();
        
        assertEquals(List.of(1, 3, 6, 10, 15), runningSum);
    }
    
    @Test
    public void foldExample() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        
        List<Integer> sum = numbers.stream()
                .gather(Gatherers.fold(() -> 0, Integer::sum))
                .toList();
        
        assertEquals(1, sum.size());
        assertEquals(15, sum.get(0));
    }
    
    @Test
    public void mapConcurrentExample() {
        List<String> inputs = List.of("apple", "banana", "cherry", "date");
        
        List<Integer> lengths = inputs.stream()
                .gather(Gatherers.mapConcurrent(2, String::length))
                .toList();
        
        assertEquals(4, lengths.size());
        assertEquals(5, lengths.get(0)); // "apple".length()
        assertEquals(6, lengths.get(1)); // "banana".length()
        assertEquals(6, lengths.get(2)); // "cherry".length()
        assertEquals(4, lengths.get(3)); // "date".length()
    }
    
    @Test
    public void customGathererExample() {
        // Custom gatherer that collects pairs of consecutive elements
        Gatherer<Integer, ArrayList<Integer>, Pair<Integer, Integer>> pairwise = Gatherer.ofSequential(
                ArrayList::new,
                Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
                    state.add(element);
                    if (state.size() == 2) {
                        boolean result = downstream.push(new Pair<>(state.get(0), state.get(1)));
                        state.clear();
                        return result;
                    }
                    return true;
                })
        );
        
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Pair<Integer, Integer>> pairs = numbers.stream()
                .gather(pairwise)
                .toList();
        
        assertEquals(3, pairs.size());
        assertEquals(new Pair<>(1, 2), pairs.get(0));
        assertEquals(new Pair<>(3, 4), pairs.get(1));
        assertEquals(new Pair<>(5, 6), pairs.get(2));
    }
    
    @Test
    public void distinctByExample() {
        record Person(String name, int age) {}
        
        List<Person> people = List.of(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 30),
                new Person("David", 25),
                new Person("Eve", 35)
        );
        
        // Custom gatherer to get distinct by age
        Gatherer<Person, HashSet<Integer>, Person> distinctByAge = Gatherer.ofSequential(
                HashSet::new,
                Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
                    if (state.add(element.age())) {
                        return downstream.push(element);
                    }
                    return true;
                })
        );
        
        List<Person> uniqueAges = people.stream()
                .gather(distinctByAge)
                .toList();
        
        assertEquals(3, uniqueAges.size());
        assertEquals("Alice", uniqueAges.get(0).name());
        assertEquals("Bob", uniqueAges.get(1).name());
        assertEquals("Eve", uniqueAges.get(2).name());
    }
    
    @Test
    public void takeWhileWithStateExample() {
        // Custom gatherer that takes elements until sum exceeds threshold
        Gatherer<Integer, int[], Integer> takeUntilSumExceeds = Gatherer.ofSequential(
                () -> new int[]{0}, // state: running sum
                Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
                    state[0] += element;
                    if (state[0] <= 10) {
                        return downstream.push(element);
                    }
                    return false; // stop processing
                })
        );
        
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> result = numbers.stream()
                .gather(takeUntilSumExceeds)
                .toList();
        
        assertEquals(List.of(1, 2, 3, 4), result); // sum = 10, next would exceed
    }
    
    // Helper class for the custom gatherer example
    record Pair<T, U>(T first, U second) {}
}