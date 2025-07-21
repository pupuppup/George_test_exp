// File: Constants.kt
// Purpose: Centralizes app package/activity for automation
// Author: <your name>
// Reads from system properties with defaults

package config

object Constants {
    val APP_PACKAGE: String = System.getProperty("app.package", "com.example.supportcall")
    val APP_ACTIVITY: String = System.getProperty("app.activity", ".MainActivity")
} 