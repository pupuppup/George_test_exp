// File: NegativePathTest.kt
// Author: Taras Mylyi
package tests

import base.BaseTest
import org.junit.jupiter.api.Test
import pages.CommunicationsScreen
import org.openqa.selenium.By
import org.assertj.core.api.Assertions.assertThat

class NegativePathTest : BaseTest() {

    @Test
    fun `should not be able to call when button is disabled`() {
        // TODO: Перевести додаток у offline перед перевіркою (інакше кнопка буде enabled)
        val commScreen = CommunicationsScreen(driver)
        // Always get a fresh element for Compose Button (never cache!)
        val button = driver.findElement(commScreen.callButtonSelector)
        assertThat(button.isEnabled).isFalse()
    }

    @Test
    fun `should return to topic selection screen after cancelling dialer`() {
        // Викликаємо FakeDialerActivity, натискаємо Cancel, перевіряємо повернення
        // TODO: реалізувати через startActivityForResult і RESULT_CANCELED
    }
} 