package com.kousenit.records;

// public record Person(Integer id, String first, String last) {}

public record Person(Integer id, String first, String last) {

    public Person(String first, String last) {
        this(999, first, last);
    }

    public String getName() {
        return first + " " + last;
    }

    public String getFirst() {
        return first;
    }
}
