package exercises.solutions;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StreamGatherersTest {
    
    @Test
    public void movingAverageExample() {
        List<Double> stockPrices = List.of(100.0, 102.0, 98.0, 105.0, 103.0, 107.0, 110.0);
        
        // Calculate 3-day moving average
        List<Double> movingAverages = stockPrices.stream()
                .gather(Gatherers.windowSliding(3))
                .map(window -> window.stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0.0))
                .toList();
        
        assertEquals(5, movingAverages.size());
        assertEquals(100.0, movingAverages.get(0), 0.01); // (100+102+98)/3
        assertEquals(101.67, movingAverages.get(1), 0.01); // (102+98+105)/3
        assertEquals(102.0, movingAverages.get(2), 0.01); // (98+105+103)/3
    }
    
    @Test
    public void batchProcessingExample() {
        List<String> logEntries = List.of(
                "2024-01-01: Login user1",
                "2024-01-01: Access /home",
                "2024-01-01: Access /profile",
                "2024-01-02: Login user2",
                "2024-01-02: Access /dashboard",
                "2024-01-02: Logout user2"
        );
        
        // Process logs in batches of 3
        List<String> batchSummaries = logEntries.stream()
                .gather(Gatherers.windowFixed(3))
                .map(batch -> String.format("Batch of %d entries processed", batch.size()))
                .toList();
        
        assertEquals(2, batchSummaries.size());
        assertEquals("Batch of 3 entries processed", batchSummaries.get(0));
        assertEquals("Batch of 3 entries processed", batchSummaries.get(1));
    }
    
    @Test
    public void runningStatisticsExample() {
        record Stats(int count, int sum, double average) {}
        
        List<Integer> values = List.of(10, 20, 30, 40, 50);
        
        // Calculate running statistics
        List<Stats> runningStats = values.stream()
                .gather(Gatherers.scan(
                        () -> new Stats(0, 0, 0.0),
                        (stats, value) -> new Stats(
                                stats.count + 1,
                                stats.sum + value,
                                (stats.sum + value) / (double) (stats.count + 1)
                        )
                ))
                .toList();
        
        assertEquals(5, runningStats.size());
        assertEquals(new Stats(1, 10, 10.0), runningStats.get(0));
        assertEquals(new Stats(2, 30, 15.0), runningStats.get(1));
        assertEquals(new Stats(5, 150, 30.0), runningStats.get(4));
    }
    
    @Test
    public void transactionGroupingExample() {
        record Transaction(String id, double amount, String category) {}
        
        List<Transaction> transactions = List.of(
                new Transaction("T1", 100.0, "Food"),
                new Transaction("T2", 50.0, "Food"),
                new Transaction("T3", 200.0, "Electronics"),
                new Transaction("T4", 75.0, "Food"),
                new Transaction("T5", 300.0, "Electronics")
        );
        
        // Custom gatherer to group consecutive transactions of same category
        Gatherer<Transaction, ArrayList<Transaction>, List<Transaction>> groupConsecutiveByCategory = Gatherer.ofSequential(
                ArrayList::new,
                Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
                    if (state.isEmpty() || state.get(0).category().equals(element.category())) {
                        state.add(element);
                        return true;
                    } else {
                        boolean result = downstream.push(new ArrayList<>(state));
                        state.clear();
                        state.add(element);
                        return result;
                    }
                }),
                (state, downstream) -> {
                    if (!state.isEmpty()) {
                        downstream.push(new ArrayList<>(state));
                    }
                }
        );
        
        List<List<Transaction>> groupedTransactions = transactions.stream()
                .gather(groupConsecutiveByCategory)
                .toList();
        
        assertEquals(4, groupedTransactions.size());
        assertEquals(2, groupedTransactions.get(0).size()); // T1, T2 (Food)
        assertEquals(1, groupedTransactions.get(1).size()); // T3 (Electronics)
        assertEquals(1, groupedTransactions.get(2).size()); // T4 (Food)
        assertEquals(1, groupedTransactions.get(3).size()); // T5 (Electronics)
    }
    
    @Test
    public void timeSeriesAnalysisExample() {
        record DataPoint(LocalDate date, double value) {}
        
        List<DataPoint> timeSeries = List.of(
                new DataPoint(LocalDate.of(2024, 1, 1), 100.0),
                new DataPoint(LocalDate.of(2024, 1, 2), 110.0),
                new DataPoint(LocalDate.of(2024, 1, 3), 105.0),
                new DataPoint(LocalDate.of(2024, 1, 4), 115.0),
                new DataPoint(LocalDate.of(2024, 1, 5), 120.0)
        );
        
        // Calculate day-over-day changes using sliding window
        List<Double> dailyChanges = timeSeries.stream()
                .gather(Gatherers.windowSliding(2))
                .map(window -> {
                    if (window.size() == 2) {
                        return window.get(1).value() - window.get(0).value();
                    }
                    return 0.0;
                })
                .toList();
        
        assertEquals(4, dailyChanges.size());
        assertEquals(10.0, dailyChanges.get(0)); // 110 - 100
        assertEquals(-5.0, dailyChanges.get(1)); // 105 - 110
        assertEquals(10.0, dailyChanges.get(2)); // 115 - 105
        assertEquals(5.0, dailyChanges.get(3));  // 120 - 115
    }
    
    @Test
    public void parallelBatchProcessingExample() {
        List<String> urls = List.of(
                "https://api1.example.com",
                "https://api2.example.com",
                "https://api3.example.com",
                "https://api4.example.com",
                "https://api5.example.com",
                "https://api6.example.com"
        );
        
        // Process URLs in batches with simulated API calls
        List<String> results = urls.stream()
                .gather(Gatherers.windowFixed(2))
                .flatMap(batch -> batch.stream()
                        .map(url -> "Processed: " + url))
                .toList();
        
        assertEquals(6, results.size());
        assertTrue(results.get(0).contains("api1"));
        assertTrue(results.get(5).contains("api6"));
    }
}