package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class StringMethodsTest {
    
    @Test
    public void isBlankMethod() {
        // Test isBlank() with various strings
        assertTrue("".isBlank());
        assertTrue("   ".isBlank());
        assertTrue("\t\n".isBlank());
        assertFalse("abc".isBlank());
        assertFalse(" abc ".isBlank());
    }
    
    @Test
    public void stripMethods() {
        String text = "  Hello World  ";
        
        // Use strip(), stripLeading(), stripTrailing()
        assertEquals("Hello World", text.strip());
        assertEquals("Hello World  ", text.stripLeading());
        assertEquals("  Hello World", text.stripTrailing());
    }
    
    @Test
    public void repeatMethod() {
        // Repeat string multiple times
        String repeated = "Java".repeat(3);
        
        assertEquals("JavaJavaJava", repeated);
        
        // Create a line of 50 dashes
        String line = "-".repeat(50);
        assertEquals(50, line.length());
    }
    
    @Test
    public void linesMethod() {
        String multiline = """
            Line 1
            Line 2
            Line 3
            """;
        
        // Count lines
        long count = multiline.lines().count();
        
        // Get lines as list
        List<String> lines = multiline.lines().toList();
        
        assertEquals(3, count);
        assertEquals("Line 2", lines.get(1));
    }
    
    @Test
    public void transformMethod() {
        String input = "hello";
        
        // Use transform() to convert to uppercase and add exclamation
        String result = input.transform(s -> s.toUpperCase() + "!");
        
        assertEquals("HELLO!", result);
    }
}