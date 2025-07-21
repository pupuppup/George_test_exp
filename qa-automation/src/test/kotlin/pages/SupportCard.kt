// File: SupportCard.kt
// Purpose: Page Object for isolated support card (for unit/visual tests)
// Author: Taras Mylyi
// Used for future extensibility

package pages

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class SupportCard(private val driver: AndroidDriver) {
    val cardSelector = By.xpath("//*[contains(@text, '24/7 Support') or contains(@text, 'Podpora 24/7')]")
    val buttonSelector = By.xpath("//*[contains(@text, 'Call') or contains(@text, 'Zavolať')]")

    fun isVisible(): Boolean = driver.findElements(cardSelector).isNotEmpty()
    fun tapButton() = driver.findElement(buttonSelector).click()
} 