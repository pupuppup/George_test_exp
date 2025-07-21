// File: OfflineStateTest.kt
// Purpose: Automation test for offline mode (banner, button disabled)
// Author: Taras Mylyi
// Checks offline toggle disables call button

package tests

import config.DriverFactory
import pages.CommunicationsScreen
import pages.OfflineBanner
import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    fun `should disable call button and show offline banner when offline`() {
        // Open overflow menu and toggle offline
        driver.findElement(By.xpath("//*[contains(@text, 'Simulate Offline') or contains(@text, 'Simulovať offline')]")).click()
        val offlineBanner = OfflineBanner(driver)
        Assertions.assertTrue(offlineBanner.isVisible())
        val commScreen = CommunicationsScreen(driver)
        Assertions.assertTrue(driver.findElement(commScreen.callButtonSelector).getAttribute("enabled") == "false")
    }
} 