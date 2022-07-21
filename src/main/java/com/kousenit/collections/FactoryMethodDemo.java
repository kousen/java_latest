package com.kousenit.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FactoryMethodDemo {
    public static void main(String[] args) {
        List<String> strings = List.of("this", "is", "a", "list", "of", "string");
        System.out.println(strings);
        System.out.println(strings.getClass().getName());

        // Set.of("hi", "there", "hi");

        Map.ofEntries(
                Map.entry("Java", "https://www.java.com"),
                Map.entry("Groovy", "https://groovy-lang.org"),
                Map.entry("Scala", "https://www.scala-lang.org"),
                Map.entry("Kotlin", "https://kotlinlang.org")
        ).forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
