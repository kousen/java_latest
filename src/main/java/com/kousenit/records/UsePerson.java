package com.kousenit.records;

public class UsePerson {
    public static void main(String[] args) {
        Person p1 = new Person(1, "Jean-Luc", "Picard");
        Person p2 = new Person(1, "Jean-Luc", "Picard");
        System.out.println(p1);
        System.out.println(p1.equals(p2));
    }
}
