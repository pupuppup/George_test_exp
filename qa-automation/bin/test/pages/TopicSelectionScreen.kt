// File: TopicSelectionScreen.kt
// Purpose: Page Object for topic selection screen
// Author: <your name>
// Used in automation tests

package pages

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class TopicSelectionScreen(private val driver: AndroidDriver) {
    val clientCentreSelector = By.xpath("//*[contains(@text, 'Client centre') or contains(@text, 'Клієнтський центр') or contains(@text, 'Klientské centrum')]")
    val clientCentreAbroadSelector = By.xpath("//*[contains(@text, 'abroad') or contains(@text, 'з-за кордону') or contains(@text, 'zahraničia')]")

    fun selectClientCentre() {
        driver.findElement(clientCentreSelector).click()
    }

    fun selectClientCentreAbroad() {
        driver.findElement(clientCentreAbroadSelector).click()
    }
} 