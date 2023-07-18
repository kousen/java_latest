package com.kousenit.miscellaneous;

import org.junit.jupiter.api.Test;

class PrimeNumbersTest {
    private final PrimeNumbers primeNumbers = new PrimeNumbers();

    @Test
    void testIsPrime() {
        primeNumbers.isPrime(2);
        primeNumbers.isPrime(3);
        primeNumbers.isPrime(5);
        primeNumbers.isPrime(7);
        primeNumbers.isPrime(11);
        primeNumbers.isPrime(13);
        primeNumbers.isPrime(17);
        primeNumbers.isPrime(19);
        primeNumbers.isPrime(23);
        primeNumbers.isPrime(29);
        primeNumbers.isPrime(31);
        primeNumbers.isPrime(37);
        primeNumbers.isPrime(41);
        primeNumbers.isPrime(43);
        primeNumbers.isPrime(47);
    }

    @Test // check that not all numbers are prime
    void isNotPrime() {

    }

}