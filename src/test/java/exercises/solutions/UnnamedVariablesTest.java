package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class UnnamedVariablesTest {
    
    record Point(int x, int y) {}
    record Box(Point topLeft, Point bottomRight) {}
    
    @Test
    public void unnamedInCatch() {
        // Use _ in catch blocks
        try {
            Integer.parseInt("not a number");
        } catch (NumberFormatException _) {
            // Don't need exception details
            System.out.println("Invalid number format");
        }
        
        assertTrue(true); // Should reach here
    }
    
    @Test
    public void unnamedInLoops() {
        List<String> items = List.of("a", "b", "c");
        int count = 0;
        
        // Count without using loop variable
        for (String _ : items) {
            count++;
        }
        
        assertEquals(3, count);
    }
    
    @Test
    public void unnamedInPatterns() {
        Point point = new Point(10, 20);
        
        // Extract only x coordinate
        int xOnly = switch (point) {
            case Point(var x, _) -> x;
        };
        
        assertEquals(10, xOnly);
    }
    
    @Test
    public void unnamedInLambdas() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        // Use _ when only need keys or values
        List<String> keys = new ArrayList<>();
        map.forEach((k, _) -> keys.add(k));
        
        assertEquals(3, keys.size());
        
        List<Integer> values = new ArrayList<>();
        map.forEach((_, v) -> values.add(v));
        
        assertEquals(3, values.size());
    }
    
    @Test
    public void nestedPatternWithUnnamed() {
        Box box = new Box(new Point(0, 0), new Point(100, 100));
        
        // Extract only the width (ignore heights)
        int width = switch (box) {
            case Box(Point(var x1, _), Point(var x2, _)) -> x2 - x1;
        };
        
        assertEquals(100, width);
    }
}