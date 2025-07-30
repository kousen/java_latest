plugins {
    java
    application
    eclipse
    jacoco
    alias(libs.plugins.versions)
    alias(libs.plugins.version.catalog.update)
    id("org.sonarqube") version "6.0.1.5171"
}

jacoco {
    toolVersion = "0.8.13"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

group = "com.kousenit"
version = "1.0"

application {
    mainClass.set("com.kousenit.fileio.ProcessDictionary")
}

repositories {
    mavenCentral()
}

dependencies {
    // JSON parsers
    implementation(libs.gson)
    implementation(libs.bundles.jackson)

    // Logging
    implementation(libs.slf4j.nop)

    // Testing
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(libs.assertj)
    testImplementation(libs.bundles.mockito)
    testImplementation(libs.wiremock.jetty12)
    testImplementation("com.jayway.jsonpath:json-path:2.9.0")

    // Make vulnerability issues go away
    testImplementation("commons-io:commons-io:2.17.0")
    // Jetty 12 is now included via wiremock-jetty12
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off", "--enable-preview")
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    // Remove dependency on test so we can run report even with test failures
    // dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off", "--enable-preview")
}

sonar {
    properties {
        property("sonar.projectKey", "kousen_java_latest")
        property("sonar.organization", "kousen-it-inc")
        property("sonar.host.url", "https://sonarcloud.io")
        
        // Disable rules that don't apply to educational/demo code
        property("sonar.issue.ignore.multicriteria", "e1,e2")
        
        // S125: Sections of code should not be commented out
        property("sonar.issue.ignore.multicriteria.e1.ruleKey", "java:S125")
        property("sonar.issue.ignore.multicriteria.e1.resourceKey", "**/*.java")
        
        // S106: Standard outputs should not be used directly to log anything
        property("sonar.issue.ignore.multicriteria.e2.ruleKey", "java:S106")
        property("sonar.issue.ignore.multicriteria.e2.resourceKey", "**/*.java")
        
        // Coverage settings
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
        
        // Source directories
        property("sonar.sources", "src/main/java")
        property("sonar.tests", "src/test/java")
        property("sonar.java.binaries", "build/classes")
    }
}
