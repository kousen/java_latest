package com.kousenit.collections;

import java.util.Map;

import static java.util.Map.entry;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = Map.ofEntries(entry("a", 1),
                entry("b", 2),
                entry("c", 3));
        map.forEach((k,v) -> System.out.println(k + " : " + v));
        System.out.println(map.getClass().getName());
    }
}
