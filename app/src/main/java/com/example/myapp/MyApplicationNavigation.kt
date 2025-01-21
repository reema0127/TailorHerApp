package com.example.myapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.pages.HomePage
import com.example.myapp.pages.LoginPage
import com.example.myapp.pages.SignUpPage

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier,autheticationViewModel: AutheticationViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination ="Login", builder= {
        composable("Login"){
            LoginPage(modifier,navController,autheticationViewModel)
        }
        composable("signup"){
            SignUpPage(modifier,navController,autheticationViewModel)
        }
        composable("home"){
            HomePage(modifier,navController,autheticationViewModel)
        }
    })
}