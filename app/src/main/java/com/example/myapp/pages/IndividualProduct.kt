package com.example.myapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.R

@Composable
fun SkirtPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Main Skirt Image
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.skirt2), // Replace with your image resource
                contentDescription = "Purple Skirt",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Color Variations
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Add each skirt variation here
            item {
                ColorOptionCard(imageRes = R.drawable.skirt22, description = "Pink Skirt")
            }
            item {
                ColorOptionCard(imageRes = R.drawable.skirt23, description = "Blue Skirt")
            }
            item {
                ColorOptionCard(imageRes = R.drawable.skirt24, description = "Green Skirt")
            }
        }
    }
}

@Composable
fun ColorOptionCard(imageRes: Int, description: String) {
    Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.size(100.dp)) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = description,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
