package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class DataOrientedTest {
    
    // Create a sealed hierarchy for different event types
    sealed interface Event permits 
        LoginEvent, LogoutEvent, ErrorEvent, DataEvent {}
    
    record LoginEvent(String userId, LocalDateTime timestamp, String ipAddress) 
        implements Event {}
    record LogoutEvent(String userId, LocalDateTime timestamp) 
        implements Event {}
    record ErrorEvent(String message, LocalDateTime timestamp, String stackTrace) 
        implements Event {}
    record DataEvent(String userId, String action, Map<String, Object> data, LocalDateTime timestamp) 
        implements Event {}
    
    @Test
    public void processEvents() {
        // Create list of different events
        List<Event> events = List.of(
            new LoginEvent("user1", LocalDateTime.now(), "192.168.1.1"),
            new DataEvent("user1", "UPDATE_PROFILE", Map.of("field", "email"), LocalDateTime.now()),
            new ErrorEvent("Connection failed", LocalDateTime.now(), "..."),
            new LogoutEvent("user1", LocalDateTime.now())
        );
        
        // Process events using pattern matching
        for (Event event : events) {
            String summary = switch (event) {
                case LoginEvent(var user, var time, var ip) -> 
                    String.format("%s logged in from %s at %s", user, ip, time);
                case LogoutEvent(var user, var time) -> 
                    String.format("%s logged out at %s", user, time);
                case ErrorEvent(var msg, var time, _) -> 
                    String.format("ERROR at %s: %s", time, msg);
                case DataEvent(var user, var action, var data, _) -> 
                    String.format("%s performed %s with %d fields", user, action, data.size());
            };
            assertNotNull(summary);
        }
    }
    
    @Test
    public void filterEventsByType() {
        // Filter specific event types
        List<Event> events = createMixedEvents();
        
        List<String> loggedInUsers = events.stream()
            .filter(LoginEvent.class::isInstance)
            .map(LoginEvent.class::cast)
            .map(LoginEvent::userId)
            .distinct()
            .toList();
        
        // Or using pattern matching with mapMulti:
        List<String> errors = events.stream()
            .<String>mapMulti((event, consumer) -> {
                if (event instanceof ErrorEvent(var msg, _, _)) {
                    consumer.accept(msg);
                }
            })
            .toList();
        
        assertTrue(loggedInUsers.size() >= 0);
        assertTrue(errors.size() >= 0);
    }
    
    @Test
    public void eventStatistics() {
        // Calculate statistics from events
        List<Event> events = createMixedEvents();
        
        Map<Class<? extends Event>, Long> counts = events.stream()
            .collect(Collectors.groupingBy(
                Event::getClass,
                Collectors.counting()
            ));

        assertFalse(counts.isEmpty());
    }
    
    private List<Event> createMixedEvents() {
        return List.of(
            new LoginEvent("user1", LocalDateTime.now(), "192.168.1.1"),
            new LoginEvent("user2", LocalDateTime.now(), "192.168.1.2"),
            new DataEvent("user1", "UPDATE", Map.of("field", "name"), LocalDateTime.now()),
            new ErrorEvent("Database error", LocalDateTime.now(), "..."),
            new LogoutEvent("user1", LocalDateTime.now())
        );
    }
}