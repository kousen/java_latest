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

    @Test @DisplayName("copy constructor delegates to three-arg ctor")
    void checkCopyCtor() {
        Person will = new Person(9, "Will", "Riker");
        Person willCopy = new Person(will);
        assertAll(
                () -> assertEquals("Will", willCopy.first()),
                () -> assertEquals("Riker", willCopy.last()),
                () -> assertEquals(9, willCopy.id())
        );
    }

    @SuppressWarnings("unused")
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