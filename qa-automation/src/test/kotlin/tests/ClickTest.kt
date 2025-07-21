// File: ClickTest.kt
// Purpose: Simple test to verify button clickability
// Test ID: CLICK-001
// Priority: ★ Debug
// Tags: debug

package tests

import config.DriverFactory
import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.assertj.core.api.Assertions.assertThat

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ClickTest {
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
    fun `CLICK_001_should_find_and_click_call_button`() {
        // Wait for app to load
        Thread.sleep(5000)
        
        // Check if we're in the right app
        val currentPackage = driver.currentPackage
        println("Current package: $currentPackage")
        assertThat(currentPackage).isEqualTo("com.example.supportcall")
        
        // Try different approaches to find clickable button
        println("Trying different approaches to find clickable button:")
        
        // 1. Try to find button element
        try {
            val button = driver.findElement(By.xpath("//android.widget.Button"))
            println("Found button: '${button.text}' | Class: ${button.getAttribute("className")}")
            println("Button clickable: ${button.getAttribute("clickable")}, enabled: ${button.getAttribute("enabled")}")
            
            if (button.getAttribute("clickable") == "true") {
                button.click()
                println("Button clicked successfully!")
            } else {
                println("Button is not clickable")
            }
        } catch (e: Exception) {
            println("No button found: ${e.message}")
        }
        
        // 2. Try to find parent of text element
        try {
            val textElement = driver.findElement(By.xpath("//*[contains(@text, 'Zavola┼е')]"))
            println("Found text element: '${textElement.text}' | Class: ${textElement.getAttribute("className")}")
            
            // Try to click the text element itself
            textElement.click()
            println("Text element clicked!")
        } catch (e: Exception) {
            println("Text element not clickable: ${e.message}")
        }
        
        // 3. Try to find any clickable element
        try {
            val clickableElements = driver.findElements(By.xpath("//*[@clickable='true']"))
            println("Found ${clickableElements.size} clickable elements:")
            clickableElements.take(5).forEach { element ->
                println("  - Text: '${element.text}' | Class: ${element.getAttribute("className")}")
            }
        } catch (e: Exception) {
            println("Error finding clickable elements: ${e.message}")
        }
        
        // Wait a bit to see if anything happens
        Thread.sleep(2000)
        
        // Check if we're still in the same app
        val newPackage = driver.currentPackage
        println("Package after attempts: $newPackage")
    }
} 