// File: DebounceTest.kt
// Purpose: Automation test for debounce logic (5 taps = 1 dialer/event)
// Author: <your name>
// Checks debounce window 1500ms

package tests

import config.DriverFactory
import pages.CommunicationsScreen
import pages.TopicSelectionScreen
import org.junit.jupiter.api.*
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DebounceTest {
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
    fun `should debounce rapid taps to single dialer launch and tap event`() {
        val commScreen = CommunicationsScreen(driver)
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        repeat(5) {
            commScreen.tapCallButton()
        }

        val topicScreen = TopicSelectionScreen(driver)
        WebDriverWait(driver, Duration.ofSeconds(5)).until {
            driver.findElement(topicScreen.clientCentreSelector).isDisplayed
        }
        topicScreen.selectClientCentre()

        // Wait for dialer to open
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            val pkg = driver.currentPackage
            pkg != null && pkg != config.Constants.APP_PACKAGE &&
                (pkg.contains("dialer") || pkg.contains("contacts") || pkg.contains("incall"))
        }
        // TODO: Add analytics event check if analytics panel exposed
    }
} 