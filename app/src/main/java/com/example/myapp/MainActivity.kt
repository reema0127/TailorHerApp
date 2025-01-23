package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapp.ui.theme.MyAppTheme
import com.example.myapp.ui.theme.ThemeProvider
import com.example.myapp.ui.theme.LocalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel: AutheticationViewModel by viewModels()
//        enableEdgeToEdge()
        installSplashScreen()


        setContent {
            // Pass `isDarkMode` state to ThemeProvider
            ThemeProvider(isDarkMode = true) {  // You can toggle this value
                MyAppTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        MyAppNavigation(
                            modifier = Modifier.padding(innerPadding),
                            autheticationViewModel = authViewModel
                        )
                    }
                }
            }
        }
    }
}
