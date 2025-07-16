# Modern Java: From 8 to 25 and Beyond

Comprehensive examples and demonstrations of Java features from Java 8 through Java 25, showcasing the evolution of the Java language and platform.

## 🚀 What's Included

- **Practical Code Examples** - Real-world demonstrations of modern Java features
- **Comprehensive Test Suite** - 160+ tests covering all major functionality  
- **Workshop Presentation** - Complete slide deck covering Java evolution (`slides.md`)
- **Java Tier List Presentation** - Feature ranking and discussion format (`slides-java-tier-list.md`)
- **Workshop Exercises** - Hands-on labs with complete solutions (`workshop-exercises.md`)
- **Data-Oriented Programming** - Examples of records, sealed classes, and pattern matching working together

## 🎯 Key Features Covered

### Java 8 - The Functional Revolution
- Lambda expressions and method references
- Stream API with advanced collectors
- Optional for null safety
- Default and static methods in interfaces
- CompletableFuture for async programming

### Java 9-11 - Platform Maturity
- JShell (Java REPL)
- Collection factory methods (`List.of()`, `Map.of()`)
- Local variable type inference (`var`)
- HTTP Client API
- String enhancements (`isBlank()`, `strip()`, `repeat()`)

### Java 12-17 - Language Evolution
- Text blocks for multi-line strings
- Records for immutable data classes
- Pattern matching for `instanceof`
- Switch expressions with `yield`
- Sealed classes for controlled inheritance

### Java 18-21 - Modern Features
- Simple web server (`jwebserver`)
- Virtual threads for scalable concurrency
- Sequenced collections
- Pattern matching in switch
- Enhanced pattern matching with guards

### Java 22-25 - Cutting Edge
- Unnamed variables with `_`
- Primitive types in patterns (preview)
- Scoped Values (finalized in Java 25)
- Foreign Function & Memory API

## 🛠️ Build Requirements

- **Java 24** - Latest features and compatibility
- **Gradle 8.14.3** - Modern build tooling with Java 24 support
- **JUnit 5.13.3** - Modern testing framework

## 🏃‍♂️ Quick Start

```bash
# Clone the repository
git clone https://github.com/kousen/java_latest.git
cd java_latest

# Run all tests
./gradlew test

# Run a specific example
./gradlew run

# Generate code coverage report
./gradlew jacocoTestReport
```

## 📊 Running the Presentation

The repository includes a comprehensive Slidev presentation:

```bash
# Install dependencies for PDF export
npm install

# Start the presentation
npx slidev slides.md

# Export to PDF
npx slidev export slides.md --format pdf
```

## 🎓 Learning Path

### For Self-Study
1. **Start with Java 8** - `ProcessDictionaryV2.java` showcases functional programming
2. **Explore Records** - See `Person.java`, `Customer.java` for data classes
3. **Pattern Matching** - Check `UseShapes.java` for modern control flow
4. **Virtual Threads** - `VirtualThreadsDemo.java` for scalable concurrency
5. **Data-Oriented Programming** - Combine records, sealed classes, and pattern matching

### For Workshops
1. **Review the slides** - `slides.md` provides comprehensive coverage
2. **Work through exercises** - `workshop-exercises.md` contains hands-on labs
3. **Check solutions** - Complete implementations in `exercises.solutions` package
4. **Practice with examples** - Explore the main codebase for real-world patterns

## 📚 Key Examples

- **`ProcessDictionaryV2`** - Comprehensive Stream API showcase
- **`astro` package** - HTTP Client with Result pattern (sealed interfaces)
- **`shapes` package** - Sealed classes with pattern matching
- **`VirtualThreadsDemo`** - Modern concurrency patterns

## 🧪 Testing

```bash
# Run all tests
./gradlew test

# Run tests for a specific package
./gradlew test --tests "*interfaces*"

# Run workshop solution tests
./gradlew test --tests "exercises.solutions.*"

# Run with coverage
./gradlew test jacocoTestReport
```

## 📈 Code Coverage

JaCoCo reports are generated in `build/reports/jacoco/test/html/index.html`

## 📖 Additional Resources

- [Modern Java Recipes](https://kousenit.com) by Kenneth Kousen
- [Java Enhancement Proposals (JEPs)](https://openjdk.org/projects/jdk/)
- [Java Almanac](https://javaalmanac.io)

## 👨‍💻 Author

**Kenneth Kousen**
- Website: [kousenit.com](https://kousenit.com)
- Newsletter: [Tales from the jar side](https://kenkousen.substack.com)
- Social: [@kenkousen](https://twitter.com/kenkousen)

---

*This repository is actively maintained and updated with the latest Java features as they become available.*
