# CLAUDE.md - Java Latest Repository Context

## Repository Overview

This repository contains comprehensive Java examples and demonstrations covering features from Java 8 through Java 25+. It serves as both a workshop resource and code reference for multiple presentation formats.

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

## Technical Configuration

### Build Setup
- **Java 24** with **preview features enabled**
- Gradle configuration includes `--enable-preview` for:
  - Compilation (`JavaCompile` tasks)
  - Testing (`Test` tasks) 
  - Execution (`JavaExec` tasks)
- Required for features like Structured Concurrency (still in preview)

### Key Dependencies
- JUnit 5 for testing
- Jackson/Gson for JSON processing
- Mockito and WireMock for testing
- AssertJ for fluent assertions

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
- `http/` - HTTP Client API examples
- `optional/` - Optional usage patterns
- `patternmatching/` - instanceof patterns, switch patterns, record patterns
- `records/` - Record classes and usage
- `sealed/` - Sealed classes and pattern matching
- `streams/` - Stream API and gatherers
- `textblocks/` - Text block examples  
- `virtualthreads/` - Virtual thread demonstrations
- And many more feature-specific packages

### Test Structure (`src/test/java/`)
- Mirrors main package structure
- `exercises/solutions/` - Workshop exercise solutions
- Comprehensive test coverage for all features

### Presentation Assets
- `slides.md` - Workshop presentation
- `slides-java-tier-list.md` - Tier list presentation  
- `images/` - Tier list graphics and watermarks
- `workshop-exercises.md` - Hands-on exercise instructions

## Development Workflow

### Adding New Features
1. Create package under `com.kousenit.{feature}`
2. Add demonstration classes
3. Create comprehensive tests
4. Update relevant presentation(s)
5. Ensure preview features are properly configured if needed

### Presentation Maintenance
- **Workshop slides** - Focus on educational progression
- **Tier list slides** - Keep concise, reference code files
- **Code examples** - Ensure they work with current Java version
- **Repository references** - Verify all slide references point to existing files

## Important Notes

### Preview Features
- Build is configured for Java 24 with preview features
- Currently used for Structured Concurrency examples
- Must maintain `--enable-preview` flags in Gradle build

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

This repository successfully serves multiple audiences: workshop attendees learning Java systematically, conference audiences engaging with feature rankings, and developers looking for practical Java examples.