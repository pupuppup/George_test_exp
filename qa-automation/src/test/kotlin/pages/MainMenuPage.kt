package pages

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class MainMenuPage(private val driver: AndroidDriver) {
    // Умовно, що є кнопка "Комунікації"
    val communicationsButton = By.id("communicationsButton")

    fun openCommunications() {
        driver.findElement(communicationsButton).click()
    }
} 