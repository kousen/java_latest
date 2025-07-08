package exercises.solutions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatternMatchingTest {
    
    @Test
    public void basicPatternMatching() {
        Object obj = "Hello, World!";
        
        // Use pattern matching instead of cast
        if (obj instanceof String s) {
            assertEquals(13, s.length());
            assertEquals("HELLO, WORLD!", s.toUpperCase());
        }
    }
    
    @Test
    public void patternMatchingWithCondition() {
        Object obj = "Java";
        
        // Use pattern matching with additional condition
        if (obj instanceof String s && s.length() > 3) {
            assertTrue(s.length() > 3);
        }
    }
    
    @Test
    public void multiplePatterns() {
        // Create method that handles different types
        String result1 = process(42);
        String result2 = process("Hello");
        String result3 = process(3.14);
        
        assertEquals("Integer: 42", result1);
        assertEquals("String: HELLO", result2);
        assertEquals("Double: 3.14", result3);
    }
    
    private String process(Object obj) {
        // Implement using pattern matching
        if (obj instanceof Integer i) {
            return "Integer: " + i;
        } else if (obj instanceof String s) {
            return "String: " + s.toUpperCase();
        } else if (obj instanceof Double d) {
            return "Double: " + d;
        }
        return "Unknown";
    }
    
    @Test
    public void negativePatterns() {
        Object obj = 123;
        
        // Use negative pattern matching
        if (!(obj instanceof String s)) {
            assertFalse(obj instanceof String);
        } else {
            // s is available here
            fail("Should not reach here");
        }
    }
}