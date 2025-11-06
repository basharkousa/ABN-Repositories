package com.bashar.abnrepositories.src.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val primary500 = Color(0xFF000000)
val primary300 = Color(0xFF40424D)
val primary200 = Color(0xFF6E7180)
val Primary100 = Color(0xFF9DA2B3)
val primary50  = Color(0xFFBCBFCC)
val primary25  = Color(0xFFD3D6E0)
val primaryWhite = Color(0xFFFFFFFF)


// ===== Material 3 Color Schemes =====
val LightColorScheme = lightColorScheme(
 primary = Color(0xFF007B5F),        // Brand green
 onPrimary = Color.White,
 secondary = Color(0xFF009879),      // Teal accent
 tertiary = Color(0xFFFFD200),       // Yellow CTA
 background = Color(0xFFF9F9F9),     // Very light grey
 onBackground = Color(0xFF002B23),   // Deep green text
 surface = Color.White,
 onSurface = Color(0xFF1B1B1B),
 error = Color(0xFFD32F2F)
)


val DarkColorScheme = darkColorScheme(
 primary = Color(0xFF00B58B),        // Lighter tone of brand for dark bg
 onPrimary = Color(0xFF00382E),
 secondary = Color(0xFF00C1A0),
 tertiary = Color(0xFFFFD200),
 background = Color(0xFF121212),
 onBackground = Color(0xFFE6E6E6),
 surface = Color(0xFF1B1B1B),
 onSurface = Color(0xFFE6E6E6),
 error = Color(0xFFEF5350)
)

