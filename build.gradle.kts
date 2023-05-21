import org.gradle.plugins.ide.eclipse.model.EclipseModel

plugins {
    java
    application
    eclipse
    alias(libs.plugins.versions)
    alias(libs.plugins.version.catalog.update)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

group = "com.kousenit"
version = "1.0"

application {
    mainClass.set("com.kousenit.fileio.ProcessDictionary")
    applicationDefaultJvmArgs = listOf("--enable-preview")
}

repositories {
    mavenCentral()
}

dependencies {
    // JSON parsers
    implementation(libs.gson)
    implementation(libs.bundles.moshi)
    implementation(libs.bundles.jackson)

    // Logging
    implementation(libs.slf4j.nop)

    // Testing
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.assertj)
    testImplementation(libs.bundles.mockito)
    testImplementation(libs.wiremock)
}

tasks.withType(JavaCompile::class).configureEach {
    options.compilerArgs.plusAssign("--enable-preview")
}

tasks.test {
    jvmArgs = listOf("--enable-preview")
    useJUnitPlatform()
    maxParallelForks = Runtime.getRuntime().availableProcessors() / 2 + 1
}

// See: https://github.com/redhat-developer/vscode-java/wiki/Enabling-Java-preview-features
//Buildship doesn't use that hook (https://discuss.gradle.org/t/when-does-buildship-eclipse-customization-run/20781/2)
//you need to run `gradlew eclipse` separately
//eclipse.jdt.file.withProperties { props: Properties ->
//    props["org.eclipse.jdt.core.compiler.problem.enablePreviewFeatures"] = "enabled"
//    props["org.eclipse.jdt.core.compiler.problem.reportPreviewFeatures"] = "ignore"
//}

configure<EclipseModel> {
    jdt {
        file {
            withProperties {
                setProperty("org.eclipse.jdt.core.compiler.problem.enablePreviewFeatures", "enabled")
                setProperty("org.eclipse.jdt.core.compiler.problem.reportPreviewFeatures", "ignore")
            }
        }
    }
}