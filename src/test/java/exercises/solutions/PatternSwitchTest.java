package exercises.solutions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatternSwitchTest {
    
    record Point(int x, int y) {}
    record Circle(Point center, double radius) {}
    record Rectangle(Point topLeft, double width, double height) {}
    
    @Test
    public void typePatterns() {
        Object obj = "Hello";
        
        // Use type patterns in switch
        String result = switch (obj) {
            case Integer i -> "Number: " + i;
            case String s -> "Text: " + s.toUpperCase();
            case null -> "Null value";
            default -> "Other: " + obj.getClass().getSimpleName();
        };
        
        assertEquals("Text: HELLO", result);
    }
    
    @Test
    public void recordPatterns() {
        Object shape = new Circle(new Point(0, 0), 5.0);
        
        // Deconstruct records in switch
        String description = switch (shape) {
            case Circle(Point(var x, var y), var r) -> 
                String.format("Circle at (%d,%d) with radius %.1f", x, y, r);
            case Rectangle(var topLeft, var w, var h) ->
                String.format("Rectangle at %s, %fx%f", topLeft, w, h);
            default -> "Unknown shape";
        };
        
        assertEquals("Circle at (0,0) with radius 5.0", description);
    }
    
    @Test
    public void guardedPatterns() {
        Object obj = 42;
        
        // Use when clauses (guards)
        String category = switch (obj) {
            case Integer i when i < 0 -> "Negative";
            case Integer i when i == 0 -> "Zero";
            case Integer i when i > 0 && i <= 100 -> "Small positive";
            case Integer i -> "Large positive";
            default -> "Not a number";
        };
        
        assertEquals("Small positive", category);
    }
    
    @Test
    public void dominanceRules() {
        // Understand pattern dominance
        Object value = "test";
        
        String result = switch (value) {
            case String s when s.length() > 5 -> "Long string";
            case String s when s.length() == 4 -> "Four chars";
            case String s -> "Short string";  // Must come last
            case null -> "Null";
            default -> "Not a string";
        };
        
        assertEquals("Four chars", result);
    }
}