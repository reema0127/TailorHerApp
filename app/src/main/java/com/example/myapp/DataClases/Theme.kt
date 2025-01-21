package com.example.myapp.DataClases

import androidx.compose.ui.graphics.Color

data class Theme(
    val backgroundColor: Color,
    val textColor: Color,
    val navBarColor: Color,
    val searchBarColor: Color
)

val LightTheme = Theme(
    backgroundColor = Color(0xFFF7EFDA),
    textColor = Color(0xFF6D4C41),
    navBarColor = Color(0xFFF8C9D5), // Light nav bar color
    searchBarColor = Color(0xFFF8C9D5) // Light search bar color
)

val DarkTheme = Theme(
    backgroundColor = Color(0xFF2C2C2C),
    textColor = Color.White,
    navBarColor = Color(0xFF995E5E), // Dark nav bar color
    searchBarColor = Color(0xFF995E5E) // Dark search bar color
)
