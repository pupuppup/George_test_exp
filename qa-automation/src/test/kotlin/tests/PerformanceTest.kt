// File: PerformanceTest.kt
// Author: Taras Mylyi
// Test ID: AUT-PERF-001
// Purpose: Nightly test - dialer latency performance check
// Priority: ★ Nightly
// Tags: nightly, perf

package tests

import config.DriverFactory
import pages.CommunicationsScreen
import pages.TopicSelectionScreen
import org.junit.jupiter.api.*
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import org.assertj.core.api.Assertions.assertThat
import base.BaseTest
import org.junit.jupiter.api.Tag

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class PerformanceTest : BaseTest() {

    @Test
    @Order(1)
    @Tag("nightly")
    @Tag("perf")
    fun `AUT_PERF_001_should_open_dialer_within_acceptable_latency`() {
        val commScreen = CommunicationsScreen(driver)
        
        // Always get a fresh element for Compose Button (never cache!)
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            driver.findElement(commScreen.callButtonSelector).isDisplayed
        }
        val start = System.currentTimeMillis()
        driver.findElement(commScreen.callButtonSelector).click()
        // TODO: дочекатися FakeDialerActivity
        val elapsed = System.currentTimeMillis() - start
        assertThat(elapsed).isLessThan(1800)

        val topicScreen = TopicSelectionScreen(driver)
        WebDriverWait(driver, Duration.ofSeconds(5)).until {
            driver.findElement(topicScreen.clientCentreSelector).isDisplayed
        }
        topicScreen.selectClientCentre()

        // Wait for dialer to open and measure total latency
        WebDriverWait(driver, Duration.ofSeconds(10)).until {
            val pkg = driver.currentPackage
            pkg != null && pkg != config.Constants.APP_PACKAGE &&
                (pkg.contains("dialer") || pkg.contains("contacts") || pkg.contains("incall"))
        }
        val endTime = System.currentTimeMillis()
        
        val latency = endTime - start
        
        // Verify latency is within acceptable range
        assertThat(latency).isLessThanOrEqualTo(800) // Target: ≤ 800ms
        
        // Warning if latency is high but not failing
        if (latency > 1000) {
            System.err.println("WARNING: Dialer latency ${latency}ms exceeds 1 second threshold")
        }
        
        // Verify dialer opened successfully
        val pkg = driver.currentPackage
        assertThat(pkg).isNotEqualTo(config.Constants.APP_PACKAGE)
        assertThat(pkg).matches(".*(dialer|contacts|incall).*")
        
        println("Dialer latency: ${latency}ms")
    }
} 
