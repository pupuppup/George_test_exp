// File: DriverFactory.kt
// Purpose: Factory for AndroidDriver with system property/env config
// Author: Taras Mylyi
// Reads appium.url, app.package, app.activity

package config

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import java.net.URL

object DriverFactory {
    fun create(): AndroidDriver {
        val appiumUrl = System.getProperty("appium.url", "http://127.0.0.1:4723")
        val appPackage = System.getProperty("app.package", "com.example.supportcall")
        val appActivity = System.getProperty("app.activity", ".MainActivity")
        val deviceName = System.getenv("DEVICE_NAME") ?: "emulator-5554"
        val options = UiAutomator2Options()
            .setDeviceName(deviceName)
            .setAppPackage(appPackage)
            .setAppActivity(appActivity)
            .autoGrantPermissions()
        return AndroidDriver(URL(appiumUrl), options)
    }
} 