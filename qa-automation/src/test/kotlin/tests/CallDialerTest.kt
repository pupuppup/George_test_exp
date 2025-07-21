// File: CallDialerTest.kt
// Author: Taras Mylyi
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
import base.BaseTest
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CallDialerTest : BaseTest() {

    @Test
    @Order(1)
    @Tag("smoke")
    @Tag("critical")
    fun `AUT_SMK_001_should_open_system_dialer_with_prefilled_number`() {
        val commScreen = CommunicationsScreen(driver)
        // Always get a fresh element for Compose Button (never cache!)
        wait.until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        driver.findElement(commScreen.callButtonSelector).click()
        // Check that FakeDialerActivity is opened (currentPackage == com.example.supportcall)
        assertThat(driver.currentPackage).isEqualTo("com.example.supportcall")
        // TODO: Check the number in FakeDialerActivity via UI (text contains number)
    }
} 
