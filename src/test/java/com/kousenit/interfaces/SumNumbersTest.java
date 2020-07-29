package com.kousenit.interfaces;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumNumbersTest {
    private final SumNumbers demo = new PrivateDemo();

    @Test
    public void addEvens() {
        assertEquals(2 + 4 + 6, demo.addEvens(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void addOdds() {
        assertEquals(1 + 3 + 5, demo.addOdds(1, 2, 3, 4, 5, 6));
    }

    private List<Method> nonObjectMethods(Method[] methods) {
        return Stream.of(methods)
                .filter(method -> !method.getDeclaringClass().equals(Object.class))
                .collect(Collectors.toList());
    }

    @Test
    public void checkMethods() throws Exception {
        List<Method> methods = nonObjectMethods(demo.getClass().getDeclaredMethods());
        assertEquals(0, methods.size());

        methods = nonObjectMethods(demo.getClass().getMethods());
        assertEquals(2, methods.size());

        List<String> names = methods.stream()
                .map(Method::getName)
                .collect(Collectors.toList());
        String[] correct = new String[]{"addEvens", "addOdds"};
        assertThat(names, containsInAnyOrder(correct));

        Class<?>[] interfaces = demo.getClass().getInterfaces();
        assertEquals(1, interfaces.length);
        assertEquals(SumNumbers.class, interfaces[0]);

        Method add = interfaces[0]
                .getDeclaredMethod("add", IntPredicate.class, int[].class);
        add.setAccessible(true);
        int[] ints = IntStream.rangeClosed(1, 5).toArray();
        IntPredicate predicate = e -> true;
        Object result = add.invoke(demo, predicate, ints);
        assertEquals(1 + 2 + 3 + 4 + 5, result);
    }

}