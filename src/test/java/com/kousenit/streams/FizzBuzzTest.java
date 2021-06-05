package com.kousenit.streams;

import net.jqwik.api.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FizzBuzzTest {
    @Property
    boolean every_third_element_starts_with_Fizz(@ForAll("divisibleBy3") int i) {
        return fizzBuzz().get(i - 1)
                .startsWith("Fizz");
    }

    @Property
    boolean every_fifth_element_ends_with_Buzz(@ForAll("divisibleBy5") int i) {
        return fizzBuzz().get(i - 1)
                .endsWith("Buzz");
    }

    @Provide
    Arbitrary<Integer> divisibleBy3() {
        return Arbitraries.integers()
                .between(1, 100)
                .filter(i -> i % 3 == 0);
    }

    @Provide
    Arbitrary<Integer> divisibleBy5() {
        return Arbitraries.integers()
                .between(1, 100)
                .filter(i -> i % 5 == 0);
    }

    private List<String> fizzBuzz() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(FizzBuzz::int2fizzbuzz)
                .collect(Collectors.toList());
    }
}