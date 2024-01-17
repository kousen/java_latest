package com.kousenit.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"DataFlowIssue"})
public class ImmutableMapTest {

    @Test
    void setOf() {
        Set<String> letters = Set.of("a", "b");
        assertEquals(2, letters.size());
        System.out.println(letters.getClass().getName());
    }

    @Test
    void listOf() {
        List<String> letters = List.of("a", "b", "b");
        assertEquals(3, letters.size());
        System.out.println(letters.getClass().getName());
    }

    @Test
    void arraysAslist() {
        final List<String> strings = Arrays.asList("this", "is", "a", "list");
        System.out.printf("original list: %s%n", strings);
        System.out.println(strings.getClass().getName());
        Collections.sort(strings);
        System.out.printf("After Collections.sort(): %s%n", strings);
        assertThrows(UnsupportedOperationException.class, () -> strings.add("new"));

        List<String> others = List.of("this", "is", "a", "list");
        // This WON'T work
        assertThrows(UnsupportedOperationException.class, () -> Collections.sort(others));
        List<String> sorted = others.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                // .collect(Collectors.toList())
                .toList(); // produces unmodifiable list (from Java 16)
        System.out.println(sorted);
    }

    @Test
    void addingElementThrowsUOE() {
        List<String> strings = List.of("a", "b");
        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, () -> strings.add("c")),
                () -> assertThrows(UnsupportedOperationException.class, () -> strings.remove("c")),
                () -> assertThrows(UnsupportedOperationException.class, strings::clear),
                () -> assertThrows(UnsupportedOperationException.class, () -> strings.replaceAll(String::toUpperCase))
        );
    }

    @SuppressWarnings("OverwrittenKey")
    @Test
    void setOfDuplicates() {
        assertThrows(IllegalArgumentException.class, () -> Set.of("a", "a"));
    }

    @Test
    void noNullArguments() {
        assertAll("No null keys or values",
                () -> assertThrows(NullPointerException.class, () -> List.of(null, null)),
                () -> assertThrows(NullPointerException.class, () -> Set.of(null, null)),
                () -> assertThrows(NullPointerException.class, () -> Map.of(null, "value")),
                () -> assertThrows(NullPointerException.class, () -> Map.of("key", null)));
    }

    @SuppressWarnings("OverwrittenKey")
    @Test
    void noDuplicateKeysInMap() {
        assertThrows(IllegalArgumentException.class,
                () -> ofEntries(
                        entry("k1", "v1"),
                        entry("k2", "v2"),
                        entry("k1", "v2"))
        );
    }

    @Test
    public void immutableMapFromEntries() {
        Map<String, String> jvmLanguages = ofEntries(
                entry("Java", "https://www.oracle.com/technetwork/java/index.html"),
                entry("Groovy", "https://groovy-lang.org/"),
                entry("Scala", "https://www.scala-lang.org/"),
                entry("Clojure", "https://clojure.org/"),
                entry("Kotlin", "https://kotlinlang.org/"));

        Set<String> names = Set.of("Java", "Scala", "Groovy", "Clojure", "Kotlin");

        List<String> urls = List.of(
                "https://www.oracle.com/technetwork/java/index.html",
                "https://groovy-lang.org/",
                "https://www.scala-lang.org/",
                "https://clojure.org/",
                "https://kotlinlang.org/");

        Set<String> keys = jvmLanguages.keySet();
        Collection<String> values = jvmLanguages.values();
        names.forEach(name -> assertTrue(keys.contains(name)));
        urls.forEach(url -> assertTrue(values.contains(url)));

        Map<String, String> javaMap = Map.of(
                "Java",
                "https://www.oracle.com/technetwork/java/index.html",
                "Groovy",
                "https://groovy-lang.org/",
                "Scala",
                "https://www.scala-lang.org/",
                "Clojure",
                "https://clojure.org/",
                "Kotlin",
                "https://kotlinlang.org/");
        javaMap.forEach((name, url) -> assertTrue(
                jvmLanguages.containsKey(name) && jvmLanguages.containsValue(url)));
    }

    @Test
    void hashMapFromMap() {
        Map<String, String> map = ofEntries(entry("k1", "v1"), entry("k2", "v2"));
        Map<String, String> modifiableMap = new HashMap<>(map);
        modifiableMap.put("k3", "v3");
        assertEquals(3, modifiableMap.size());
    }

    @Test
    void modifyMapViaIterator() {
        Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
        map.forEach((k, v) -> {
            // creates a local variable, which does NOT update the map
            v = v * 2;
        });
        System.out.println(map);
        assertThrows(UnsupportedOperationException.class, () -> map.put("one", 11));
    }
}
