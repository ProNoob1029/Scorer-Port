package com.dragos.scorerport.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontWeight

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = Typography().headlineLarge.copy(
        fontWeight = FontWeight.Medium
    ),
    headlineSmall = Typography().headlineSmall.copy(
        fontWeight = FontWeight.Medium
    ),
    titleMedium = Typography().titleMedium.copy(
        fontWeight = Typography().titleLarge.fontWeight
    )
)