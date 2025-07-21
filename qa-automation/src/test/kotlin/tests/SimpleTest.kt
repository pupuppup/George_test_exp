// File: SimpleTest.kt
// Purpose: Simple test to verify basic Appium functionality
// Author: Taras Mylyi
// Test ID: SIMPLE-001
// Priority: ★ Debug
// Tags: debug

package tests

import config.DriverFactory
import org.junit.jupiter.api.*
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import org.assertj.core.api.Assertions.assertThat

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class SimpleTest {
    private lateinit var driver: io.appium.java_client.android.AndroidDriver

    @BeforeAll
    fun setup() {
        driver = DriverFactory.create()
    }

    @AfterAll
    fun teardown() {
        driver.quit()
    }

    @Test
    @Order(1)
    @Tag("debug")
    fun `SIMPLE_001_should_connect_to_device_and_find_any_element`() {
        // Wait for app to load
        Thread.sleep(3000)
        
        // Check if we're in the right app
        val currentPackage = driver.currentPackage
        println("Current package: $currentPackage")
        assertThat(currentPackage).isEqualTo("com.example.supportcall")
        
        // Try to find any element on screen
        val allElements = driver.findElements(org.openqa.selenium.By.xpath("//*"))
        println("Found ${allElements.size} elements on screen")
        assertThat(allElements.size).isGreaterThan(0)
        
        // Print first few elements for debugging
        allElements.take(5).forEach { element ->
            try {
                val text = element.text
                val className = element.getAttribute("className")
                println("Element: $className - Text: '$text'")
            } catch (e: Exception) {
                println("Element: ${element.tagName} - Error getting text: ${e.message}")
            }
        }
    }
} 