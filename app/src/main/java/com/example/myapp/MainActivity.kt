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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel: AutheticationViewModel by viewModels()
//        enableEdgeToEdge()
        installSplashScreen()


        setContent {
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
