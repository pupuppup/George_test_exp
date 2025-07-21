// File: CommunicationsScreen.kt
// Purpose: Page Object for Communications screen (support card, call button)
// Author: <your name>
// Used in automation tests

package pages

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class CommunicationsScreen(private val driver: AndroidDriver) {
    val supportCardSelector = By.xpath("//*[contains(@text, '24/7') or contains(@text, 'Підтримка') or contains(@text, 'Podpora')]")
    val callButtonSelector = By.xpath("//*[contains(@text, 'Зателефонувати') or contains(@text, 'Call') or contains(@text, 'Zavolať')]")

    fun tapCallButton() {
        driver.findElement(callButtonSelector).click()
    }
} 