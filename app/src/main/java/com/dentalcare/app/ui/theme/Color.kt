package com.dentalcare.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light Theme Colors
private val PrimaryLight = Color(0xFF00897B)
private val SecondaryLight = Color(0xFFE64A19)
private val TertiaryLight = Color(0xFF0288D1)
private val BackgroundLight = Color(0xFFFAFAFA)
private val SurfaceLight = Color(0xFFFFFFFF)
private val ErrorLight = Color(0xFFE53935)

// Dark Theme Colors
private val PrimaryDark = Color(0xFF4DB8A8)
private val SecondaryDark = Color(0xFFFF7043)
private val TertiaryDark = Color(0xFF4FC3F7)
private val BackgroundDark = Color(0xFF121212)
private val SurfaceDark = Color(0xFF1E1E1E)
private val ErrorDark = Color(0xFFEF5350)

private val LightColorScheme = lightColorScheme(\n    primary = PrimaryLight,
    secondary = SecondaryLight,
    tertiary = TertiaryLight,
    background = BackgroundLight,
    surface = SurfaceLight,
    error = ErrorLight,
)\n\nprivate val DarkColorScheme = darkColorScheme(\n    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = TertiaryDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    error = ErrorDark,
)\n\n@Composable\nfun DentalCareTheme(\n    darkTheme: Boolean = false,\n    content: @Composable () -> Unit,\n) {\n    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme\n\n    MaterialTheme(\n        colorScheme = colorScheme,\n        typography = DentalCareTypography,\n        shapes = DentalCareShapes,\n        content = content,\n    )\n}\n"