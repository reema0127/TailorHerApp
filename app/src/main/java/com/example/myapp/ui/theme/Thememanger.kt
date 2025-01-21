package com.example.myapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.myapp.DataClases.DarkTheme
import com.example.myapp.DataClases.LightTheme

// This will provide the current theme across the composables
val LocalTheme = staticCompositionLocalOf { LightTheme } // Default to LightTheme

@Composable
fun ThemeProvider(isDarkMode: Boolean, content: @Composable () -> Unit) {
    // Determine the current theme based on the `isDarkMode` flag
    val currentTheme = if (isDarkMode) DarkTheme else LightTheme

    // Provide the current theme to the composables inside this provider
    CompositionLocalProvider(LocalTheme provides currentTheme) {
        content()
    }
}
