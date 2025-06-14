package com.example.myapp.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.AuthState
import com.example.myapp.AutheticationViewModel
import com.example.myapp.DataClases.LocalUserStore
import com.example.myapp.R
import com.example.myapp.ui.theme.playfairdisplayFamily

@Composable
fun LoginPage(modifier: Modifier = Modifier, navController: NavController, autheticationViewModel: AutheticationViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState = autheticationViewModel.authState.observeAsState()
    val context = LocalContext.current

//    LaunchedEffect(authState.value) {
//        when (authState.value) {
//            is AuthState.Authenticated -> navController.navigate("home")
//            is AuthState.Error -> Toast.makeText(
//                context,
//                (authState.value as AuthState.Error).message,
//                Toast.LENGTH_SHORT
//            ).show()
//            else -> Unit
//        }
//    }

    val userStore = LocalUserStore(context)

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                userStore.saveEmail(email) // Save email locally
                navController.navigate("home")
            }
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }


    val isDarkTheme = isSystemInDarkTheme()
    val backgroundRes = if (isDarkTheme) R.drawable.loginbgdark else R.drawable.loginbg

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = "Login Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(600.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
                ),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Welcome Back!",
                        fontFamily = playfairdisplayFamily,
                        fontSize = 35.sp,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 40.dp)
                    )

                    Text(
                        text = "Email",
                        fontFamily = playfairdisplayFamily,
                        fontSize = 22.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, bottom = 8.dp)
                    )
                    BasicTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(16.dp),
                        singleLine = true
                    )



                    // Password Section
                    Text(
                        text = "Password",
                        fontFamily = playfairdisplayFamily,
                        fontSize = 22.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, bottom = 8.dp)
                    )
                    BasicTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(16.dp),

                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = { autheticationViewModel.login(email, password) },
                        modifier = Modifier
                            .width(100.dp)
                            .height(45.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White.copy(alpha = 0.6f),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "Login",
                            fontFamily = playfairdisplayFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = { navController.navigate("signup") }) {
                        Text(text = "Don't have an Account? Signup",
                            color = Color.White,
                            fontFamily = playfairdisplayFamily,
                            fontSize = 18.sp,)
                    }
                }
            }
        }
    }
}
