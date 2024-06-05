package com.kousenit.records;

// toString, equals, hashCode
// immutable
// primary constructor
// final and extends java.lang.Record
// methods are id(), first(), and last()
// Simplest case:
// public record Person(Integer id, String first, String last) {}

import java.util.Objects;

// Canonical constructor
public record Person(Integer id, String first, String last) {

    // "compact" constructor (for validation or transformation)
    // NOTE: No parentheses!
    public Person {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be non-negative");
        }
    }

    // Any additional constructors must delegate to the primary constructor
    public Person(Person other) {
        this(other.id(), other.first(), other.last());
    }

    public String name() {
        return "%s %s".formatted(first, last);
    }

    public String getFirst() {
        return first;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(first, person.first) &&
               Objects.equals(last, person.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }

    // fields are private and final, so this doesn't work:
//    public void setFirst(String first) {
//        this.first = first;
//    }

    // inner record
    record Job(String name) {}
}
