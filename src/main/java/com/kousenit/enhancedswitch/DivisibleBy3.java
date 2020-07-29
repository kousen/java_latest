package com.kousenit.enhancedswitch;

public class DivisibleBy3 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String result = switch (i % 3) {
                case 0 -> i + " % 3 == 0";
                case 1 -> i + " % 3 == 1";
                default -> i + " % 3 == 2";
            };
            System.out.println(result);
        }
    }
}
