package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class CollectorsTest {
    
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
        // Group people by city
        Map<String, List<Person>> byCity = people.stream()
                .collect(Collectors.groupingBy(Person::city));
        
        assertEquals(2, byCity.get("New York").size());
        assertEquals(2, byCity.get("London").size());
        assertEquals(1, byCity.get("Paris").size());
    }
    
    @Test
    public void groupByAgeWithCounting() {
        // Group by age and count people in each age group
        Map<Integer, Long> ageCount = people.stream()
                .collect(Collectors.groupingBy(Person::age, Collectors.counting()));
        
        assertEquals(2L, ageCount.get(25));
        assertEquals(2L, ageCount.get(30));
        assertEquals(1L, ageCount.get(35));
    }
    
    @Test
    public void partitionByAge() {
        // Partition people into those 30 or older vs younger
        Map<Boolean, List<Person>> partitioned = people.stream()
                .collect(Collectors.partitioningBy(p -> p.age() >= 30));
        
        assertEquals(3, partitioned.get(true).size());  // 30 or older
        assertEquals(2, partitioned.get(false).size()); // younger than 30
    }
    
    @Test
    public void averageAgeByCity() {
        // Calculate average age by city
        Map<String, Double> avgAgeByCity = people.stream()
                .collect(Collectors.groupingBy(
                        Person::city,
                        Collectors.averagingDouble(Person::age)
                ));
        
        assertEquals(25.0, avgAgeByCity.get("New York"));
        assertEquals(32.5, avgAgeByCity.get("London"));
        assertEquals(30.0, avgAgeByCity.get("Paris"));
    }
    
    @Test
    public void joinNames() {
        // Join all names with comma separator
        String names = people.stream()
                .map(Person::name)
                .collect(Collectors.joining(", "));
        
        assertEquals("Alice, Bob, Charlie, David, Eve", names);
    }
}