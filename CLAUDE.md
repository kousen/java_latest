# CLAUDE.md - Java Latest Repository Context

## Repository Overview

This repository contains comprehensive Java examples and demonstrations covering features from Java 8 through Java 25+. It serves as both a workshop resource and code reference for multiple presentation formats. The codebase emphasizes educational clarity while maintaining modern Java best practices and SonarCloud quality standards.

## Recent Major Improvements (2025)

### Code Quality Modernization
The repository underwent comprehensive quality improvements to align with modern Java practices while maintaining its educational purpose:

1. **HttpClient Lifecycle Management**
   - Converted all HttpClient instances to static final fields
   - Fixed async operation lifecycle issues with Java 21+ AutoCloseable implementation
   - Ensures proper resource sharing and prevents premature client closure

2. **Exception Handling Standardization**
   - Implemented proper InterruptedException handling throughout codebase
   - Added `Thread.currentThread().interrupt()` calls in all catch blocks
   - Separated IOException and InterruptedException handling for clarity

3. **Modern Java Pattern Adoption**
   - Migrated from `collect(Collectors.toList())` to `toList()` terminal operations
   - Implemented record patterns in switch expressions where applicable
   - Enhanced pattern matching usage in data-oriented programming examples

4. **SonarCloud Integration**
   - Configured comprehensive code quality analysis with educational code standards
   - Disabled inappropriate rules for demo/educational code (commented code, System.out.println, generic exceptions)
   - Maintained quality gates while preserving educational clarity

## Key Architecture Decisions

### Dual Presentation Strategy
The repository supports **two distinct presentation formats**:

1. **Workshop Presentation** (`slides.md`)
   - Educational/training focus
   - Step-by-step Java evolution coverage
   - Paired with `workshop-exercises.md`
   - Designed for hands-on learning sessions

2. **Java Tier List Presentation** (`slides-java-tier-list.md`)
   - Entertainment/engagement focus  
   - Feature ranking and discussion format
   - Interactive audience participation
   - 90-minute presentation timing
   - Uses `images/` folder for tier list graphics

### Repository Consolidation Decision
**Important**: The tier list presentation was originally developed in a separate repository (`java-tier-list`) but was **migrated here** for these reasons:
- Single source of truth for Java code examples
- Easier distribution (one repo link)
- Code and slides always in sync
- Leverages existing repository popularity
- Avoids complexity of submodules or multiple repos

### Code Organization Philosophy
- **Package-based organization** - Each Java feature has its own package
- **Comprehensive examples** - Both simple demos and real-world usage
- **Test coverage** - 160+ tests demonstrating functionality
- **Presentation support** - Code examples referenced directly in slides
- **Educational clarity** - Code optimized for learning, not just production patterns

## Technical Configuration

### Build Setup
- **Java 25 LTS** with **preview features enabled**
- Gradle configuration includes `--enable-preview` for:
  - Compilation (`JavaCompile` tasks)
  - Testing (`Test` tasks)
  - Execution (`JavaExec` tasks)
- Required for features like Structured Concurrency (still in 5th preview in Java 25)

### Quality Assurance
- **SonarCloud** integration with educational code standards
- **JaCoCo** test coverage reporting
- **JUnit 5** comprehensive test suite (160+ tests)
- **GitHub Actions** CI/CD pipeline with quality gates

### Key Dependencies
- JUnit 5 for testing
- Jackson/Gson for JSON processing
- Mockito and WireMock for testing
- AssertJ for fluent assertions

## HttpClient Implementation Pattern

All HttpClient instances follow this pattern for thread safety and proper lifecycle management:

```java
private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
```

**Rationale**: 
- Java 21+ implements AutoCloseable on HttpClient
- Static final ensures single instance per class
- Prevents premature closure of async operations
- Thread-safe for concurrent usage
- Automatically garbage collected with class

## Exception Handling Standards

InterruptedException handling follows this pattern throughout the codebase:

```java
try {
    // operation that may throw InterruptedException
} catch (IOException e) {
    return new Failure<>(new RuntimeException(e));
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    return new Failure<>(new RuntimeException(e));
}
```

**Rationale**:
- Separate catch blocks for clarity (preferred by SonarCloud)
- Proper thread interrupt status restoration
- Consistent error handling across all network operations

## Presentation-Specific Details

### Tier List Presentation Evolution
Originally created as separate project, went through major refactoring:
1. **Split oversized slides** - Many slides were too long for 90-minute format
2. **Added repository references** - Slides now reference actual code files instead of inline examples
3. **Organized images** - All tier list graphics moved to `images/` subdirectory
4. **Focused content** - Each slide covers one specific concept/feature

### Workshop Presentation  
- Traditional educational format
- Covers Java evolution chronologically
- Includes hands-on exercises
- More comprehensive code examples in slides

## Directory Structure

### Core Code (`src/main/java/com/kousenit/`)
- `collections/` - Factory methods, immutable collections
- `enhancedswitch/` - Switch expressions and patterns  
- `http/` - HTTP Client API examples with proper lifecycle management
- `optional/` - Optional usage patterns
- `patternmatching/` - instanceof patterns, switch patterns, record patterns
- `records/` - Record classes and usage
- `sealed/` - Sealed classes and pattern matching
- `streams/` - Stream API and gatherers with modern terminal operations
- `textblocks/` - Text block examples  
- `virtualthreads/` - Virtual thread demonstrations
- `dataorientedprogramming/` - Records, sealed classes, and pattern matching integration
- And many more feature-specific packages

### Test Structure (`src/test/java/`)
- Mirrors main package structure
- `exercises/solutions/` - Workshop exercise solutions
- Comprehensive test coverage for all features
- Educational test patterns with minimal assertions for demo purposes

### Presentation Assets
- `slides.md` - Workshop presentation
- `slides-java-tier-list.md` - Tier list presentation  
- `images/` - Tier list graphics and watermarks
- `workshop-exercises.md` - Hands-on exercise instructions

### Configuration Files
- `build.gradle.kts` - Gradle build with SonarCloud integration
- `sonar-project.properties` - SonarCloud project configuration
- `.github/workflows/sonar.yml` - CI/CD pipeline for quality analysis

## Development Workflow

### Adding New Features
1. Create package under `com.kousenit.{feature}`
2. Add demonstration classes following established patterns
3. Create comprehensive tests with educational focus
4. Update relevant presentation(s)
5. Ensure preview features are properly configured if needed
6. Verify SonarCloud quality gates pass

### Code Quality Standards
- Follow HttpClient static final pattern for network clients
- Implement proper InterruptedException handling
- Use modern Java features (records, switch expressions, pattern matching)
- Prefer `toList()` over `collect(Collectors.toList())`
- Maintain educational clarity over production complexity

### Presentation Maintenance
- **Workshop slides** - Focus on educational progression
- **Tier list slides** - Keep concise, reference code files
- **Code examples** - Ensure they work with current Java version
- **Repository references** - Verify all slide references point to existing files

## Important Notes

### Preview Features
- Build is configured for Java 25 LTS with preview features
- Currently used for Structured Concurrency (5th preview) and other preview features
- Must maintain `--enable-preview` flags in Gradle build
- Java 25 finalized: Scoped Values, Module Import Declarations, Flexible Constructor Bodies, and more

### Educational vs Production Code
The repository balances educational clarity with modern best practices:
- **System.out.println** allowed for demonstration purposes
- **Generic exceptions** permitted for simplicity
- **Commented code** preserved for explanatory value
- **String literal duplication** acceptable for clarity
- **Test assertions** minimal to focus on feature demonstration

### SonarCloud Configuration
Disabled rules for educational context:
- S125: Commented code sections
- S106: System.out usage for logging
- S112: Generic exception usage
- S2699: Test assertion requirements
- S1192: String literal duplication

### Presentation Philosophy  
- **Slides should be concise** - Complex examples belong in repository
- **Code should be runnable** - All examples must compile and run
- **Tests are documentation** - Test files often better than slide examples
- **Single source of truth** - Repository is authoritative for all code

### Future Considerations
- Monitor Java preview features for stability
- Consider splitting presentations if content grows too large  
- Maintain backward compatibility for workshop exercises
- Keep tier list graphics updated with new feature additions
- Update SonarCloud rules as educational needs evolve

This repository successfully serves multiple audiences: workshop attendees learning Java systematically, conference audiences engaging with feature rankings, and developers looking for practical Java examples. The recent quality improvements ensure the code demonstrates modern Java best practices while maintaining its educational mission.