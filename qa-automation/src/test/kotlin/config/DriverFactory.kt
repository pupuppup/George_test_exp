// File: DriverFactory.kt
// Purpose: Factory for AndroidDriver with system property/env config
// Author: <your name>
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
        
        // Get device name from adb devices
        val deviceName = System.getenv("DEVICE_NAME") ?: "j78dhmtkkfz5sgv8" // Use actual device ID
        
        val options = UiAutomator2Options()
            .setDeviceName(deviceName)
            .setUdid(deviceName) // Add UDID for physical device
            .setAutomationName("UiAutomator2")
            .autoGrantPermissions()
            .setNoReset(false) // Reset app state
            .setAppPackage(appPackage)
            .setAppActivity(appActivity)
        
        // For Appium 2, use the correct URL structure
        val appium2Url = "$appiumUrl/wd/hub"
        return AndroidDriver(URL(appium2Url), options)
    }
} 