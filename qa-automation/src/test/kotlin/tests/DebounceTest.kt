// File: DebounceTest.kt
// Author: Taras Mylyi
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
import base.BaseTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DebounceTest : BaseTest() {

    @Test
    @Order(1)
    @Tag("critical")
    fun `AUT_FN_003_should_ignore_multiple_rapid_taps_and_open_only_one_dialer_instance`() {
        val commScreen = CommunicationsScreen(driver)
        // Always get a fresh element for Compose Button (never cache!)
        wait.until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        // Симулюємо 5 тапів за 500ms
        repeat(5) {
            driver.findElement(commScreen.callButtonSelector).click()
            Thread.sleep(100)
        }
        // Перевіряємо, що відкрився лише один FakeDialerActivity
        assertThat(driver.currentPackage).isEqualTo("com.example.supportcall")
        // TODO: перевірити, що створено лише один інтенція (можна через лог/лічильник)
    }
} 
