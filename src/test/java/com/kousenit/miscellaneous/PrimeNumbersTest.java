package com.kousenit.miscellaneous;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumbersTest {
    private final PrimeNumbers primeNumbers = new PrimeNumbers();

    @ParameterizedTest(name = "isPrime({0}) should be true")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19,
            23, 29, 31, 37, 41, 43, 47})
    void testIsPrime(int number) {
        assertTrue(primeNumbers.isPrime(number));
    }

    @Test // check that not all numbers are prime
    void isNotPrime() {
        assertFalse(primeNumbers.isPrime(4));
    }

}