package com.kousenit.records;

import java.util.Set;

public class UsePerson {
    public static void main(String[] args) {
        Person p1 = new Person(1, "Jean-Luc", "Picard");
        Person p2 = new Person(1, "Jean-Luc", "Picard");
        System.out.println(p1);
        System.out.println(p1.equals(p2));
//        Set<Person> people = Set.of(p1, p2);
//        System.out.println(people);
        System.out.println(p1.first() + " " + p1.last());
    }
}
