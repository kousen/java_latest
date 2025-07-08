package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecordsTest {
    
    // Create a Point record with x and y coordinates
    record Point(int x, int y) {}
    
    // Create a Person record with validation
    record Person(String name, int age) {
        // Add compact constructor for validation
        public Person {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        }
    }
    
    // Create a Book record with custom methods
    record Book(String title, String author, int year) {
        // Add a method to get display string
        public String displayString() {
            return "%s by %s (%d)".formatted(title, author, year);
        }
    }
    
    @Test
    public void createAndUseRecord() {
        // Create Point instances and test
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        
        assertEquals(3, p1.x());
        assertEquals(4, p1.y());
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
    
    @Test
    public void recordValidation() {
        // Test Person validation
        assertThrows(IllegalArgumentException.class, 
            () -> new Person("", 25));
        assertThrows(IllegalArgumentException.class, 
            () -> new Person("John", -5));
    }
    
    @Test
    public void recordWithMethods() {
        // Test Book custom method
        Book book = new Book("Effective Java", "Joshua Bloch", 2018);
        assertEquals("Effective Java by Joshua Bloch (2018)", 
            book.displayString());
    }
    
    @Test
    public void recordsInCollections() {
        // Use records in collections
        List<Point> pointList = List.of(
            new Point(0, 0),
            new Point(1, 1),
            new Point(0, 0)  // Duplicate
        );
        
        Set<Point> points = new HashSet<>(pointList);
        
        assertEquals(2, points.size());
    }
}