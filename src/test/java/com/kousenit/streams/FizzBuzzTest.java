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

    @Provide
    Arbitrary<Integer> divisibleBy3() {
        return Arbitraries.integers().between(1, 100)
                .filter(i -> i % 3 == 0);
    }

    private List<String> fizzBuzz() {
        return IntStream.range(1, 100)
                .mapToObj(FizzBuzz::int2fizzbuzz)
                .collect(Collectors.toList());
    }
}