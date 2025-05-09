package com.app.cryptocurrency.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    surface = surface,
    onSurface = onSurface,
    background = DarkGray,
    onBackground = TextWhite,
    tertiary = tertiary,
)

private val LightColorScheme = lightColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    surface = surface,
    onSurface = onSurface,
    background = Color.White,
    onBackground = MediumGray,
    inversePrimary = inversePrimary,
    outline = outline,
    error = error
)

@Composable
fun CryptocurrencyAppYTTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
