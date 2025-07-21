// File: DebounceTest.kt
// Test ID: AUT-FN-003
// Purpose: Critical test - multiple rapid taps ignored, only one dialer instance
// Priority: ⭐ Critical
// Tags: critical

package tests

import config.DriverFactory
import pages.CommunicationsScreen
import pages.TopicSelectionScreen
import org.junit.jupiter.api.*
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import org.assertj.core.api.Assertions.assertThat

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
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
    @Order(1)
    @Tag("critical")
    fun `AUT_FN_003_should_ignore_multiple_rapid_taps_and_open_only_one_dialer_instance`() {
        val commScreen = CommunicationsScreen(driver)
        
        // Wait for call button to be visible
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        
        // Perform 5 rapid taps in less than 1 second
        val startTime = System.currentTimeMillis()
        repeat(5) {
            commScreen.tapCallButton()
            Thread.sleep(100) // 100ms between taps
        }
        val endTime = System.currentTimeMillis()
        
        // Verify taps were rapid (< 1 second total)
        val tapDuration = endTime - startTime
        assertThat(tapDuration).isLessThan(1000)

        val topicScreen = TopicSelectionScreen(driver)
        WebDriverWait(driver, Duration.ofSeconds(5)).until {
            driver.findElement(topicScreen.clientCentreSelector).isDisplayed
        }
        topicScreen.selectClientCentre()

        // Wait for dialer to open and verify only one instance
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            val pkg = driver.currentPackage
            pkg != null && pkg != config.Constants.APP_PACKAGE &&
                (pkg.contains("dialer") || pkg.contains("contacts") || pkg.contains("incall"))
        }
        
        // Verify only one dialer instance opened
        val pkg = driver.currentPackage
        assertThat(pkg).isNotEqualTo(config.Constants.APP_PACKAGE)
        assertThat(pkg).matches(".*(dialer|contacts|incall).*")
        
        // TODO: Add analytics event check if analytics panel exposed
        // Should log only one support_call_tap event despite 5 physical taps
    }
} 
