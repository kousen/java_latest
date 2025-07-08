package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;

public class SwitchExpressionsTest {
    
    enum Size { SMALL, MEDIUM, LARGE, EXTRA_LARGE }
    
    @Test
    public void basicSwitchExpression() {
        Size size = Size.MEDIUM;
        
        // Convert to switch expression
        int price = switch (size) {
            case SMALL -> 5;
            case MEDIUM -> 10;
            case LARGE -> 15;
            case EXTRA_LARGE -> 20;
        };
        
        assertEquals(10, price);
    }
    
    @Test
    public void switchWithMultipleLabels() {
        DayOfWeek day = DayOfWeek.SATURDAY;
        
        // Categorize days
        String type = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
        };
        
        assertEquals("Weekend", type);
    }
    
    @Test
    public void switchWithYield() {
        Month month = Month.FEBRUARY;
        int year = 2024;
        
        // Calculate days in month using yield
        int days = switch (month) {
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            case FEBRUARY -> {
                // Use yield for complex logic
                yield Year.of(year).isLeap() ? 29 : 28;
            }
        };
        
        assertEquals(29, days); // 2024 is a leap year
    }
    
    @Test
    public void exhaustiveSwitch() {
        // Create exhaustive switch over enum
        Size size = Size.LARGE;
        
        String description = switch (size) {
            // Handle all cases - no default needed!
            case SMALL -> "Small (S)";
            case MEDIUM -> "Medium (M)";
            case LARGE -> "Large (L)";
            case EXTRA_LARGE -> "Extra Large (XL)";
        };
        
        assertNotNull(description);
        assertEquals("Large (L)", description);
    }
}