package com.kousenit.collections;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ImmutableMapTest {

    @Test
    void setOf() {
        Set<String> letters = Set.of("a", "b");
        assertEquals(2, letters.size());
    }

    @Test
    void listOf() {
        List<String> letters = List.of("a", "b");
        assertEquals(2, letters.size());
    }

    @Test
    void addingElementThrowsUOE() {
        List<String> strings = List.of("a", "b");
        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, () -> strings.add("c")),
                () -> assertThrows(UnsupportedOperationException.class, () -> strings.remove("c"))
        );
    }

    @Test
    void setOfDuplicates() {
        assertThrows(IllegalArgumentException.class, () -> Set.of("a", "a"));
    }

    @Test
    void noNullArguments() {
        assertAll("No null keys or values",
//                () -> assertThrows(NullPointerException.class, () -> List.of(null)),
//                () -> assertThrows(NullPointerException.class, () -> Set.of(null)),
                () -> assertThrows(NullPointerException.class, () -> Map.of(null, "value")),
                () -> assertThrows(NullPointerException.class, () -> Map.of("key", null)));
    }

    @Test
    void noDuplicateKeysInMap() {
        assertThrows(IllegalArgumentException.class,
                () -> ofEntries(entry("k1", "v1"),
                        entry("k2", "v1"),
                        entry("k1", "v2")));
    }

    @Test
    public void immutableMapFromEntries() {
        Map<String, String> jvmLanguages = ofEntries(
                entry("Java", "http://www.oracle.com/technetwork/java/index.html"),
                entry("Groovy", "http://groovy-lang.org/"),
                entry("Scala", "http://www.scala-lang.org/"),
                entry("Clojure", "https://clojure.org/"),
                entry("Kotlin", "http://kotlinlang.org/"));

        Set<String> names = Set.of("Java", "Scala", "Groovy", "Clojure", "Kotlin");

        List<String> urls = List.of(
                "http://www.oracle.com/technetwork/java/index.html",
                "http://groovy-lang.org/",
                "http://www.scala-lang.org/",
                "https://clojure.org/",
                "http://kotlinlang.org/");

        Set<String> keys = jvmLanguages.keySet();
        Collection<String> values = jvmLanguages.values();
        names.forEach(name -> assertTrue(keys.contains(name)));
        urls.forEach(url -> assertTrue(values.contains(url)));

        Map<String, String> javaMap = Map.of(
                "Java",
                "http://www.oracle.com/technetwork/java/index.html",
                "Groovy",
                "http://groovy-lang.org/",
                "Scala",
                "http://www.scala-lang.org/",
                "Clojure",
                "https://clojure.org/",
                "Kotlin",
                "http://kotlinlang.org/");
        javaMap.forEach((name, url) -> assertTrue(
                jvmLanguages.containsKey(name) && jvmLanguages.containsValue(url)));
    }
}
