package com.kousenit.lvti;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"UnnecessaryBoxing", "ConstantConditions", "UnnecessaryLocalVariable", "MismatchedQueryAndUpdateOfCollection"})
public class VarTypeTest {
    @Test
    void inferString() {
        var s = "Hello, World!";
        assertEquals(String.class, s.getClass());
    }

    @Test
    void inferInteger() {
        var num = 3;
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
        Map<String, List<Integer>> map = Map.ofEntries(
                Map.entry("a", List.of(1, 2, 3)),
                Map.entry("b", List.of(1, 2, 3)),
                Map.entry("c", List.of(1, 2, 3)),
                Map.entry("d", List.of(1, 2, 3)));

        // LVTI useful in for loops and try-with-resource blocks
        for (var e : map.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
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
        // var x = null;
    }

    @Test
    void dontDoThis() {
        var var =  new Var("var");
    }
}

class Var {
    private final String var;

    public Var(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }
}
