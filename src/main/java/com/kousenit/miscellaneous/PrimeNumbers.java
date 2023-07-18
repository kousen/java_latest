package com.kousenit.miscellaneous;

public class PrimeNumbers {

    public boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i < x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
