package com.kousenit.interfaces;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public interface SumNumbers {
    IntPredicate EVENS = n -> n % 2 == 0;

    default int addEvens(int... nums) {
        // return add(n -> n % 2 == 0, nums);
        return add(EVENS, nums);
    }

    default int addOdds(int... nums) {
        return add(n -> n % 2 != 0, nums);
    }

    private int add(IntPredicate predicate, int... nums) {
        return IntStream.of(nums)
                        .filter(predicate)
                        .sum();
    }
}
