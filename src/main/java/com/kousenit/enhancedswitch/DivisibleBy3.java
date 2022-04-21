package com.kousenit.enhancedswitch;

public class DivisibleBy3 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String result = switch (i % 3) {
                case 0 -> i + " % 3 == 0";
                case 1 -> i + " % 3 == 1";
                case 2 -> i + " % 3 == 2";
                default -> throw new IllegalStateException("Unexpected value: " + i % 3);
            };
            System.out.println(result);
        }

        // If you don't return a value, switch does NOT need to be exhaustive
        for (int i = 0; i < 10; i++) {
            switch (i % 3) {
                case 0 -> System.out.println(i + " % 3 == 0");
                case 1 -> System.out.println(i + " % 3 == 1");
                case 2 -> System.out.println(i + " % 3 == 2");
            };
        }
    }
}
