// File: TopicSelectionScreen.kt
// Purpose: Topic selection screen styled as on screenshot (SK)
// Author: <your name>

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

@Composable
fun TopicSelectionScreen(onBack: () -> Unit) {
    val context = LocalContext.current
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
            options.forEach { option ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            openDialer(context, "+421000000000")
                        },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF23242A))
                ) {
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

fun openDialer(context: Context, number: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$number")
    }
    context.startActivity(intent)
} 