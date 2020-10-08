package com.kousenit.records;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test @DisplayName("check equal records are not necessarily the same")
    void checkEquivalenceAndReferenceEquality() {
        Person p1 = new Person(1, "Jean-Luc", "Picard");
        Person p2 = new Person(1, "Jean-Luc", "Picard");

        assertAll(
                () -> assertEquals(p1, p2),
                () -> assertNotSame(p1, p2)
        );
    }
}