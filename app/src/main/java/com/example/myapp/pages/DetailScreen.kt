package com.example.myapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.DataSource
import com.example.myapp.R

@Composable
fun DetailScreen(modifier: Modifier = Modifier, itemId: String, navController: NavController) {
    val item = DataSource().loadIvoryDrops().find { it.id == itemId }

    item?.let {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = item.imageResourceId),
                    contentDescription = "Item Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                    Row {
                        IconButton(onClick = { /* Handle search */ }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        }
                        IconButton(onClick = { /* Handle share */ }) {
                            Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = stringResource(id = item.titleResourceId),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF947B7A)
                )
                Text(
                    text = stringResource(id = item.priceResourceId),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF947B7A)
                )
                Text(
                    text = "Availability: ${item.stockLevel}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF947B7A),
                    modifier = Modifier.padding(top = 8.dp),
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Size buttons (Two lines, wrapped content)
                Text(
                    text = "Select Size:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF947B7A),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp) // Space between rows
                ) {
                    // First row of buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly // Space buttons evenly
                    ) {
                        Button(
                            onClick = { /* Handle Small Size */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8C9D5)),
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(horizontal = 4.dp)
                        ) {
                            Text("Small", fontSize = 18.sp, color = Color.White)
                        }

                        Button(
                            onClick = { /* Handle Medium Size */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8C9D5)),
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(horizontal = 4.dp)
                        ) {
                            Text("Medium", fontSize = 18.sp, color = Color.White)
                        }
                    }

                    // Second row of buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly // Space buttons evenly
                    ) {
                        Button(
                            onClick = { /* Handle Large Size */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8C9D5)),
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(horizontal = 4.dp)
                        ) {
                            Text("Large", fontSize = 18.sp, color = Color.White)
                        }

                        Button(
                            onClick = { /* Handle Custom Size */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8C9D5)),
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(horizontal = 4.dp)
                        ) {
                            Text("Custom", fontSize = 18.sp, color = Color.White)
                        }
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* Handle buy now */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8C9D5)),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                            .height(50.dp)
                    ) {
                        Text("Buy now", fontSize = 20.sp)
                    }

                    Button(
                        onClick = { /* Handle add to cart */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8C9D5)),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text("Add to cart", fontSize = 20.sp)
                    }
                }
            }
        }
    } ?: run {
        Text(text = "Item not found!", modifier = Modifier.padding(16.dp))
    }
}
