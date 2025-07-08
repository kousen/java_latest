package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionFactoryTest {
    
    @Test
    public void createImmutableList() {
        // Create immutable list using List.of()
        List<String> list = List.of("one", "two", "three");
        
        assertEquals(3, list.size());
        assertThrows(UnsupportedOperationException.class, 
            () -> list.add("four"));
    }
    
    @Test
    public void createImmutableSet() {
        // Create immutable set of numbers 1-5
        Set<Integer> set = Set.of(1, 2, 3, 4, 5);
        
        assertEquals(5, set.size());
        assertTrue(set.contains(3));
    }
    
    @Test
    public void createImmutableMap() {
        // Create map with 3 entries using Map.of()
        Map<String, Integer> map = Map.of(
            "one", 1,
            "two", 2,
            "three", 3
        );
        
        assertEquals(3, map.size());
        assertEquals(1, map.get("one"));
    }
    
    @Test
    public void createLargeImmutableMap() {
        // Create map with more than 10 entries using Map.ofEntries()
        Map<Integer, String> map = Map.ofEntries(
            Map.entry(1, "one"),
            Map.entry(2, "two"),
            Map.entry(3, "three"),
            Map.entry(4, "four"),
            Map.entry(5, "five"),
            Map.entry(6, "six"),
            Map.entry(7, "seven"),
            Map.entry(8, "eight"),
            Map.entry(9, "nine"),
            Map.entry(10, "ten"),
            Map.entry(11, "eleven"),
            Map.entry(12, "twelve")
        );
        
        assertTrue(map.size() > 10);
    }
    
    @Test
    public void copyOf() {
        List<String> mutable = new ArrayList<>();
        mutable.add("a");
        mutable.add("b");
        
        // Create immutable copy
        List<String> immutable = List.copyOf(mutable);
        
        mutable.add("c");
        
        assertEquals(2, immutable.size()); // Copy not affected
        assertEquals(3, mutable.size());
    }
}