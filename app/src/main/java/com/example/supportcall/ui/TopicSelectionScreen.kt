// File: TopicSelectionScreen.kt
// Purpose: Topic selection screen styled as on screenshot (SK)
// Author:Taras Mylyi

package com.example.supportcall.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import android.app.Activity
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import com.example.supportcall.BuildConfig

@Composable
fun TopicSelectionScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onBack()
        }
    }
    val title = "Spojte sa s nami"
    val subtitle = "Vyberte možnosť"
    val options = listOf("Klientske centrum", "Klientske centrum zo zahraničia")
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181A20)),
        color = Color(0xFF181A20)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = subtitle,
                color = Color(0xFFB0B0B0),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            options.forEachIndexed { idx, option ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .semantics { contentDescription = if (idx == 0) "clientCentreButton" else "clientCentreAbroadButton" }
                        .clickable {
                            val number = if (idx == 0) "+421000000000" else "+421111111111"
                            val intent = if (BuildConfig.DEBUG) {
                                Intent("com.example.supportcall.FAKE_DIAL", Uri.parse("tel:$number"))
                            } else {
                                Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
                            }
                            if (activity != null) {
                                launcher.launch(intent)
                            } else {
                                context.startActivity(intent)
                            }
                        },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF23242A))
                ) {
                    Box {
                        Text(
                            text = option,
                            modifier = Modifier.padding(20.dp),
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
} 