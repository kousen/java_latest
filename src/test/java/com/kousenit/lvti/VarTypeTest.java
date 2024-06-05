package com.kousenit.lvti;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"UnnecessaryBoxing", "ConstantConditions", "UnnecessaryLocalVariable", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class VarTypeTest {
    // private final var x = "abc";  // var is not allowed here

    @Test
    void inferString() {
        var s = "Hello, World!";  // Not helpful
        assertEquals(String.class, s.getClass());
    }

    @Test
    void inferDouble() {
        var num = 3.0; // Is this "double" or "Double"? (it's "double")
        // System.out.println(num.getClass());  // doesn't compile because num is type double
        assertInstanceOf(Double.class, Double.valueOf(num));
    }

    @Test
    void inferListOfString() {
        var strings = List.of("this", "is", "a", "list", "of", "strings", 42);
        System.out.println(strings.getClass().getName());
        assertInstanceOf(List.class, strings);

        List<Integer> nums = new ArrayList<>();
        nums.add(3); nums.add(1); nums.add(4);
        var numsVar = nums;
        System.out.println(numsVar.getClass().getName());
        assertEquals(ArrayList.class, numsVar.getClass());
    }

    @Test
    void inferArrayListOfString() {
        var strings = new ArrayList<String>();
        assertEquals(ArrayList.class, strings.getClass());
    }

    @Test
    void reassignVar() {
        var list = List.of("a", "b", "c");
        list = new LinkedList<>();
        assertInstanceOf(LinkedList.class, list);

        var arrayList = new ArrayList<String>();
        // arrayList = new LinkedList<String>();
    }

    @Test
    void loopOverComplicatedMap() {
        Map<? extends String, List<? extends Integer>> map = Map.ofEntries(
        // var map = Map.ofEntries(    // actually get a Map<String, List<Integer>>
                Map.entry("a", List.of(1, 2, 3)),
                Map.entry("b", List.of(1, 2, 3)),
                Map.entry("c", List.of(1, 2, 3)),
                Map.entry("d", List.of(1, 2, 3)));

        // LVTI useful in for loops and try-with-resource blocks
        for (var entry : map.entrySet()) {
            System.out.printf("%s: %s%n", entry.getKey(), entry.getValue());
        }

        // Of course, Map now has a forEach(BiConsumer)
        // As of Java 11, var is okay in lambda expressions
        map.forEach((@Deprecated var s, var list) -> System.out.printf("%s: %s%n", s, list));
        map.forEach((s, list) -> System.out.printf("%s: %s%n", s, list));
    }

    @Test
    void inferredAnonymousType() {
        var v = new Runnable() {
            public void run() {
                System.out.println("Running...");
            }

            public void runTwice() {
                run();
                run();
            }
        };

        v.runTwice();
    }

    @Test
    void nullProblem() {
        var z = (String) null;
        var x = (Void) null;
        // var y = null;
        System.out.printf("%s, %s%n", x, z);
    }

    @Test @DisplayName("Can't reassign a type at runtime")
    void canNotReassignTypeAtRuntime() {
        var x = "abc";
        // x = 3;
        assertThat(x).isExactlyInstanceOf(String.class);
    }

    // Records: (GA as of Java 16)
    // - immutable data holders
    // - have a primary ("canonical") constructor defined BEFORE the {}
    // - autogenerate equals(), hashCode(), and toString() methods
    // - "getters" take the names of the properties, as in var() below
    // - records are final and extend java.lang.Record
    @Test
    void dontDoThis() {
        // Can't use "var" as a type name or a field type
        record Var(String var) { }

        // So silliest thing I can manage is:
        var var =  new Var("var");
        assertEquals("var", var.var());
    }
}