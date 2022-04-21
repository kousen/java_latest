package com.kousenit.records;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test @DisplayName("check equal records are not necessarily the same objects")
    void checkEquivalenceAndReferenceEquality() {
        Person p1 = new Person(1, "Jean-Luc", "Picard");
        Person p2 = new Person(1, "Jean-Luc", "Picard");

        assertAll(
                () -> assertEquals(p1, p2),
                () -> assertNotSame(p1, p2)
        );
    }

    @Test @DisplayName("two-arg constructor delegates to three-arg ctor")
    void checkTwoArgCtor() {
        Person p = new Person("Will", "Riker");
        assertAll(
                () -> assertEquals("Will", p.first()),
                () -> assertEquals("Riker", p.last()),
                () -> assertEquals(999, p.id())
        );
    }

    @Test
    void canNotModifyFields() {
        Person p = new Person(999, "Wesley", "Crusher");
        // p.first("Wes");
    }

    @Test
    void canUseInstanceMethods() {
        Person p = new Person(999, "Wesley", "Crusher");
        assertEquals("Wesley Crusher", p.name());
    }
}