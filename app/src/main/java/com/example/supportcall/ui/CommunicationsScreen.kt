// File: CommunicationsScreen.kt
// Purpose: Main screen with single support card styled as on screenshot (SK)
// Author:Taras Mylyi

package com.example.supportcall.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supportcall.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunicationsScreen(
    onCallClick: () -> Unit
) {
    val background = colorResource(id = R.color.background)
    val cardBg = colorResource(id = R.color.card_bg)
    val buttonColor = colorResource(id = R.color.call_button)
    val buttonTextColor = colorResource(id = R.color.button_text)
    val textPrimary = colorResource(id = R.color.text_primary)
    val textSecondary = colorResource(id = R.color.text_secondary)
    val iconColor = colorResource(id = R.color.icon_secondary)
    val surface = colorResource(id = R.color.surface)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // AppBar
            TopAppBar(
                title = {
                    Text(
                        text = "Support Call Demo",
                        color = textPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surface
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            // Support Card
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .background(cardBg, shape = RoundedCornerShape(24.dp))
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Klientske centrum",
                        color = textPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Spojte sa s nami.",
                        color = textSecondary,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = onCallClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor,
                            contentColor = buttonTextColor
                        ),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("callButton")
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_call),
                            contentDescription = null,
                            tint = buttonTextColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Zavolať",
                            color = buttonTextColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
} 