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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.AuthState
import com.example.myapp.AutheticationViewModel
import com.example.myapp.R
import com.example.myapp.ui.theme.playfairdisplayFamily

@Composable
fun SignUpPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    autheticationViewModel: AutheticationViewModel
) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState = autheticationViewModel.authState.observeAsState()
    val context = LocalContext.current
    val CustomPink = Color(0xFFD0AAAB) // Custom pink color

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("login")
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
                    .height(700.dp),
                shape = RoundedCornerShape(24.dp), // Larger rounded corners
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
                ),
                elevation = CardDefaults.cardElevation(0.dp) // No border or shadow
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp), // Adjusted padding for the form
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Sign Up",
                        fontSize = 40.sp,
                        fontFamily = playfairdisplayFamily,
                        color = Color.White,
                        )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "First Name",
                        fontFamily = playfairdisplayFamily,
                        fontSize = 22.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, bottom = 3.dp, top = 8.dp)
                    )
                    BasicTextField(
                        value = firstname,
                        onValueChange = { firstname = it },

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(16.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Last Name",
                        fontSize = 22.sp,
                        fontFamily = playfairdisplayFamily,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, bottom = 6.dp)
                    )
                    BasicTextField(
                        value = lastname,
                        onValueChange = { lastname = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(16.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Email",
                        fontSize = 22.sp,
                        fontFamily = playfairdisplayFamily,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, bottom = 6.dp)
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

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Password",
                        fontSize = 22.sp,
                        fontFamily = playfairdisplayFamily,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, bottom = 6.dp)
                    )
                    BasicTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(16.dp),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { autheticationViewModel.signup(email, password) },
                        modifier = Modifier
                            .width(120.dp)
                            .height(45.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White.copy(alpha = 0.6f),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "Sign Up",
                            fontFamily = playfairdisplayFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(onClick = { navController.navigate("login") }) {
                        Text(text = "Already have an account? Login",
                            color = Color.White,
                            fontFamily = playfairdisplayFamily,
                            fontSize = 18.sp )
                    }
                }
            }
        }
    }
}