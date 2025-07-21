// File: NoPermissionDialogTest.kt
// Author: Taras Mylyi
// Test ID: AUT-FN-002
// Purpose: Critical test - no CALL_PHONE runtime permission dialog appears
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
import org.openqa.selenium.By
import base.BaseTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class NoPermissionDialogTest : BaseTest() {

    @Test
    @Order(1)
    @Tag("critical")
    fun `AUT_FN_002_should_not_show_CALL_PHONE_runtime_permission_dialog`() {
        val commScreen = CommunicationsScreen(driver)
        
        // Always get a fresh element for Compose Button (never cache!)
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        driver.findElement(commScreen.callButtonSelector).click()

        val topicScreen = TopicSelectionScreen(driver)
        WebDriverWait(driver, Duration.ofSeconds(5)).until {
            driver.findElement(topicScreen.clientCentreSelector).isDisplayed
        }
        topicScreen.selectClientCentre()

        // Wait for dialer to open
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            val pkg = driver.currentPackage
            pkg != null && pkg != config.Constants.APP_PACKAGE &&
                (pkg.contains("dialer") || pkg.contains("contacts") || pkg.contains("incall"))
        }
        
        // Verify no permission dialog appeared during the process
        // Check for common permission dialog texts
        val permissionDialogSelectors = listOf(
            "//*[contains(@text, 'Allow')]",
            "//*[contains(@text, 'Deny')]",
            "//*[contains(@text, 'Call') and contains(@text, 'permission')]",
            "//*[contains(@text, 'Phone') and contains(@text, 'permission')]"
        )
        
        for (selector in permissionDialogSelectors) {
            val elements = driver.findElements(By.xpath(selector))
            assertThat(elements).isEmpty()
        }
        
        // Verify dialer opened successfully without permission issues
        val pkg = driver.currentPackage
        assertThat(pkg).isNotEqualTo(config.Constants.APP_PACKAGE)
        assertThat(pkg).matches(".*(dialer|contacts|incall).*")
    }
} 
