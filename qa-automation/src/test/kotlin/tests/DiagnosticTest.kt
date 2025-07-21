// File: DiagnosticTest.kt
// Purpose: Diagnostic test to check all elements on screen
// Author: Taras Mylyi
// Test ID: DIAG-001
// Priority: ★ Debug
// Tags: debug

package tests

import config.DriverFactory
import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.assertj.core.api.Assertions.assertThat

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DiagnosticTest {
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
    fun `DIAG_001_should_analyze_all_elements_on_screen`() {
        // Wait for app to load
        Thread.sleep(3000)
        
        // Check if we're in the right app
        val currentPackage = driver.currentPackage
        println("Current package: $currentPackage")
        assertThat(currentPackage).isEqualTo("com.example.supportcall")
        
        // Find all elements with text
        val elementsWithText = driver.findElements(By.xpath("//*[@text]"))
        println("Found ${elementsWithText.size} elements with text:")
        
        elementsWithText.forEach { element ->
            try {
                val text = element.text
                val className = element.getAttribute("className")
                val resourceId = element.getAttribute("resourceId")
                println("Text: '$text' | Class: $className | ID: $resourceId")
            } catch (e: Exception) {
                println("Error getting element info: ${e.message}")
            }
        }
        
        // Try to find call button with different locators
        println("\nTrying different locators for call button:")
        
        val locators = listOf(
            "//*[contains(@text, 'Зателефонувати')]",
            "//*[contains(@text, 'Call')]",
            "//*[contains(@text, 'Zavolať')]",
            "//*[contains(@text, 'зателефонувати')]",
            "//*[contains(@text, 'call')]",
            "//*[contains(@text, 'zavola')]",
            "//*[@text]",
            "//android.widget.Button",
            "//android.widget.TextView"
        )
        
        locators.forEach { locator ->
            try {
                val elements = driver.findElements(By.xpath(locator))
                println("Locator '$locator': found ${elements.size} elements")
                elements.take(3).forEach { element ->
                    try {
                        println("  - Text: '${element.text}' | Class: ${element.getAttribute("className")}")
                    } catch (e: Exception) {
                        println("  - Error: ${e.message}")
                    }
                }
            } catch (e: Exception) {
                println("Locator '$locator': error - ${e.message}")
            }
        }
        
        // Check if any elements contain call-related text
        val allElements = driver.findElements(By.xpath("//*"))
        val callRelatedElements = allElements.filter { element ->
            try {
                val text = element.text.lowercase()
                text.contains("телефон") || text.contains("call") || text.contains("zavola")
            } catch (e: Exception) {
                false
            }
        }
        
        println("\nCall-related elements found: ${callRelatedElements.size}")
        callRelatedElements.forEach { element ->
            try {
                println("Call-related: '${element.text}' | Class: ${element.getAttribute("className")}")
            } catch (e: Exception) {
                println("Call-related: Error getting info - ${e.message}")
            }
        }
    }
} 