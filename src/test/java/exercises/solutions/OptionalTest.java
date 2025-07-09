package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class OptionalTest {
    
    record Address(String street, String city, String zipCode) {}
    record Person(String name, Optional<Address> address) {}
    
    @Test
    public void createOptionals() {
        // Create an Optional containing "Hello"
        Optional<String> hello = Optional.of("Hello");
        
        // Create an empty Optional
        Optional<String> empty = Optional.empty();
        
        // Create an Optional from a nullable value
        String nullable = Math.random() > 0.5 ? "value" : null;
        Optional<String> maybe = Optional.ofNullable(nullable);
        
        assertTrue(hello.isPresent());
        assertFalse(empty.isPresent());
    }
    
    @Test
    public void transformOptional() {
        Optional<String> name = Optional.of("java");
        
        // Transform to uppercase
        Optional<String> upper = name.map(String::toUpperCase);
        
        // Get the length
        Optional<Integer> length = name.map(String::length);
        
        assertEquals("JAVA", upper.get());
        assertEquals(4, length.get());
    }
    
    @Test
    public void chainOptionals() {
        Person person1 = new Person("Alice", 
            Optional.of(new Address("123 Main", "NYC", "10001")));
        Person person2 = new Person("Bob", Optional.empty());
        
        // Get zip code for person1 (should be present)
        Optional<String> zip1 = person1.address()
                .map(Address::zipCode);
        
        // Get zip code for person2 (should be empty)  
        Optional<String> zip2 = person2.address()
                .map(Address::zipCode);
        
        assertEquals("10001", zip1.orElse("Unknown"));
        assertEquals("Unknown", zip2.orElse("Unknown"));
    }
    
    @Test
    public void optionalOrElse() {
        Optional<String> empty = Optional.empty();
        
        // Provide default value
        String result1 = empty.orElse("Default");
        
        // Provide default from supplier
        String result2 = empty.orElseGet(() -> "Generated Default");
        
        // Throw exception if empty
        assertThrows(NoSuchElementException.class, () -> empty.orElseThrow());
        
        assertEquals("Default", result1);
        assertEquals("Generated Default", result2);
    }
}