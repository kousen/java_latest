package com.kousenit.lvti;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"UnnecessaryBoxing", "ConstantConditions", "UnnecessaryLocalVariable", "MismatchedQueryAndUpdateOfCollection"})
public class VarTypeTest {
    // private var x = "abc";  // var is not allowed here

    @Test
    void inferString() {
        var s = "Hello, World!";  // Not helpful
        assertEquals(String.class, s.getClass());
    }

    @Test
    void inferInteger() {
        var num = 3;
        // System.out.println(num.getClass());  // doesn't compile because num is type int
        assertTrue(Integer.valueOf(num) instanceof Integer);
    }

    @Test
    void inferListOfString() {
        var strings = List.of("this", "is", "a", "list", "of", "strings");
        System.out.println(strings.getClass().getName());
        assertTrue(strings instanceof List);

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
    void loopOverComplicatedMap() {
        var map = Map.ofEntries(
                Map.entry("a", List.of(1, 2, 3)),
                Map.entry("b", List.of(1, 2, 3)),
                Map.entry("c", List.of(1, 2, 3)),
                Map.entry("d", List.of(1, 2, 3)));

        // LVTI useful in for loops and try-with-resource blocks
        for (var entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Of course, Map now has a forEach(BiConsumer)
        // As of Java 11, var is okay in lambda expressions
        map.forEach((@NotNull var s, var list) -> System.out.println(s + ": " + list));
        map.forEach((s, list) -> System.out.println(s + ": " + list));
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
        // var y = null;
        var x = (Void) null;
        var z = (String) null;
        System.out.println(x + ", " + z);
    }

    @Test @DisplayName("Can't reassign a type at runtime")
    void canNotReassignTypeAtRuntime() {
        var x = "abc";
        // x = 3;
        assertThat(x).isExactlyInstanceOf(String.class);
    }

    @Test
    void dontDoThis() {
        // Can't use "var" as a type name or a field type
        record Var(String var) { }

        // So silliest thing I can manage is:
        var var =  new Var("var");
        assertEquals("var", var.var());
    }
}