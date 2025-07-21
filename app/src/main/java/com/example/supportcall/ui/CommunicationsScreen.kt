// File: CommunicationsScreen.kt
// Purpose: Main screen with single support card styled as on screenshot (SK)
// Author: <your name>

package com.example.supportcall.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CommunicationsScreen(
    isOffline: Boolean,
    isLoggedIn: Boolean,
    onCallClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181A20)),
        color = Color(0xFF181A20)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            SupportCard(
                onCall = onCallClick,
                enabled = !isOffline
            )
        }
    }
} 