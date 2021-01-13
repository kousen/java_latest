package com.kousenit.optional;

import java.util.Optional;

public class OrElseGetDemo {
    private static String getMessage() {
        System.out.println("Inside getMessage");
        return "message";
    }

    public static void main(String[] args) {

        Optional<String> optional = Optional.of("my string");
        System.out.println(optional.orElse(getMessage()));
        System.out.println(optional.orElseGet(() -> getMessage()));  // lazy creation of default
        System.out.println(optional.orElseGet(OrElseGetDemo::getMessage));
    }
}
