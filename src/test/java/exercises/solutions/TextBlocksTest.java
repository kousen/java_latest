package exercises.solutions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextBlocksTest {
    
    @Test
    public void basicTextBlock() {
        // Create a text block for JSON
        String json = """
            {
                "name": "John Doe",
                "age": 30,
                "email": "john@example.com"
            }
            """;
        
        assertTrue(json.contains("\"name\""));
        assertTrue(json.contains("\"age\""));
    }
    
    @Test
    public void textBlockWithFormatting() {
        String name = "Java";
        int version = 21;
        
        // Create formatted text block
        String message = """
            Welcome to %s %d!
            
            This version includes:
            - Virtual Threads
            - Pattern Matching
            - Sequenced Collections
            """.formatted(name, version);
        
        assertTrue(message.contains("Java"));
        assertTrue(message.contains("21"));
    }
    
    @Test
    public void sqlQuery() {
        // Create SQL query using text block
        String query = """
            SELECT u.id, u.name, COUNT(o.id) as order_count
            FROM users u
            LEFT JOIN orders o ON u.id = o.user_id
            WHERE u.active = true
            GROUP BY u.id, u.name
            HAVING COUNT(o.id) > ?
            """;
        
        assertTrue(query.contains("SELECT"));
        assertTrue(query.contains("GROUP BY"));
    }
    
    @Test
    public void htmlTemplate() {
        // Create HTML template
        String html = """
            <!DOCTYPE html>
            <html>
                <head>
                    <title>%s</title>
                </head>
                <body>
                    <h1>%s</h1>
                    <p>%s</p>
                </body>
            </html>
            """.formatted("My Page", "Welcome", "Hello, World!");
        
        assertTrue(html.contains("<title>My Page</title>"));
    }
}