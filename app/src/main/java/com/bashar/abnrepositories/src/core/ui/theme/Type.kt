package com.bashar.abnrepositories.src.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.bashar.abnrepositories.R

val WestMoscow = FontFamily(
    Font(R.font.west_moscow, weight = FontWeight.W400, style = FontStyle.Normal)
)

val Montserrat = FontFamily(
    Font(R.font.montserrat_regular, weight = FontWeight.W400),
    Font(R.font.montserrat_medium, weight = FontWeight.W500),
    Font(R.font.montserrat_semi_bold, weight = FontWeight.W600),
    Font(R.font.montserrat_bold, weight = FontWeight.W700)
)


fun lsPercent(pct: Float): TextUnit = (pct / 100f).em
fun lhPercent(fontSizeSp: Int, pct: Float): TextUnit = (fontSizeSp * pct / 100f).sp

val Typography = Typography(

    // Display / Hero titles
    displayLarge = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W700,
        fontSize = 44.sp, lineHeight = lhPercent(44, 120f), letterSpacing = lsPercent(0f)
    ),
    displayMedium = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W700,
        fontSize = 36.sp, lineHeight = lhPercent(36, 120f), letterSpacing = lsPercent(0f)
    ),
    displaySmall = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W600,
        fontSize = 28.sp, lineHeight = lhPercent(28, 130f), letterSpacing = lsPercent(0f)
    ),

    // Headings (used on cards, sections)
    headlineLarge = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W600,
        fontSize = 24.sp, lineHeight = lhPercent(24, 135f), letterSpacing = lsPercent(0f)
    ),
    headlineMedium = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W600,
        fontSize = 20.sp, lineHeight = lhPercent(20, 140f), letterSpacing = lsPercent(0f)
    ),
    headlineSmall = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W500,
        fontSize = 18.sp, lineHeight = lhPercent(18, 140f), letterSpacing = lsPercent(0f)
    ),

    // Titles (navigation bars, app bars)
    titleLarge = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W600,
        fontSize = 22.sp, lineHeight = lhPercent(22, 130f), letterSpacing = lsPercent(0f)
    ),
    titleMedium = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W600,
        fontSize = 18.sp, lineHeight = lhPercent(18, 130f), letterSpacing = lsPercent(0f)
    ),
    titleSmall = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W500,
        fontSize = 16.sp, lineHeight = lhPercent(16, 130f), letterSpacing = lsPercent(0f)
    ),

    // Body text
    bodyLarge = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W400,
        fontSize = 18.sp, lineHeight = lhPercent(18, 145f), letterSpacing = lsPercent(0f)
    ),
    bodyMedium = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W400,
        fontSize = 16.sp, lineHeight = lhPercent(16, 150f), letterSpacing = lsPercent(0f)
    ),
    bodySmall = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W400,
        fontSize = 14.sp, lineHeight = lhPercent(14, 155f), letterSpacing = lsPercent(0f)
    ),

    // Labels / Buttons / Chips
    labelLarge = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W600,
        fontSize = 14.sp, lineHeight = lhPercent(14, 140f), letterSpacing = lsPercent(1f)
    ),
    labelMedium = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W600,
        fontSize = 12.sp, lineHeight = lhPercent(12, 140f), letterSpacing = lsPercent(1f)
    ),
    labelSmall = TextStyle(
        fontFamily = Montserrat, fontWeight = FontWeight.W400,
        fontSize = 11.sp, lineHeight = lhPercent(11, 140f), letterSpacing = lsPercent(0f)
    ),
)
