package com.example.myapp.pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapp.DataClases.LocalUserStore
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun ProfilePage(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val userStore = remember { LocalUserStore(context) }
    val auth = remember { FirebaseAuth.getInstance() }

    var currentEmail by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    // Load current email from local store
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val savedEmail = userStore.getEmail.first() ?: ""
            currentEmail = savedEmail
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Close button row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Profile Modal"
                    )
                }
            }

            Text(text = "Edit Profile", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))

            // Disabled Email Field
            OutlinedTextField(
                value = currentEmail,
                onValueChange = { },
                label = { Text("Email (cannot change)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("New Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = currentPassword,
                onValueChange = { currentPassword = it },
                label = { Text("Current Password (for verification)") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        val user = auth.currentUser
                        if (user != null && currentEmail.isNotEmpty() && currentPassword.isNotEmpty()) {
                            val credential = EmailAuthProvider.getCredential(currentEmail, currentPassword)

                            user.reauthenticate(credential)
                                .addOnCompleteListener { reauthTask ->
                                    if (reauthTask.isSuccessful) {
                                        if (newPassword.isNotBlank()) {
                                            user.updatePassword(newPassword)
                                                .addOnSuccessListener {
                                                    Toast.makeText(context, "Password updated", Toast.LENGTH_SHORT).show()
                                                    onDismiss()
                                                }
                                                .addOnFailureListener {
                                                    Toast.makeText(context, "Failed to update password: ${it.message}", Toast.LENGTH_LONG).show()
                                                }
                                        } else {
                                            Toast.makeText(context, "Please enter a new password", Toast.LENGTH_SHORT).show()
                                        }
                                    } else {
                                        Toast.makeText(context, "Re-authentication failed: ${reauthTask.exception?.message}", Toast.LENGTH_LONG).show()
                                    }
                                }
                        } else {
                            Toast.makeText(context, "Please enter current password", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Save Changes")
            }
        }
    }
}

