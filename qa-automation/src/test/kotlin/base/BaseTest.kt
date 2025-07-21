package base

import config.DriverFactory
import io.appium.java_client.android.AndroidDriver
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.support.ui.FluentWait
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
    protected lateinit var driver: AndroidDriver
    protected lateinit var wait: FluentWait<AndroidDriver>

    @BeforeAll
    fun setup() {
        driver = DriverFactory.create()
        wait = FluentWait(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException::class.java)
    }

    @BeforeEach
    fun ensureAppInForeground() {
        if (driver.currentPackage != "com.example.supportcall") {
            driver.activateApp("com.example.supportcall")
        }
    }

    @AfterAll
    fun teardown() {
        driver.quit()
    }
} 