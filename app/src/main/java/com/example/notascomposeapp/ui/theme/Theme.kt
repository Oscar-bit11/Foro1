package com.example.notascomposeapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = AcademicBlue,
    secondary = AcademicSky,
    background = AcademicBackground,
    surface = AcademicCard
)

private val DarkColors = darkColorScheme(
    primary = AcademicSky,
    secondary = AcademicBlue
)

@Composable
fun NotasComposeAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
