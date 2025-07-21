// File: OfflineStateTest.kt
// Test ID: AUT-OFF-001
// Purpose: Nightly test - offline mode disables call functionality
// Priority: ★ Nightly
// Tags: nightly

package tests

import config.DriverFactory
import pages.CommunicationsScreen
import pages.OfflineBanner
import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import org.assertj.core.api.Assertions.assertThat

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class OfflineStateTest {
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
    @Tag("nightly")
    fun `AUT_OFF_001_should_disable_call_button_and_show_offline_banner_when_offline`() {
        // Open overflow menu and toggle offline mode
        val offlineToggleSelector = By.xpath(
            "//*[contains(@text, 'Simulювати офлайн') or " +
            "contains(@text, 'Simulate Offline') or " +
            "contains(@text, 'Simulovať offline')]"
        )
        
        WebDriverWait(driver, java.time.Duration.ofSeconds(10)).until {
            driver.findElement(offlineToggleSelector).isDisplayed
        }
        driver.findElement(offlineToggleSelector).click()
        
        // Verify offline banner is visible
        val offlineBanner = OfflineBanner(driver)
        assertThat(offlineBanner.isVisible()).isTrue()
        
        // Verify call button is disabled or absent
        val commScreen = CommunicationsScreen(driver)
        val callButton = driver.findElement(commScreen.callButtonSelector)
        
        // Check if button is disabled
        val isEnabled = callButton.getAttribute("enabled") == "true"
        val isClickable = callButton.getAttribute("clickable") == "true"
        
        // Button should be either disabled or not clickable in offline mode
        assertThat(isEnabled && isClickable).isFalse()
        
        // Verify app is in offline state
        // TODO: Add additional offline state verification if needed
    }
} 
