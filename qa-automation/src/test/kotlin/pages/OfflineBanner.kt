// File: OfflineBanner.kt
// Purpose: Page Object for offline banner
// Author: Taras Mylyi
// Used in offline state automation tests

package pages

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class OfflineBanner(private val driver: AndroidDriver) {
    val offlineBannerSelector = By.xpath("//*[contains(@text, 'offline') or contains(@text, 'Немає підключення') or contains(@text, 'Žiadne internetové')]")

    fun isVisible(): Boolean = driver.findElements(offlineBannerSelector).isNotEmpty()
} 