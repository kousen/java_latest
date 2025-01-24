plugins {
    java
    application
    eclipse
    alias(libs.plugins.versions)
    alias(libs.plugins.version.catalog.update)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
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
    testImplementation(libs.wiremock)
    testImplementation("com.jayway.jsonpath:json-path:2.9.0")

    // Make vulnerability issues go away
    testImplementation("commons-io:commons-io:2.17.0")
//    testImplementation("org.eclipse.jetty:jetty-http:12.0.15")
//    testImplementation("org.eclipse.jetty:jetty-server:12.0.15")
}

//tasks.withType<JavaCompile>().configureEach {
//    options.compilerArgs.add("--enable-preview")
//}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    // jvmArgs("--enable-preview", "-XX:+EnableDynamicAgentLoading", "-Xshare:off")
    jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off")
}

tasks.withType<JavaExec>().configureEach {
    // jvmArgs("--enable-preview", "-XX:+EnableDynamicAgentLoading", "-Xshare:off")
    jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off")
}
