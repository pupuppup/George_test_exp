// File: LocalisationTest.kt
// Purpose: Automation test for localisation (button text)
// Author: Taras Mylyi
// Checks UA/EN/SK text present

package tests

import config.DriverFactory
import pages.CommunicationsScreen
import org.junit.jupiter.api.*
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LocalisationTest {
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
    fun `should display call button text`() {
        val commScreen = CommunicationsScreen(driver)
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        val buttonText = driver.findElement(commScreen.callButtonSelector).text
        Assertions.assertTrue(
            buttonText.contains("Call") ||
            buttonText.contains("Zavolať")
        )
    }

    @Test
    fun `should display Slovak call button text`() {
        val commScreen = CommunicationsScreen(driver)
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        val buttonText = driver.findElement(commScreen.callButtonSelector).text
        Assertions.assertTrue(
            buttonText.contains("Zavolať")
        )
    }
} 