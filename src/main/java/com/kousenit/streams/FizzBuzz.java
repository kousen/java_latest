package com.kousenit.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz {
    public static String int2fizzbuzz(int i) {
        boolean divBy3 = i % 3 == 0;
        boolean divBy5 = i % 5 == 0;
        return divBy3 && divBy5 ? "FizzBuzz"
                : divBy3 ? "Fizz"
                : divBy5 ? "Buzz"
                : String.valueOf(i);
    }

    public static void main(String[] args) {
        List<String> output = IntStream.range(1, 20)
                .mapToObj(FizzBuzz::int2fizzbuzz)
                .collect(Collectors.toList());
        System.out.println(output);
    }
}
