// File: LocalisationTest.kt
// Author: Taras Mylyi
// Test ID: AUT-LOC-UA, AUT-LOC-EN
// Purpose: Nightly tests - localization verification for EN and SK
// Priority: ★ Nightly
// Tags: nightly, locale

package tests

import config.DriverFactory
import pages.CommunicationsScreen
import org.junit.jupiter.api.*
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import org.assertj.core.api.Assertions.assertThat
import base.BaseTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class LocalisationTest : BaseTest() {

    @Test
    @Order(1)
    @Tag("nightly")
    @Tag("locale")
    fun `AUT_LOC_EN_should_display_English_call_button_and_dialer_opens`() {
        val commScreen = CommunicationsScreen(driver)
        // Always get a fresh element for Compose Button (never cache!)
        wait.until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        val button = driver.findElement(commScreen.callButtonSelector)
        assertThat(button.isDisplayed).isTrue()
        assertThat(button.isEnabled).isTrue()
        // Click using fresh element
        driver.findElement(commScreen.callButtonSelector).click()
        assertThat(driver.currentPackage).isEqualTo("com.example.supportcall")
    }

    @Test
    @Order(2)
    @Tag("nightly")
    @Tag("locale")
    fun `AUT_LOC_SK_should_display_Slovak_call_button_and_dialer_opens`() {
        val commScreen = CommunicationsScreen(driver)
        wait.until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        val button = driver.findElement(commScreen.callButtonSelector)
        assertThat(button.isDisplayed).isTrue()
        assertThat(button.isEnabled).isTrue()
        assertThat(button.text).contains("Zavolať")
        driver.findElement(commScreen.callButtonSelector).click()
        assertThat(driver.currentPackage).isEqualTo("com.example.supportcall")
    }
} 
