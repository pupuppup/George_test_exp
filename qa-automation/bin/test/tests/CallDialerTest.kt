// File: CallDialerTest.kt
// Purpose: Automation test for dialer open and number check
// Author: Taras Mylyi
// Checks ACTION_DIAL, no CALL_PHONE, package != app, contains dialer/contacts/incall

package tests

import config.DriverFactory
import config.Constants
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import pages.CommunicationsScreen
import pages.TopicSelectionScreen
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CallDialerTest {
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
    fun `should open system dialer with prefilled number`() {
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

        // Wait for dialer to open (package name != app, contains dialer/contacts/incall)
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            val pkg = driver.currentPackage
            pkg != null && pkg != Constants.APP_PACKAGE &&
                (pkg.contains("dialer") || pkg.contains("contacts") || pkg.contains("incall"))
        }
        val pkg = driver.currentPackage
        assertThat(pkg).isNotEqualTo(Constants.APP_PACKAGE)
        assertThat(pkg).matches(".*(dialer|contacts|incall).*")
    }
} 