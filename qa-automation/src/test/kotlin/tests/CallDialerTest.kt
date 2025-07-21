// File: CallDialerTest.kt
// Test ID: AUT-SMK-001
// Purpose: Smoke test - dialer opens with correct package and number
// Priority: ⭐ Critical
// Tags: smoke, critical

package tests

import config.DriverFactory
import config.Constants
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import pages.CommunicationsScreen
import pages.TopicSelectionScreen
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import io.appium.java_client.AppiumBy

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
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
    @Order(1)
    @Tag("smoke")
    @Tag("critical")
    fun `AUT_SMK_001_should_open_system_dialer_with_prefilled_number`() {
        val commScreen = CommunicationsScreen(driver)
        
        // Wait for call button to be visible
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        
        // Tap call button
        commScreen.tapCallButton()

        val topicScreen = TopicSelectionScreen(driver)
        WebDriverWait(driver, Duration.ofSeconds(5)).until {
            driver.findElement(topicScreen.clientCentreSelector).isDisplayed
        }
        topicScreen.selectClientCentre()

        // Wait for dialer to open and verify package
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            val pkg = driver.currentPackage
            pkg != null && pkg != Constants.APP_PACKAGE &&
                (pkg.contains("dialer") || pkg.contains("contacts") || pkg.contains("incall"))
        }
        
        val pkg = driver.currentPackage
        assertThat(pkg).isNotEqualTo(Constants.APP_PACKAGE)
        assertThat(pkg).matches(".*(dialer|contacts|incall).*")
        
        // Verify number is pre-filled (if possible to check)
        // TODO: Add number verification if dialer exposes number field
    }
} 
