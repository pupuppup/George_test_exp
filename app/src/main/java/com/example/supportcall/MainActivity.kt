// File: MainActivity.kt
// Purpose: Entry point for the app, sets Compose content
// Author:Taras Mylyi
// MainActivity for single-activity Compose app

package com.example.supportcall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.supportcall.ui.AppRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRoot()
        }
    }
} 