package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class MovieSystemTest {
    
    // Design the data model using records and sealed classes
    
    // Records for data
    record Movie(String id, String title, int year, Set<String> genres, double rating) {}
    record User(String id, String name, Map<String, Integer> ratings) {} // movieId -> rating
    record Recommendation(Movie movie, double score, String reason) {}
    
    // Sealed hierarchy for recommendation strategies
    sealed interface RecommendationStrategy permits 
        GenreBasedStrategy, RatingBasedStrategy, CollaborativeStrategy {}
    
    record GenreBasedStrategy(Set<String> preferredGenres) implements RecommendationStrategy {}
    record RatingBasedStrategy(double minRating) implements RecommendationStrategy {}
    record CollaborativeStrategy(List<User> similarUsers) implements RecommendationStrategy {}
    
    @Test
    public void createMovieCatalog() {
        // Create movie catalog with modern collections
        var movies = List.of(
            new Movie("1", "The Matrix", 1999, Set.of("Sci-Fi", "Action"), 8.7),
            new Movie("2", "Inception", 2010, Set.of("Sci-Fi", "Thriller"), 8.8),
            new Movie("3", "The Godfather", 1972, Set.of("Drama", "Crime"), 9.2),
            new Movie("4", "Pulp Fiction", 1994, Set.of("Drama", "Crime"), 8.9),
            new Movie("5", "The Dark Knight", 2008, Set.of("Action", "Crime"), 9.0)
        );
        
        // Group by genre using collectors
        Map<String, List<Movie>> byGenre = movies.stream()
                .flatMap(movie -> movie.genres().stream().map(genre -> Map.entry(genre, movie)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
        
        // Find top-rated movies using streams
        List<Movie> topRated = movies.stream()
                .filter(movie -> movie.rating() > 9.0)
                .sorted(Comparator.comparing(Movie::rating).reversed())
                .toList();
        
        assertFalse(byGenre.isEmpty());
        assertFalse(topRated.isEmpty());
    }
    
    @Test
    public void recommendationEngine() {
        var movie = new Movie("1", "The Matrix", 1999, Set.of("Sci-Fi", "Action"), 8.7);
        var user = new User("user1", "John", Map.of("1", 5, "2", 4));
        
        // Implement recommendation using pattern matching
        Recommendation result1 = recommend(user, movie, new GenreBasedStrategy(Set.of("Sci-Fi")));
        Recommendation result2 = recommend(user, movie, new RatingBasedStrategy(8.0));
        
        assertNotNull(result1);
        assertNotNull(result2);
    }
    
    private Recommendation recommend(User user, Movie movie, RecommendationStrategy strategy) {
        return switch (strategy) {
            case GenreBasedStrategy(var preferredGenres) -> {
                // Calculate score based on genre match
                long matchingGenres = movie.genres().stream()
                        .filter(preferredGenres::contains)
                        .count();
                double score = (double) matchingGenres / movie.genres().size();
                yield new Recommendation(movie, score, "Genre match");
            }
            case RatingBasedStrategy(var minRating) -> {
                // Check if movie meets rating threshold
                boolean meetsThreshold = movie.rating() >= minRating;
                double score = meetsThreshold ? movie.rating() / 10.0 : 0.0;
                yield new Recommendation(movie, score, "Rating-based");
            }
            case CollaborativeStrategy(var similarUsers) -> {
                // Use other users' ratings
                double avgRating = similarUsers.stream()
                        .mapToInt(u -> u.ratings().getOrDefault(movie.id(), 0))
                        .average()
                        .orElse(0.0);
                yield new Recommendation(movie, avgRating / 5.0, "Collaborative filtering");
            }
        };
    }
    
    @Test
    public void asyncMovieFetching() throws Exception {
        // Use CompletableFuture for async operations
        CompletableFuture<List<Movie>> apiMovies = fetchFromAPI();
        CompletableFuture<List<Movie>> dbMovies = fetchFromDatabase();
        
        // Combine multiple sources
        CompletableFuture<List<Movie>> combined = 
            apiMovies.thenCombine(dbMovies, (list1, list2) -> {
                // Merge and deduplicate
                Set<Movie> unique = new HashSet<>(list1);
                unique.addAll(list2);
                return new ArrayList<>(unique);
            });
        
        List<Movie> result = combined.get();
        assertNotNull(result);
    }
    
    @Test
    public void virtualThreadsForScaling() throws Exception {
        List<User> users = createSampleUsers();
        
        // Use virtual threads for handling many users
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<List<Recommendation>>> futures = users.stream()
                .map(user -> executor.submit(() -> 
                    generateRecommendations(user)))
                .toList();
            
            // Process results
            for (Future<List<Recommendation>> future : futures) {
                List<Recommendation> recommendations = future.get();
                assertNotNull(recommendations);
            }
        }
    }
    
    @Test
    public void advancedFiltering() {
        List<Movie> movies = createSampleMovies();
        User user = new User("user1", "John", Map.of("1", 5));
        Set<String> userPreferences = Set.of("Sci-Fi", "Action");
        
        // Find movies:
        // - Released after 2000
        // - Rating > 7.5
        // - Has at least one genre in user preferences
        // - Not already watched by user
        // - Sort by recommendation score
        
        List<Movie> filtered = movies.stream()
            .filter(movie -> movie.year() > 2000)
            .filter(movie -> movie.rating() > 7.5)
            .filter(movie -> movie.genres().stream().anyMatch(userPreferences::contains))
            .filter(movie -> !user.ratings().containsKey(movie.id()))
            .sorted(Comparator.comparing(Movie::rating).reversed())
            .limit(10)
            .toList();
        
        assertNotNull(filtered);
    }
    
    private CompletableFuture<List<Movie>> fetchFromAPI() {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate API call
            return List.of(
                new Movie("api1", "API Movie 1", 2020, Set.of("Action"), 8.0),
                new Movie("api2", "API Movie 2", 2021, Set.of("Drama"), 7.5)
            );
        });
    }
    
    private CompletableFuture<List<Movie>> fetchFromDatabase() {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate database call
            return List.of(
                new Movie("db1", "DB Movie 1", 2019, Set.of("Comedy"), 7.8),
                new Movie("db2", "DB Movie 2", 2022, Set.of("Thriller"), 8.2)
            );
        });
    }
    
    private List<User> createSampleUsers() {
        return List.of(
            new User("user1", "Alice", Map.of("1", 5, "2", 4)),
            new User("user2", "Bob", Map.of("2", 3, "3", 5)),
            new User("user3", "Charlie", Map.of("1", 4, "3", 4))
        );
    }
    
    private List<Movie> createSampleMovies() {
        return List.of(
            new Movie("1", "The Matrix", 1999, Set.of("Sci-Fi", "Action"), 8.7),
            new Movie("2", "Inception", 2010, Set.of("Sci-Fi", "Thriller"), 8.8),
            new Movie("3", "John Wick", 2014, Set.of("Action", "Thriller"), 7.4),
            new Movie("4", "Blade Runner 2049", 2017, Set.of("Sci-Fi", "Drama"), 8.0)
        );
    }
    
    private List<Recommendation> generateRecommendations(User user) {
        // Simulate recommendation generation
        return List.of(
            new Recommendation(
                new Movie("rec1", "Recommended Movie", 2020, Set.of("Action"), 8.5),
                0.9,
                "High match"
            )
        );
    }
}