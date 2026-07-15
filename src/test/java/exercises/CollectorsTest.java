package exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class CollectorsTest {

    // Like a class, but immutable
    // Were released in Java 16
    // Autogenerate getters, equals, hashCode, toString
    // Primary ("canonical") constructor appears BEFORE the body
    // "getters" are named after the fields (i.e., name(), age(), city()), not prefixed with "get"
    record Person(String name, int age, String city) {}

    private final List<Person> people = List.of(
            new Person("Alice", 25, "New York"),
            new Person("Bob", 30, "London"),
            new Person("Charlie", 25, "New York"),
            new Person("David", 35, "London"),
            new Person("Eve", 30, "Paris")
    );

    @Test
    public void groupByCity() {
        // TODO: Group people by city
        // Map<String, List<Person>> byCity = people.stream()...

        Map<String, List<Person>> byCity = people.stream()
                .collect(Collectors.groupingBy(Person::city));

         assertEquals(2, byCity.get("New York").size());
         assertEquals(2, byCity.get("London").size());
         assertEquals(1, byCity.get("Paris").size());
    }

    @Test
    public void groupByAgeWithCounting() {
        // TODO: Group by age and count people in each age group
        // Map<Integer, Long> ageCount = people.stream()...

        // assertEquals(2L, ageCount.get(25));
        // assertEquals(2L, ageCount.get(30));
        // assertEquals(1L, ageCount.get(35));
    }

    @Test
    public void partitionByAge() {
        // TODO: Partition people into those 30 or older vs younger
        // Map<Boolean, List<Person>> partitioned = people.stream()...

        // assertEquals(3, partitioned.get(true).size());  // 30 or older
        // assertEquals(2, partitioned.get(false).size()); // younger than 30
    }

    @Test
    public void averageAgeByCity() {
        // TODO: Calculate average age by city
        // Map<String, Double> avgAgeByCity = people.stream()...

        // assertEquals(25.0, avgAgeByCity.get("New York"));
        // assertEquals(32.5, avgAgeByCity.get("London"));
        // assertEquals(30.0, avgAgeByCity.get("Paris"));
    }

    @Test
    public void joinNames() {
        // TODO: Join all names with comma separator
        // String names = people.stream()...

        // assertEquals("Alice, Bob, Charlie, David, Eve", names);
    }
}