package com.example.diasderecetas.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = TomatoPrimary,
    onPrimary = TomatoOnPrimary,
    primaryContainer = TomatoPrimaryContainer,
    onPrimaryContainer = TomatoOnPrimaryContainer,
    secondary = OlivaSecondary,
    onSecondary = OlivaOnSecondary,
    secondaryContainer = OlivaSecondaryContainer,
    onSecondaryContainer = OlivaOnSecondaryContainer,
    background = Background,
    onBackground = OnBackground,
    surface = Surface,
    onSurface = OnSurface
)

private val DarkColors = darkColorScheme(
    primary = TomatoPrimaryDark,
    onPrimary = TomatoOnPrimaryDark,
    primaryContainer = TomatoPrimaryContainerDark,
    onPrimaryContainer = TomatoOnPrimaryContainerDark,
    secondary = OlivaSecondaryDark,
    onSecondary = OlivaOnSecondaryDark,
    secondaryContainer = OlivaSecondaryContainerDark,
    onSecondaryContainer = OlivaOnSecondaryContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark
)

@Composable
fun DiasDeRecetasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}