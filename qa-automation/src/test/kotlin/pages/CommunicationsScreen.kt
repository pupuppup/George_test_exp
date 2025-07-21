// File: CommunicationsScreen.kt
// Purpose: Page Object for Communications screen (support card, call button)
// Author: <your name>
// Used in automation tests

package pages

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.AppiumBy
import org.openqa.selenium.By

class CommunicationsScreen(private val driver: AndroidDriver) {
    val supportCardSelector = By.xpath("//*[contains(@text, '24/7') or contains(@text, 'Підтримка') or contains(@text, 'Podpora')]")
    
    // Updated locator based on diagnostic test - found 'Zavola┼е' on screen
    val callButtonSelector = By.xpath("//*[contains(@text, 'Zavola┼е') or contains(@text, 'Зателефонувати') or contains(@text, 'Call')]")

    fun tapCallButton() {
        driver.findElement(callButtonSelector).click()
    }
} 