// File: qa-automation/build.gradle.kts
// Purpose: QA automation module build config
// Author: Taras Mylyi
// Appium, JUnit5, Jackson, AssertJ, system properties mapping

plugins {
    kotlin("jvm")
}

dependencies {
    implementation("io.appium:java-client:9.0.0")
    implementation("org.junit.jupiter:junit-jupiter:5.10.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    implementation("org.assertj:assertj-core:3.25.3")
    implementation("io.github.artsok:rerunner-jupiter:2.1.6")
    // implementation("io.qameta.allure:allure-junit5:2.24.0") // Uncomment for Allure
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
    systemProperty("appium.url", System.getenv("APPIUM_URL") ?: "http://127.0.0.1:4723")
    systemProperty("app.package", System.getenv("APP_PACKAGE") ?: "com.example.supportcall")
    systemProperty("app.activity", System.getenv("APP_ACTIVITY") ?: ".MainActivity")
} 