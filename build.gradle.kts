plugins {
    java
    application
    eclipse
    jacoco
    alias(libs.plugins.versions)
    alias(libs.plugins.version.catalog.update)
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
