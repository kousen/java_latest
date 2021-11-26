package com.kousenit.records;

import java.util.HashSet;
import java.util.Set;

public class UsePerson {
    public static void main(String[] args) {
        Person p1 = new Person(1, "Jean-Luc", "Picard");
        Person p2 = new Person(1, "Jean-Luc", "Picard");
        System.out.println(p1);
        System.out.println(p1.equals(p2));
        System.out.println(p1 == p2);
        Set<Person> people = new HashSet<>();
        people.add(p1); people.add(p2);  // only adds unique elements

        // Can't add both to Set.of because they are equivalent
        // Set<Person> other = Set.of(p1, p2);
        // System.out.println(other);
        System.out.println(people);
        System.out.println(p1.id() + ": " + p1.first() + " " + p1.last());

        // Inner record
        record Employee(int id, double salary) {}
        Employee emp = new Employee(1, 100_000.0);
        System.out.println(emp);

        Person.Job job = new Person.Job("developer");
        System.out.println(job);
    }
}
