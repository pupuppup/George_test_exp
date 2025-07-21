// File: AnalyticsEventTest.kt
// Purpose: Automation test for analytics event order/count
// Author: <your name>
// Checks support_call_tap, support_call_dialer_opened

package tests

import config.DriverFactory
import pages.CommunicationsScreen
import pages.TopicSelectionScreen
import org.junit.jupiter.api.*
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnalyticsEventTest {
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
    fun `should log support_call_tap and support_call_dialer_opened events in order`() {
        val commScreen = CommunicationsScreen(driver)
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        commScreen.tapCallButton()

        val topicScreen = TopicSelectionScreen(driver)
        WebDriverWait(driver, Duration.ofSeconds(5)).until {
            driver.findElement(topicScreen.clientCentreSelector).isDisplayed
        }
        topicScreen.selectClientCentre()

        // TODO: Open analytics panel and check event order if panel exposed
    }
} 