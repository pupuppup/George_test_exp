// File: SupportCard.kt
// Purpose: Compose card styled as on screenshot (SK)
// Author: <your name>

package com.example.supportcall.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Headset
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SupportCard(
    onCall: () -> Unit,
    enabled: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Klientske centrum",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Spojte sa s nami.",
                    color = Color(0xFFB0B0B0), // light gray
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onCall,
                    enabled = enabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics {
                            contentDescription = "Zavolať"
                        },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF223A5E))
                ) {
                    Icon(Icons.Filled.Phone, contentDescription = null, tint = Color(0xFF4FC3F7))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Zavolať", color = Color(0xFF4FC3F7), fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF2C2C2C), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Rounded.Headset, contentDescription = null, tint = Color(0xFFB0B0B0))
            }
        }
    }
} 