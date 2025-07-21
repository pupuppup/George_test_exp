// File: OfflineStateTest.kt
// Author: Taras Mylyi
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
import base.BaseTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class OfflineStateTest : BaseTest() {

    @Test
    fun `AUT_OFF_001_should_disable_call_button_and_show_offline_banner_when_offline`() {
        // TODO: Disable Wi-Fi, wait for idle via IdlingResource/waitForIdleSync
        // Check that the button is disabled and the banner has accessibilityHeading
        driver.findElement(By.xpath("//*[contains(@text, 'Simulate Offline') or contains(@text, 'Simulovať offline')]")).click()
    }
} 
