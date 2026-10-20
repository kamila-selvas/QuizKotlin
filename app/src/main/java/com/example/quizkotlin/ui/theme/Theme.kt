package com.example.quizkotlin.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorAcierto = Color(0xFF2E7D32)
val ColorError = Color(0xFFB3261E)

private val EsquemaMorado = lightColorScheme(
    primary = Color(0xFF6750A4),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE8DEF8),
    onPrimaryContainer = Color(0xFF2D1B69),
    secondaryContainer = Color(0xFFE8DEF8),
    onSecondaryContainer = Color(0xFF2D1B69),
    background = Color(0xFFFDFBFF),
    surface = Color(0xFFFDFBFF),
    surfaceVariant = Color(0xFFF3EDF7),
    outlineVariant = Color(0xFFCAC4D0)
)

@Composable
fun QuizKotlinTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = EsquemaMorado, content = content)
}
