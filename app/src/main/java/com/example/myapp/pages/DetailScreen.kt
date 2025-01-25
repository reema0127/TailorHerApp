package com.example.myapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapp.DataSource
import com.example.myapp.R

@Composable
fun DetailScreen(modifier: Modifier = Modifier, itemId: String, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Back Button
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }

        // Mocked details (replace with actual data based on `itemId`)
        Text(text = "Item Details for ID: $itemId")
        Spacer(modifier = Modifier.height(16.dp))

        // Image, title, price, and description
        Image(
            painter = painterResource(id = R.drawable.dress1), // Replace with dynamic item
            contentDescription = "Item Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Text(text = "Floral Dream")
        Text(text = "LKR 6000")
        Text(
            text = "A short corset dress with a lace-up bodice and flared skirt.",

        )

        Spacer(modifier = Modifier.weight(1f))

        // Action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { /* Handle buy now */ }) {
                Text("Buy now")
            }
            Button(onClick = { /* Handle add to cart */ }) {
                Text("Add to cart")
            }
        }
    }
}

