// File: TopicSelectionScreen.kt
// Purpose: Page Object for topic selection screen
// Author: Taras Mylyi
// Used in automation tests

package pages

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import io.appium.java_client.AppiumBy

class TopicSelectionScreen(private val driver: AndroidDriver) {
    // Тепер використовуємо accessibility id (content-desc) для кнопок
    val clientCentreSelector = AppiumBy.accessibilityId("clientCentreButton")
    val clientCentreAbroadSelector = AppiumBy.accessibilityId("clientCentreAbroadButton")

    fun selectClientCentre() {
        driver.findElement(clientCentreSelector).click()
    }

    fun selectClientCentreAbroad() {
        driver.findElement(clientCentreAbroadSelector).click()
    }
} 