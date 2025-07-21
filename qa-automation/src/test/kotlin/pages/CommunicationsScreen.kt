// File: CommunicationsScreen.kt
// Purpose: Page Object for Communications screen (support card, call button)
// Author: Taras Mylyi
// Used in automation tests

package pages

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.AppiumBy
import org.openqa.selenium.By

class CommunicationsScreen(private val driver: AndroidDriver) {
    val supportCardSelector = By.xpath("//*[contains(@text, '24/7') or contains(@text, 'Підтримка') or contains(@text, 'Podpora')]")
    
    // NOTE: Jetpack Compose renders Button as android.widget.Button, but the text is a separate TextView inside.
    // Do NOT search by text! Always use By.className("android.widget.Button") for the main call button.
    val callButtonSelector = By.className("android.widget.Button")

    fun tapCallButton() {
        driver.findElement(callButtonSelector).click()
    }
} 