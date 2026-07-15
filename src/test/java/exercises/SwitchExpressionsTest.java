package exercises;

import org.junit.jupiter.api.Test;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;

public class SwitchExpressionsTest {

    enum Size { SMALL, MEDIUM, LARGE, EXTRA_LARGE }

    @Test
    public void basicSwitchExpression() {
        Size size = Size.MEDIUM;

        // TODO: Convert to switch expression
        // int price = switch (size) {
        //     case SMALL -> ...;
        //     case MEDIUM -> ...;
        //     case LARGE -> ...;
        //     case EXTRA_LARGE -> ...;
        // };

        // assertEquals(10, price);
    }

    @Test
    public void switchWithMultipleLabels() {
        DayOfWeek day = DayOfWeek.SATURDAY;

        // TODO: Categorize days
        // String type = switch (day) {
        //     case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> ...;
        //     case SATURDAY, SUNDAY -> ...;
        // };

        // assertEquals("Weekend", type);
    }

    @Test
    public void switchWithYield() {
        Month month = Month.FEBRUARY;
        int year = 2024;

        // TODO: Calculate days in month using yield
        // int days = switch (month) {
        //     case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
        //     case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
        //     case FEBRUARY -> {
        //         // Use yield for complex logic
        //         yield ...;
        //     }
        // };

        // assertEquals(29, days); // 2024 is a leap year
    }

    @Test
    public void exhaustiveSwitch() {
        // TODO: Create exhaustive switch over enum
        Size size = Size.LARGE;

        // String description = switch (size) {
        //     // Handle all cases - no default needed!
        // };

        // assertNotNull(description);
    }
}
