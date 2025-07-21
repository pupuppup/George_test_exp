// File: AppRoot.kt
// Purpose: Root composable with navigation, debug menu, offline toggle
// Author: Taras Mylyi
// Handles navigation and debug actions

package com.example.supportcall.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot() {
    var isOffline by remember { mutableStateOf(false) }
    var isLoggedIn by remember { mutableStateOf(true) } // Fake login
    var showTopic by remember { mutableStateOf(false) }

    Scaffold(
        // topBar = {
        //     TopAppBar(
        //         title = { Text("Support Call Demo") }
        //     )
        // }
    ) { padding ->
        if (showTopic) {
            TopicSelectionScreen(onBack = { showTopic = false })
        } else {
            CommunicationsScreen(
                onCallClick = { showTopic = true }
            )
        }
    }
} 