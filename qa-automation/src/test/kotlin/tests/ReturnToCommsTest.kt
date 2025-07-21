// File: ReturnToCommsTest.kt
// Test ID: AUT-FN-004
// Purpose: Critical test - closing dialer returns to Communications screen intact
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ReturnToCommsTest {
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
    fun `AUT_FN_004_should_return_to_Communications_screen_when_dialer_is_closed`() {
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

        // Wait for dialer to open
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            val pkg = driver.currentPackage
            pkg != null && pkg != config.Constants.APP_PACKAGE &&
                (pkg.contains("dialer") || pkg.contains("contacts") || pkg.contains("incall"))
        }
        
        // Verify dialer opened
        val pkg = driver.currentPackage
        assertThat(pkg).isNotEqualTo(config.Constants.APP_PACKAGE)
        assertThat(pkg).matches(".*(dialer|contacts|incall).*")
        
        // Close dialer using back button - using proper Appium method
        driver.navigate().back()
        
        // Wait to return to Communications screen
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            driver.currentPackage == config.Constants.APP_PACKAGE
        }
        
        // Verify we're back on Communications screen
        assertThat(driver.currentPackage).isEqualTo(config.Constants.APP_PACKAGE)
        
        // Verify call button is still present and functional
        WebDriverWait(driver, Duration.ofSeconds(5)).until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        
        val callButton = driver.findElement(commScreen.callButtonSelector)
        assertThat(callButton.isDisplayed).isTrue()
        assertThat(callButton.isEnabled).isTrue()
        
        // Verify screen state is intact (no crashes, no data loss)
        // TODO: Add additional state verification if needed
    }
} 
