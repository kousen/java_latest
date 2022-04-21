package com.kousenit.records;

// public record Person(Integer id, String first, String last) {}

public record Person(Integer id, String first, String last) {

    public Person(String first, String last) {
        this(999, first, last);
    }

    public String name() {
        return first + " " + last;
    }

    public String getFirst() {
        return first;
    }

    // fields are private and final, so this doesn't work:
//    public void setFirst(String first) {
//        this.first = first;
//    }

    // inner record
    record Job(String name) {}
}
