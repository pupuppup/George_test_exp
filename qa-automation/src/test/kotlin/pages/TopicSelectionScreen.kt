package pages

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import io.appium.java_client.AppiumBy

class TopicSelectionScreen(private val driver: AndroidDriver) {
    val clientCentreSelector = By.xpath("//*[contains(@text, 'Client centre') or contains(@text, 'Klientské centrum')]")
    val clientCentreAbroadSelector = By.xpath("//*[contains(@text, 'abroad') or contains(@text, 'zahraničia')]")

    fun selectClientCentre() {
        driver.findElement(clientCentreSelector).click()
    }

    fun selectClientCentreAbroad() {
        driver.findElement(clientCentreAbroadSelector).click()
    }
} 