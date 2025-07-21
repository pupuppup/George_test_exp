package pages

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import org.assertj.core.api.Assertions.assertThat

class PhoneIntentPage(private val driver: AndroidDriver) {
    val phoneNumberField = By.id("phoneNumberField")

    fun verifyPhoneNumber(expectedNumber: String) {
        val actualNumber = driver.findElement(phoneNumberField).text
        assertThat(actualNumber).isEqualTo(expectedNumber)
    }
} 