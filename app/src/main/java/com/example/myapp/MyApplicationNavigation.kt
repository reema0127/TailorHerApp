package com.example.myapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.pages.*

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, autheticationViewModel: AutheticationViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable("Login") {
            LoginPage(modifier, navController, autheticationViewModel)
        }
        composable("signup") {
            SignUpPage(modifier, navController, autheticationViewModel)
        }
        composable("home") {
            HomePage(modifier, navController, autheticationViewModel)
        }
        composable("detail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            itemId?.let {
                DetailScreen(modifier, itemId, navController)
            }
        }
        // Add these new routes
        composable("cart") {
            CartPageUI(modifier,navController)
        }

        composable("account") {
            UserDashboard(modifier, navController, autheticationViewModel)
        }
        composable("about_us") {
            AboutUs(navController) // Assuming AboutUs is a composable function
        }
//        composable("CoffeeScreen") {
//            SearchPage(navController) // Assuming AboutUs is a composable function
//        }
        composable("search") {
            ClothingCollectionScreen(navController)
        }

        composable("summer") { ProductGridPage() }
        composable("winter") { WinterProductGridPage() }

        composable("profile") {
            ProfilePage(onDismiss = {
                navController.popBackStack()
            }
            )
        }
    }
}