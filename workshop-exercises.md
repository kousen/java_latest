# Modern Java Workshop Exercises

This document contains hands-on lab exercises for the Modern Java workshop, covering features from Java 8 through Java 25. Each exercise includes starter code, TODOs, and expected outcomes. Complete solutions are available in the repository's test files.

## Table of Contents

- [Java 8: Functional Programming](#java-8-functional-programming)
  - [Exercise 1: Lambda Expressions and Method References](#exercise-1-lambda-expressions-and-method-references)
  - [Exercise 2: Stream API Basics](#exercise-2-stream-api-basics)
  - [Exercise 3: Advanced Collectors](#exercise-3-advanced-collectors)
  - [Exercise 4: Optional Handling](#exercise-4-optional-handling)
  - [Exercise 5: CompletableFuture Basics](#exercise-5-completablefuture-basics)
- [Java 9-11: Platform Improvements](#java-9-11-platform-improvements)
  - [Exercise 6: Collection Factory Methods](#exercise-6-collection-factory-methods)
  - [Exercise 7: Stream Enhancements](#exercise-7-stream-enhancements)
  - [Exercise 8: HTTP Client](#exercise-8-http-client)
  - [Exercise 9: String Methods](#exercise-9-string-methods)
- [Java 12-17: Language Evolution](#java-12-17-language-evolution)
  - [Exercise 10: Text Blocks](#exercise-10-text-blocks)
  - [Exercise 11: Records](#exercise-11-records)
  - [Exercise 12: Pattern Matching instanceof](#exercise-12-pattern-matching-instanceof)
  - [Exercise 13: Switch Expressions](#exercise-13-switch-expressions)
  - [Exercise 14: Sealed Classes](#exercise-14-sealed-classes)
- [Java 18-21: Modern Features](#java-18-21-modern-features)
  - [Exercise 15: Pattern Matching in Switch](#exercise-15-pattern-matching-in-switch)
  - [Exercise 16: Virtual Threads](#exercise-16-virtual-threads)
  - [Exercise 17: Sequenced Collections](#exercise-17-sequenced-collections)
- [Java 22+: Cutting Edge](#java-22-cutting-edge)
  - [Exercise 18: Unnamed Variables](#exercise-18-unnamed-variables)
  - [Exercise 19: Data-Oriented Programming](#exercise-19-data-oriented-programming)
- [Capstone Project](#capstone-project)
- [Running the Exercises](#running-the-exercises)
- [Tips and Best Practices](#tips-and-best-practices)

## Java 8: Functional Programming

### Exercise 1: Lambda Expressions and Method References

**Learning Objectives:** Master lambda syntax and method reference variations

Create a new test file: `src/test/java/exercises/LambdaBasicsTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.*;
import static org.junit.jupiter.api.Assertions.*;

public class LambdaBasicsTest {
    
    @Test
    public void sortWithLambda() {
        List<String> words = Arrays.asList("lambda", "expression", "java", "functional");
        
        // TODO: Sort words by length using a lambda expression
        // words.sort(...);
        
        assertEquals("java", words.get(0));
        assertEquals("expression", words.get(3));
    }
    
    @Test
    public void sortWithMethodReference() {
        List<String> words = Arrays.asList("lambda", "expression", "java", "functional");
        
        // TODO: Sort words by length using Comparator.comparingInt and method reference
        // words.sort(...);
        
        assertEquals("java", words.getFirst());
    }
    
    @Test
    public void implementConsumer() {
        List<String> result = new ArrayList<>();
        
        // TODO: Create a Consumer<String> that adds strings to result list
        // Consumer<String> consumer = ...;
        
        // consumer.accept("Hello");
        // consumer.accept("World");
        
        assertEquals(Arrays.asList("Hello", "World"), result);
    }
    
    @Test
    public void implementSupplier() {
        // TODO: Create a Supplier<Double> using Math.random
        // Supplier<Double> randomSupplier = ...;
        
        // double value = randomSupplier.get();
        // assertTrue(value >= 0.0 && value < 1.0);
    }
    
    @Test
    public void implementPredicate() {
        // TODO: Create a Predicate<String> that tests if length > 5
        // Predicate<String> isLong = ...;
        
        // assertTrue(isLong.test("functional"));
        // assertFalse(isLong.test("java"));
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 2: Stream API Basics

**Learning Objectives:** Practice fundamental stream operations

Create: `src/test/java/exercises/StreamBasicsTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class StreamBasicsTest {
    
    private final List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
    
    @Test
    public void filterAndCount() {
        // TODO: Count how many numbers are greater than 4
        // long count = numbers.stream()...
        
        // assertEquals(5, count);
    }
    
    @Test
    public void mapAndSum() {
        // TODO: Square each number and sum the results
        // int sum = numbers.stream()...
        
        // assertEquals(159, sum);
    }
    
    @Test
    public void findFirstEven() {
        // TODO: Find the first even number
        // Optional<Integer> firstEven = numbers.stream()...
        
        // assertTrue(firstEven.isPresent());
        // assertEquals(4, firstEven.get());
    }
    
    @Test
    public void distinctAndSorted() {
        // TODO: Get distinct numbers in sorted order
        // List<Integer> result = numbers.stream()...
        
        // assertEquals(List.of(1, 2, 3, 4, 5, 6, 9), result);
    }
    
    @Test
    public void maxValue() {
        // TODO: Find the maximum value
        // Optional<Integer> max = numbers.stream()...
        
        // assertEquals(9, max.orElse(0));
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 3: Advanced Collectors

**Learning Objectives:** Master Collectors API including groupingBy and custom collectors

Create: `src/test/java/exercises/CollectorsTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class CollectorsTest {
    
    record Person(String name, int age, String city) {}
    
    private final List<Person> people = List.of(
        new Person("Alice", 25, "New York"),
        new Person("Bob", 30, "London"),
        new Person("Charlie", 25, "New York"),
        new Person("David", 35, "London"),
        new Person("Eve", 30, "Paris")
    );
    
    @Test
    public void groupByCity() {
        // TODO: Group people by city
        // Map<String, List<Person>> byCity = people.stream()...
        
        // assertEquals(2, byCity.get("New York").size());
        // assertEquals(2, byCity.get("London").size());
        // assertEquals(1, byCity.get("Paris").size());
    }
    
    @Test
    public void groupByAgeWithCounting() {
        // TODO: Group by age and count people in each age group
        // Map<Integer, Long> ageCount = people.stream()...
        
        // assertEquals(2L, ageCount.get(25));
        // assertEquals(2L, ageCount.get(30));
        // assertEquals(1L, ageCount.get(35));
    }
    
    @Test
    public void partitionByAge() {
        // TODO: Partition people into those 30 or older vs younger
        // Map<Boolean, List<Person>> partitioned = people.stream()...
        
        // assertEquals(3, partitioned.get(true).size());  // 30 or older
        // assertEquals(2, partitioned.get(false).size()); // younger than 30
    }
    
    @Test
    public void averageAgeByCity() {
        // TODO: Calculate average age by city
        // Map<String, Double> avgAgeByCity = people.stream()...
        
        // assertEquals(25.0, avgAgeByCity.get("New York"));
        // assertEquals(32.5, avgAgeByCity.get("London"));
        // assertEquals(30.0, avgAgeByCity.get("Paris"));
    }
    
    @Test
    public void joinNames() {
        // TODO: Join all names with comma separator
        // String names = people.stream()...
        
        // assertEquals("Alice, Bob, Charlie, David, Eve", names);
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 4: Optional Handling

**Learning Objectives:** Practice safe null handling with Optional

Create: `src/test/java/exercises/OptionalTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class OptionalTest {
    
    record Address(String street, String city, String zipCode) {}
    record Person(String name, Optional<Address> address) {}
    
    @Test
    public void createOptionals() {
        // TODO: Create an Optional containing "Hello"
        // Optional<String> hello = ...;
        
        // TODO: Create an empty Optional
        // Optional<String> empty = ...;
        
        // TODO: Create an Optional from a nullable value
        String nullable = Math.random() > 0.5 ? "value" : null;
        // Optional<String> maybe = ...;
        
        // assertTrue(hello.isPresent());
        // assertFalse(empty.isPresent());
    }
    
    @Test
    public void transformOptional() {
        Optional<String> name = Optional.of("java");
        
        // TODO: Transform to uppercase
        // Optional<String> upper = name...;
        
        // TODO: Get the length
        // Optional<Integer> length = name...;
        
        // assertEquals("JAVA", upper.get());
        // assertEquals(4, length.get());
    }
    
    @Test
    public void chainOptionals() {
        Person person1 = new Person("Alice", 
            Optional.of(new Address("123 Main", "NYC", "10001")));
        Person person2 = new Person("Bob", Optional.empty());
        
        // TODO: Get zip code for person1 (should be present)
        // Optional<String> zip1 = ...;
        
        // TODO: Get zip code for person2 (should be empty)  
        // Optional<String> zip2 = ...;
        
        // assertEquals("10001", zip1.orElse("Unknown"));
        // assertEquals("Unknown", zip2.orElse("Unknown"));
    }
    
    @Test
    public void optionalOrElse() {
        Optional<String> empty = Optional.empty();
        
        // TODO: Provide default value
        // String result1 = empty...;
        
        // TODO: Provide default from supplier
        // String result2 = empty...;
        
        // TODO: Throw exception if empty
        // assertThrows(NoSuchElementException.class, () -> {
        //     empty...;
        // });
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 5: CompletableFuture Basics

**Learning Objectives:** Introduction to async programming with CompletableFuture

Create: `src/test/java/exercises/CompletableFutureTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

public class CompletableFutureTest {
    
    @Test
    public void createCompletableFuture() {
        // TODO: Create a CompletableFuture that returns "Hello"
        // CompletableFuture<String> future = CompletableFuture...;
        
        // assertEquals("Hello", future.get());
    }
    
    @Test
    public void asyncComputation() {
        // TODO: Create async computation that returns 42 after delay
        // CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
        //     // Simulate some work
        //     return 42;
        // });
        
        // assertEquals(42, future.get());
    }
    
    @Test
    public void chainOperations() {
        // TODO: Chain operations: get number, double it, convert to string
        // CompletableFuture<String> result = CompletableFuture
        //     .supplyAsync(() -> 21)
        //     .thenApply(...)
        //     .thenApply(...);
        
        // assertEquals("42", result.get());
    }
    
    @Test
    public void combineResults() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);
        
        // TODO: Combine both results by adding them
        // CompletableFuture<Integer> combined = future1.thenCombine(...);
        
        // assertEquals(30, combined.get());
    }
    
    @Test
    public void handleException() {
        // TODO: Handle exception in async computation
        // CompletableFuture<String> future = CompletableFuture
        //     .supplyAsync(() -> {
        //         throw new RuntimeException("Oops!");
        //     })
        //     .exceptionally(...);
        
        // assertEquals("Error occurred", future.get());
    }
}
```

[Back to Table of Contents](#table-of-contents)

## Java 9-11: Platform Improvements

### Exercise 6: Collection Factory Methods

**Learning Objectives:** Use convenient collection creation methods

Create: `src/test/java/exercises/CollectionFactoryTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionFactoryTest {
    
    @Test
    public void createImmutableList() {
        // TODO: Create immutable list using List.of()
        // List<String> list = ...;
        
        // assertEquals(3, list.size());
        // assertThrows(UnsupportedOperationException.class, 
        //     () -> list.add("four"));
    }
    
    @Test
    public void createImmutableSet() {
        // TODO: Create immutable set of numbers 1-5
        // Set<Integer> set = ...;
        
        // assertEquals(5, set.size());
        // assertTrue(set.contains(3));
    }
    
    @Test
    public void createImmutableMap() {
        // TODO: Create map with 3 entries using Map.of()
        // Map<String, Integer> map = ...;
        
        // assertEquals(3, map.size());
        // assertEquals(1, map.get("one"));
    }
    
    @Test
    public void createLargeImmutableMap() {
        // TODO: Create map with more than 10 entries using Map.ofEntries()
        // Map<Integer, String> map = Map.ofEntries(
        //     ...
        // );
        
        // assertTrue(map.size() > 10);
    }
    
    @Test
    public void copyOf() {
        List<String> mutable = new ArrayList<>();
        mutable.add("a");
        mutable.add("b");
        
        // TODO: Create immutable copy
        // List<String> immutable = ...;
        
        mutable.add("c");
        
        // assertEquals(2, immutable.size()); // Copy not affected
        // assertEquals(3, mutable.size());
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 7: Stream Enhancements

**Learning Objectives:** Use Java 9+ stream methods

Create: `src/test/java/exercises/StreamEnhancementsTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class StreamEnhancementsTest {
    
    @Test
    public void takeWhileExample() {
        List<Integer> numbers = List.of(2, 4, 6, 7, 8, 9, 10);
        
        // TODO: Take elements while they are even
        // List<Integer> result = numbers.stream()...
        
        // assertEquals(List.of(2, 4, 6), result);
    }
    
    @Test
    public void dropWhileExample() {
        List<Integer> numbers = List.of(2, 4, 6, 7, 8, 9, 10);
        
        // TODO: Drop elements while they are even
        // List<Integer> result = numbers.stream()...
        
        // assertEquals(List.of(7, 8, 9, 10), result);
    }
    
    /** @noinspection UnusedAssignment*/
    @Test
    public void ofNullableExample() {
        String value = null;
        
        // TODO: Create stream from nullable value
        // long count = Stream...
        
        // assertEquals(0, count);
        
        value = "hello";
        // count = Stream...
        // assertEquals(1, count);
    }
    
    @Test
    public void iterateWithPredicate() {
        // TODO: Generate powers of 2 less than 100
        // List<Integer> powers = Stream.iterate(...)
        //     .collect(Collectors.toList());
        
        // assertEquals(List.of(1, 2, 4, 8, 16, 32, 64), powers);
    }
    
    @Test
    public void toListConvenience() {
        // TODO: Use Java 16's toList() instead of collect(Collectors.toList())
        // List<String> result = Stream.of("a", "b", "c")...
        
        // assertEquals(3, result.size());
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 8: HTTP Client

**Learning Objectives:** Use modern HTTP Client API

**Important Note:** As of Java 21, HttpClient implements AutoCloseable. However, avoid using try-with-resources with async operations as it will close the client before the response arrives!

Create: `src/test/java/exercises/HttpClientTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

public class HttpClientTest {
    
    // Note: In production code, prefer static final for HttpClient
    // For tests, instance fields are acceptable
    private final HttpClient client = HttpClient.newHttpClient();
    
    @Test
    public void synchronousGet() throws Exception {
        // TODO: Create GET request to JSONPlaceholder
        // HttpRequest request = HttpRequest.newBuilder()
        //     .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
        //     ...
        //     .build();
        
        // TODO: Send request and get response
        // HttpResponse<String> response = client.send(...);
        
        // assertEquals(200, response.statusCode());
        // assertTrue(response.body().contains("userId"));
        // assertTrue(response.body().contains("title"));
    }
    
    @Test
    public void asynchronousGet() throws Exception {
        // TODO: Create async GET request for user data
        // HttpRequest request = HttpRequest.newBuilder()
        //     .uri(URI.create("https://jsonplaceholder.typicode.com/users/1"))
        //     ...
        //     .build();
        
        // TODO: Send async and handle response
        // CompletableFuture<String> future = client
        //     .sendAsync(request, HttpResponse.BodyHandlers.ofString())
        //     .thenApply(...);
        
        // String body = future.get(5, TimeUnit.SECONDS);
        // assertTrue(body.contains("Leanne Graham"));
        // assertTrue(body.contains("Bret"));
    }
    
    @Test
    public void postWithJson() throws Exception {
        String json = """
            {
                "title": "Learning Modern Java",
                "body": "Java 21 has many great features",
                "userId": 1
            }
            """;
        
        // TODO: Create POST request with JSON body
        // HttpRequest request = HttpRequest.newBuilder()
        //     .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
        //     .header("Content-Type", "application/json")
        //     ...
        //     .build();
        
        // HttpResponse<String> response = client.send(...);
        // assertEquals(201, response.statusCode()); // 201 Created
        // assertTrue(response.body().contains("Learning Modern Java"));
        // assertTrue(response.body().contains("id")); // JSONPlaceholder adds an ID
    }
    
    @Test
    public void customClient() {
        // TODO: Create client with timeout and redirect policy
        // HttpClient customClient = HttpClient.newBuilder()
        //     ...
        //     .build();
        
        // assertNotNull(customClient);
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 9: String Methods

**Learning Objectives:** Use Java 11 string enhancements

Create: `src/test/java/exercises/StringMethodsTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class StringMethodsTest {
    
    @Test
    public void isBlankMethod() {
        // TODO: Test isBlank() with various strings
        // assertTrue("".isBlank());
        // assertTrue("   ".isBlank());
        // assertTrue("\t\n".isBlank());
        // assertFalse("abc".isBlank());
        // assertFalse(" abc ".isBlank());
    }
    
    @Test
    public void stripMethods() {
        String text = "  Hello World  ";
        
        // TODO: Use strip(), stripLeading(), stripTrailing()
        // assertEquals(..., text.strip());
        // assertEquals(..., text.stripLeading());
        // assertEquals(..., text.stripTrailing());
    }
    
    @Test
    public void repeatMethod() {
        // TODO: Repeat string multiple times
        // String repeated = "Java".repeat(3);
        
        // assertEquals(..., repeated);
        
        // TODO: Create a line of 50 dashes
        // String line = ...;
        // assertEquals(50, line.length());
    }
    
    @Test
    public void linesMethod() {
        String multiline = """
            Line 1
            Line 2
            Line 3
            """;
        
        // TODO: Count lines
        // long count = multiline.lines()...;
        
        // TODO: Get lines as list
        // List<String> lines = multiline.lines()...;
        
        // assertEquals(3, count);
        // assertEquals("Line 2", lines.get(1));
    }
    
    @Test
    public void transformMethod() {
        String input = "hello";
        
        // TODO: Use transform() to convert to uppercase and add exclamation
        // String result = input.transform(s -> ...);
        
        // assertEquals("HELLO!", result);
    }
}
```

[Back to Table of Contents](#table-of-contents)

## Java 12-17: Language Evolution

### Exercise 10: Text Blocks

**Learning Objectives:** Master multi-line string literals

Create: `src/test/java/exercises/TextBlocksTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextBlocksTest {
    
    @Test
    public void basicTextBlock() {
        // TODO: Create a text block for JSON
        // String json = """
        //     ...
        //     """;
        
        // assertTrue(json.contains("\"name\""));
        // assertTrue(json.contains("\"age\""));
    }
    
    @Test
    public void textBlockWithFormatting() {
        String name = "Java";
        int version = 21;
        
        // TODO: Create formatted text block
        // String message = """
        //     ...
        //     """.formatted(name, version);
        
        // assertTrue(message.contains("Java"));
        // assertTrue(message.contains("21"));
    }
    
    @Test
    public void sqlQuery() {
        // TODO: Create SQL query using text block
        // String query = """
        //     SELECT u.id, u.name, COUNT(o.id) as order_count
        //     FROM users u
        //     LEFT JOIN orders o ON u.id = o.user_id
        //     WHERE u.active = true
        //     GROUP BY u.id, u.name
        //     HAVING COUNT(o.id) > ?
        //     """;
        
        // assertTrue(query.contains("SELECT"));
        // assertTrue(query.contains("GROUP BY"));
    }
    
    @Test
    public void htmlTemplate() {
        // TODO: Create HTML template
        // String html = """
        //     <!DOCTYPE html>
        //     <html>
        //         <head>
        //             <title>%s</title>
        //         </head>
        //         <body>
        //             <h1>%s</h1>
        //             <p>%s</p>
        //         </body>
        //     </html>
        //     """.formatted("My Page", "Welcome", "Hello, World!");
        
        // assertTrue(html.contains("<title>My Page</title>"));
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 11: Records

**Learning Objectives:** Create and use record classes

Create: `src/test/java/exercises/RecordsTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecordsTest {
    
    // TODO: Create a Point record with x and y coordinates
    // record Point(...) {}
    
    // TODO: Create a Person record with validation
    // record Person(String name, int age) {
    //     // Add compact constructor for validation
    // }
    
    // TODO: Create a Book record with custom methods
    // record Book(String title, String author, int year) {
    //     // Add a method to get display string
    // }
    
    @Test
    public void createAndUseRecord() {
        // TODO: Create Point instances and test
        // Point p1 = new Point(3, 4);
        // Point p2 = new Point(3, 4);
        
        // assertEquals(3, p1.x());
        // assertEquals(4, p1.y());
        // assertEquals(p1, p2);
        // assertEquals(p1.hashCode(), p2.hashCode());
    }
    
    @Test
    public void recordValidation() {
        // TODO: Test Person validation
        // assertThrows(IllegalArgumentException.class, 
        //     () -> new Person("", 25));
        // assertThrows(IllegalArgumentException.class, 
        //     () -> new Person("John", -5));
    }
    
    @Test
    public void recordWithMethods() {
        // TODO: Test Book custom method
        // Book book = new Book("Effective Java", "Joshua Bloch", 2018);
        // assertEquals("Effective Java by Joshua Bloch (2018)", 
        //     book.displayString());
    }
    
    @Test
    public void recordsInCollections() {
        // TODO: Use records in collections
        // Set<Point> points = Set.of(
        //     new Point(0, 0),
        //     new Point(1, 1),
        //     new Point(0, 0)  // Duplicate
        // );
        
        // assertEquals(2, points.size());
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 12: Pattern Matching instanceof

**Learning Objectives:** Use pattern matching with instanceof

Create: `src/test/java/exercises/PatternMatchingTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatternMatchingTest {
    
    @Test
    public void basicPatternMatching() {
        Object obj = "Hello, World!";
        
        // TODO: Use pattern matching instead of cast
        // if (obj instanceof String s) {
        //     assertEquals(13, ...);
        //     assertEquals("HELLO, WORLD!", ...);
        // }
    }
    
    @Test
    public void patternMatchingWithCondition() {
        Object obj = "Java";
        
        // TODO: Use pattern matching with additional condition
        // if (obj instanceof String s && s.length() > 3) {
        //     assertTrue(...);
        // }
    }
    
    @Test
    public void multiplePatterns() {
        // TODO: Create method that handles different types
        String result1 = process(42);
        String result2 = process("Hello");
        String result3 = process(3.14);
        
        assertEquals("Integer: 42", result1);
        assertEquals("String: HELLO", result2);
        assertEquals("Double: 3.14", result3);
    }
    
    private String process(Object obj) {
        // TODO: Implement using pattern matching
        // if (obj instanceof Integer i) {
        //     return ...;
        // } else if (obj instanceof String s) {
        //     return ...;
        // } else if (obj instanceof Double d) {
        //     return ...;
        // }
        return "Unknown";
    }
    
    @Test
    public void negativePatterns() {
        Object obj = 123;
        
        // TODO: Use negative pattern matching
        // if (!(obj instanceof String s)) {
        //     assertFalse(obj instanceof String);
        // } else {
        //     // s is available here
        // }
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 13: Switch Expressions

**Learning Objectives:** Master new switch expression syntax

Create: `src/test/java/exercises/SwitchExpressionsTest.java`

```java
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
```

[Back to Table of Contents](#table-of-contents)

### Exercise 14: Sealed Classes

**Learning Objectives:** Design type hierarchies with sealed classes

Create: `src/test/java/exercises/SealedClassesTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SealedClassesTest {
    
    // TODO: Create sealed class hierarchy for shapes
    // sealed abstract class Shape permits Circle, Rectangle, Triangle {
    //     abstract double area();
    // }
    
    // final class Circle extends Shape {
    //     private final double radius;
    //     // Constructor and area() implementation
    // }
    
    // final class Rectangle extends Shape {
    //     private final double width, height;
    //     // Constructor and area() implementation
    // }
    
    // final class Triangle extends Shape {
    //     private final double base, height;
    //     // Constructor and area() implementation
    // }
    
    @Test
    public void createShapes() {
        // TODO: Create instances and calculate areas
        // Shape circle = new Circle(5);
        // Shape rectangle = new Rectangle(4, 6);
        // Shape triangle = new Triangle(3, 4);
        
        // assertEquals(Math.PI * 25, circle.area(), 0.001);
        // assertEquals(24, rectangle.area());
        // assertEquals(6, triangle.area());
    }
    
    @Test
    public void exhaustiveSwitchWithSealed() {
        // TODO: Use exhaustive switch with sealed classes
        // Shape shape = new Circle(10);
        
        // String description = switch (shape) {
        //     case Circle c -> "Circle with radius " + c.radius;
        //     case Rectangle r -> "Rectangle " + r.width + "x" + r.height;
        //     case Triangle t -> "Triangle with base " + t.base;
        //     // No default needed!
        // };
        
        // assertTrue(description.contains("Circle"));
    }
    
    // TODO: Create sealed interface hierarchy
    // sealed interface Result<T> permits Success, Failure {}
    // record Success<T>(T value) implements Result<T> {}
    // record Failure<T>(String error) implements Result<T> {}
    
    @Test
    public void sealedInterfaces() {
        // TODO: Use Result pattern
        // Result<Integer> success = new Success<>(42);
        // Result<Integer> failure = new Failure<>("Not found");
        
        // String message1 = switch (success) {
        //     case Success(var value) -> "Got: " + value;
        //     case Failure(var error) -> "Error: " + error;
        // };
        
        // assertEquals("Got: 42", message1);
    }
}
```

[Back to Table of Contents](#table-of-contents)

## Java 18-21: Modern Features

### Exercise 15: Pattern Matching in Switch

**Learning Objectives:** Advanced pattern matching with switch

Create: `src/test/java/exercises/PatternSwitchTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatternSwitchTest {
    
    record Point(int x, int y) {}
    record Circle(Point center, double radius) {}
    record Rectangle(Point topLeft, double width, double height) {}
    
    @Test
    public void typePatterns() {
        Object obj = "Hello";
        
        // TODO: Use type patterns in switch
        // String result = switch (obj) {
        //     case Integer i -> "Number: " + i;
        //     case String s -> "Text: " + s.toUpperCase();
        //     case null -> "Null value";
        //     default -> "Other: " + obj.getClass().getSimpleName();
        // };
        
        // assertEquals("Text: HELLO", result);
    }
    
    @Test
    public void recordPatterns() {
        Object shape = new Circle(new Point(0, 0), 5.0);
        
        // TODO: Deconstruct records in switch
        // String description = switch (shape) {
        //     case Circle(Point(var x, var y), var r) -> 
        //         String.format("Circle at (%d,%d) with radius %.1f", x, y, r);
        //     case Rectangle(var topLeft, var w, var h) ->
        //         String.format("Rectangle at %s, %fx%f", topLeft, w, h);
        //     default -> "Unknown shape";
        // };
        
        // assertEquals("Circle at (0,0) with radius 5.0", description);
    }
    
    @Test
    public void guardedPatterns() {
        Object obj = 42;
        
        // TODO: Use when clauses (guards)
        // String category = switch (obj) {
        //     case Integer i when i < 0 -> "Negative";
        //     case Integer i when i == 0 -> "Zero";
        //     case Integer i when i > 0 && i <= 100 -> "Small positive";
        //     case Integer i -> "Large positive";
        //     default -> "Not a number";
        // };
        
        // assertEquals("Small positive", category);
    }
    
    @Test
    public void dominanceRules() {
        // TODO: Understand pattern dominance
        Object value = "test";
        
        // String result = switch (value) {
        //     case String s when s.length() > 5 -> "Long string";
        //     case String s when s.length() == 4 -> "Four chars";
        //     case String s -> "Short string";  // Must come last
        //     case null -> "Null";
        //     default -> "Not a string";
        // };
        
        // assertEquals("Four chars", result);
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 16: Virtual Threads

**Learning Objectives:** Leverage lightweight threads for massive concurrency

Create: `src/test/java/exercises/VirtualThreadsTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.concurrent.*;
import java.util.stream.*;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;

public class VirtualThreadsTest {
    
    @Test
    public void createVirtualThread() {
        // TODO: Create and start a virtual thread
        // Thread vThread = Thread.ofVirtual()
        //     .name("my-virtual-thread")
        //     .start(() -> {
        //         System.out.println("Running in: " + Thread.currentThread());
        //     });
        
        // vThread.join();
        // assertTrue(vThread.isVirtual());
    }
    
    @Test
    public void virtualThreadExecutor() {
        // TODO: Use virtual thread executor
        // try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
        //     Future<String> future = executor.submit(() -> {
        //         Thread.sleep(100);
        //         return "Done in " + Thread.currentThread();
        //     });
        //     
        //     assertEquals("Done", future.get().substring(0, 4));
        // }
    }
    
    @Test
    public void massiveConcurrency() {
        int taskCount = 10_000;
        CountDownLatch latch = new CountDownLatch(taskCount);
        
        // TODO: Create many virtual threads
        // long start = System.currentTimeMillis();
        
        // try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
        //     for (int i = 0; i < taskCount; i++) {
        //         executor.submit(() -> {
        //             try {
        //                 Thread.sleep(100); // Simulate I/O
        //                 latch.countDown();
        //             } catch (InterruptedException e) {
        //                 Thread.currentThread().interrupt();
        //             }
        //         });
        //     }
        // }
        
        // assertTrue(latch.await(5, TimeUnit.SECONDS));
        // long elapsed = System.currentTimeMillis() - start;
        // assertTrue(elapsed < 1000); // Should complete quickly!
    }
    
    @Test
    public void platformVsVirtual() {
        // TODO: Compare platform threads vs virtual threads
        
        // Platform threads (limited scalability)
        // var platformExecutor = Executors.newFixedThreadPool(100);
        
        // Virtual threads (massive scalability)  
        // var virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
        
        // Run same workload on both and compare
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 17: Sequenced Collections

**Learning Objectives:** Use new sequenced collection interfaces

Create: `src/test/java/exercises/SequencedCollectionsTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SequencedCollectionsTest {
    
    @Test
    public void sequencedList() {
        // TODO: Use SequencedCollection methods
        // List<String> list = new ArrayList<>();
        // list.add("first");
        // list.add("middle");
        // list.add("last");
        
        // assertEquals("first", list.getFirst());
        // assertEquals("last", list.getLast());
        
        // list.addFirst("new-first");
        // list.addLast("new-last");
        
        // assertEquals("new-first", list.getFirst());
        // assertEquals("new-last", list.getLast());
    }
    
    @Test
    public void reversedView() {
        // TODO: Use reversed() method
        // SequencedCollection<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        // SequencedCollection<Integer> reversed = numbers.reversed();
        
        // assertEquals(List.of(5, 4, 3, 2, 1), new ArrayList<>(reversed));
        
        // Add to reversed view
        // reversed.addFirst(6);
        // assertEquals(6, numbers.getLast());
    }
    
    @Test
    public void sequencedSet() {
        // TODO: Use SequencedSet
        // SequencedSet<String> set = new LinkedHashSet<>();
        // set.add("a");
        // set.add("b");
        // set.add("c");
        
        // assertEquals("a", set.getFirst());
        // assertEquals("c", set.getLast());
        
        // SequencedSet<String> reversed = set.reversed();
        // assertEquals("c", reversed.getFirst());
    }
    
    @Test
    public void sequencedMap() {
        // TODO: Use SequencedMap
        // SequencedMap<Integer, String> map = new LinkedHashMap<>();
        // map.put(1, "one");
        // map.put(2, "two");
        // map.put(3, "three");
        
        // Map.Entry<Integer, String> first = map.firstEntry();
        // Map.Entry<Integer, String> last = map.lastEntry();
        
        // assertEquals(1, first.getKey());
        // assertEquals(3, last.getKey());
        
        // map.putFirst(0, "zero");
        // assertEquals("zero", map.firstEntry().getValue());
    }
}
```

[Back to Table of Contents](#table-of-contents)

## Java 22+: Cutting Edge

### Exercise 18: Unnamed Variables

**Learning Objectives:** Use _ for unused variables

Create: `src/test/java/exercises/UnnamedVariablesTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class UnnamedVariablesTest {
    
    record Point(int x, int y) {}
    record Box(Point topLeft, Point bottomRight) {}
    
    @Test
    public void unnamedInCatch() {
        // TODO: Use _ in catch blocks
        // try {
        //     Integer.parseInt("not a number");
        // } catch (NumberFormatException _) {
        //     // Don't need exception details
        //     System.out.println("Invalid number format");
        // }
        
        assertTrue(true); // Should reach here
    }
    
    @Test
    public void unnamedInLoops() {
        List<String> items = List.of("a", "b", "c");
        int count = 0;
        
        // TODO: Count without using loop variable
        // for (String _ : items) {
        //     count++;
        // }
        
        // assertEquals(3, count);
    }
    
    @Test
    public void unnamedInPatterns() {
        Point point = new Point(10, 20);
        
        // TODO: Extract only x coordinate
        // int xOnly = switch (point) {
        //     case Point(var x, _) -> x;
        // };
        
        // assertEquals(10, xOnly);
    }
    
    @Test
    public void unnamedInLambdas() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        // TODO: Use _ when only need keys or values
        // List<String> keys = new ArrayList<>();
        // map.forEach((k, _) -> keys.add(k));
        
        // assertEquals(3, keys.size());
        
        // List<Integer> values = new ArrayList<>();
        // map.forEach((_, v) -> values.add(v));
        
        // assertEquals(3, values.size());
    }
    
    @Test
    public void nestedPatternWithUnnamed() {
        Box box = new Box(new Point(0, 0), new Point(100, 100));
        
        // TODO: Extract only the width (ignore heights)
        // int width = switch (box) {
        //     case Box(Point(var x1, _), Point(var x2, _)) -> x2 - x1;
        // };
        
        // assertEquals(100, width);
    }
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 19: Data-Oriented Programming

**Learning Objectives:** Combine records, sealed classes, and pattern matching

Create: `src/test/java/exercises/DataOrientedTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DataOrientedTest {
    
    // TODO: Create a sealed hierarchy for different event types
    // sealed interface Event permits 
    //     LoginEvent, LogoutEvent, ErrorEvent, DataEvent {}
    
    // record LoginEvent(String userId, LocalDateTime timestamp, String ipAddress) 
    //     implements Event {}
    // record LogoutEvent(String userId, LocalDateTime timestamp) 
    //     implements Event {}
    // record ErrorEvent(String message, LocalDateTime timestamp, String stackTrace) 
    //     implements Event {}
    // record DataEvent(String userId, String action, Map<String, Object> data, LocalDateTime timestamp) 
    //     implements Event {}
    
    @Test
    public void processEvents() {
        // TODO: Create list of different events
        // List<Event> events = List.of(
        //     new LoginEvent("user1", LocalDateTime.now(), "192.168.1.1"),
        //     new DataEvent("user1", "UPDATE_PROFILE", Map.of("field", "email"), LocalDateTime.now()),
        //     new ErrorEvent("Connection failed", LocalDateTime.now(), "..."),
        //     new LogoutEvent("user1", LocalDateTime.now())
        // );
        
        // TODO: Process events using pattern matching
        // for (Event event : events) {
        //     String summary = switch (event) {
        //         case LoginEvent(var user, var time, var ip) -> 
        //             String.format("%s logged in from %s at %s", user, ip, time);
        //         case LogoutEvent(var user, var time) -> 
        //             String.format("%s logged out at %s", user, time);
        //         case ErrorEvent(var msg, var time, _) -> 
        //             String.format("ERROR at %s: %s", time, msg);
        //         case DataEvent(var user, var action, var data, _) -> 
        //             String.format("%s performed %s with %d fields", user, action, data.size());
        //     };
        //     assertNotNull(summary);
        // }
    }
    
    @Test
    public void filterEventsByType() {
        // TODO: Filter specific event types
        // List<Event> events = createMixedEvents();
        
        // List<String> loggedInUsers = events.stream()
        //     .filter(e -> e instanceof LoginEvent)
        //     .map(e -> ((LoginEvent) e).userId())
        //     .distinct()
        //     .toList();
        
        // Or using pattern matching:
        // List<String> errors = events.stream()
        //     .mapMulti((event, consumer) -> {
        //         if (event instanceof ErrorEvent(var msg, _, _)) {
        //             consumer.accept(msg);
        //         }
        //     })
        //     .toList();
    }
    
    @Test
    public void eventStatistics() {
        // TODO: Calculate statistics from events
        // Map<Class<? extends Event>, Long> counts = events.stream()
        //     .collect(Collectors.groupingBy(
        //         Event::getClass,
        //         Collectors.counting()
        //     ));
        
        // assertEquals(2L, counts.get(LoginEvent.class));
    }
}
```

[Back to Table of Contents](#table-of-contents)

## Capstone Project

### Movie Recommendation System

Create a complete application using modern Java features:

Create: `src/test/java/exercises/MovieSystemTest.java`

```java
package exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class MovieSystemTest {
    
    // TODO: Design the data model using records and sealed classes
    
    // Records for data
    // record Movie(String id, String title, int year, Set<String> genres, double rating) {}
    // record User(String id, String name, Map<String, Integer> ratings) {} // movieId -> rating
    // record Recommendation(Movie movie, double score, String reason) {}
    
    // Sealed hierarchy for recommendation strategies
    // sealed interface RecommendationStrategy permits 
    //     GenreBasedStrategy, RatingBasedStrategy, CollaborativeStrategy {}
    
    @Test
    public void createMovieCatalog() {
        // TODO: Create movie catalog with modern collections
        // var movies = List.of(
        //     new Movie("1", "The Matrix", 1999, Set.of("Sci-Fi", "Action"), 8.7),
        //     new Movie("2", "Inception", 2010, Set.of("Sci-Fi", "Thriller"), 8.8),
        //     // ... more movies
        // );
        
        // Group by genre using collectors
        // Map<String, List<Movie>> byGenre = ...;
        
        // Find top-rated movies using streams
        // List<Movie> topRated = ...;
    }
    
    @Test
    public void recommendationEngine() {
        // TODO: Implement recommendation using pattern matching
        // Recommendation recommend(User user, Movie movie, RecommendationStrategy strategy) {
        //     return switch (strategy) {
        //         case GenreBasedStrategy(var preferredGenres) -> {
        //             // Calculate score based on genre match
        //             yield new Recommendation(...);
        //         }
        //         case RatingBasedStrategy(var minRating) -> {
        //             // Check if movie meets rating threshold
        //             yield new Recommendation(...);
        //         }
        //         case CollaborativeStrategy(var similarUsers) -> {
        //             // Use other users' ratings
        //             yield new Recommendation(...);
        //         }
        //     };
        // }
    }
    
    @Test
    public void asyncMovieFetching() {
        // TODO: Use CompletableFuture for async operations
        // CompletableFuture<List<Movie>> fetchFromAPI() {
        //     return CompletableFuture.supplyAsync(() -> {
        //         // Simulate API call
        //         return movieList;
        //     });
        // }
        
        // Combine multiple sources
        // var future1 = fetchFromAPI();
        // var future2 = fetchFromDatabase();
        // CompletableFuture<List<Movie>> combined = 
        //     future1.thenCombine(future2, (list1, list2) -> {
        //         // Merge and deduplicate
        //     });
    }
    
    @Test
    public void virtualThreadsForScaling() {
        // TODO: Use virtual threads for handling many users
        // try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
        //     List<Future<List<Recommendation>>> futures = users.stream()
        //         .map(user -> executor.submit(() -> 
        //             generateRecommendations(user)))
        //         .toList();
        //     
        //     // Process results
        // }
    }
    
    @Test
    public void advancedFiltering() {
        // TODO: Complex filtering with stream operations
        // Find movies:
        // - Released after 2000
        // - Rating > 7.5
        // - Has at least one genre in user preferences
        // - Not already watched by user
        // - Sort by recommendation score
        
        // List<Movie> filtered = movies.stream()
        //     .filter(...)
        //     .sorted(...)
        //     .limit(10)
        //     .toList();
    }
}
```

[Back to Table of Contents](#table-of-contents)

## Running the Exercises

### Command Line

```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "exercises.LambdaBasicsTest"

# Run specific test method
./gradlew test --tests "exercises.LambdaBasicsTest.sortWithLambda"

# Run with more output
./gradlew test --info
```

### IDE

1. Import the project into your IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Navigate to the test file
3. Click the green arrow next to the test method or class
4. Use keyboard shortcuts:
   - IntelliJ: Ctrl+Shift+F10 (Windows/Linux) or Cmd+Shift+R (Mac)
   - Eclipse: Alt+Shift+X, T
   - VS Code: Use test explorer or CodeLens

### Debugging Tests

1. Set breakpoints in your test code
2. Run tests in debug mode
3. Step through to understand the flow
4. Inspect variables and expressions

## Tips and Best Practices

### General Tips

1. **Start Simple**: Begin with basic exercises before moving to advanced topics
2. **Read Errors**: Compilation errors often give hints about what's needed
3. **Use IDE Features**: 
   - Auto-completion (Ctrl+Space)
   - Quick fixes (Alt+Enter in IntelliJ)
   - Parameter hints
   - JavaDoc popup (Ctrl+Q in IntelliJ)
4. **Consult API Docs**: Keep Java API documentation open
5. **Experiment**: Try different approaches to the same problem

### Feature-Specific Tips

#### Lambdas and Streams
- Start with method references where possible
- Use type inference but add types if unclear
- Chain operations for readability
- Consider performance with parallel streams

#### Records
- Use compact constructors for validation
- Records are implicitly final
- Can implement interfaces
- Can have static members

#### Pattern Matching
- Order matters - specific patterns before general
- Use guards (when clauses) for additional conditions
- Leverage exhaustiveness checking

#### Virtual Threads
- Best for I/O-bound operations
- Don't pool virtual threads
- Use try-with-resources for executors

### Common Pitfalls

1. **Forgetting `toList()`**: Streams are lazy - terminal operation required
2. **Modifying Immutable Collections**: `List.of()` creates immutable lists
3. **Pattern Dominance**: More specific patterns must come first
4. **Resource Management**: Always close resources (use try-with-resources)

### Solution Strategies

1. **Incremental Development**: 
   - Get the test to compile first
   - Make it pass with simple implementation
   - Refactor to use modern features

2. **Use the Repository Code**:
   - Look at similar patterns in the main source
   - `ProcessDictionaryV2.java` has many stream examples
   - Test files show expected usage

3. **Break Down Complex Problems**:
   - Solve part of the problem first
   - Use intermediate variables if needed
   - Refactor once working

## Solutions

Complete solutions for all exercises are available in the `exercises.solutions` package:

```bash
# Run solution tests to see working implementations
./gradlew test --tests "exercises.solutions.*"

# Compare your solutions with the provided ones
# Navigate to src/test/java/exercises/solutions/ in your IDE
```

The solution files mirror the exercise structure:
- `exercises.solutions.LambdaBasicsTest`
- `exercises.solutions.StreamBasicsTest`
- `exercises.solutions.CollectorsTest`
- And so on...

Use these as reference implementations and to verify your understanding of the concepts.

## Additional Resources

- **Repository Examples**: Study the main source code in `src/main/java`
- **Test Files**: Existing tests show patterns and best practices
- **Slides**: Review `slides.md` for conceptual understanding
- **Java Documentation**:
  - [Official Java Docs](https://docs.oracle.com/en/java/)
  - [JEP List](https://openjdk.org/jeps/)
  - [Java Tutorials](https://dev.java/learn/)
