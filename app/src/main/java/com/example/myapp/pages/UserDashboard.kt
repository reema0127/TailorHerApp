package com.example.myapp.pages

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.myapp.R
import com.example.myapp.getMoreToLoveList
import kotlinx.coroutines.launch

@Composable
fun UserDashboard(modifier: Modifier = Modifier) {
    var isSettingsVisible by remember { mutableStateOf(false) } // State for settings panel visibility

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ProfileHeader(onSettingsClick = { isSettingsVisible = true })
            Spacer(modifier = Modifier.height(16.dp))
            OrderStatusSection()
            Spacer(modifier = Modifier.height(16.dp))
            PaymentSection()
            Spacer(modifier = Modifier.height(16.dp))
            MoreToLoveSection()
        }

        // Animated settings panel
        AnimatedVisibility(
            visible = isSettingsVisible,
            enter = slideInHorizontally(initialOffsetX = { it }),
            exit = slideOutHorizontally(targetOffsetX = { it })
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        onClick = { isSettingsVisible = false },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() } // Avoid ripple effect
                    )
            ) {
                SettingsPanel(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClose = { isSettingsVisible = false }
                )
            }
        }
    }
}

@Composable
fun SettingsPanel(modifier: Modifier = Modifier, onClose: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(Color(0xFFF9F3F4), RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Settings",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF9E7D85))
                )
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Close",
                        tint = Color(0xFF9E7D85)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Currency", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium))
            Spacer(modifier = Modifier.height(8.dp))
            Text("LKR", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light, color = Color.Gray))

            Spacer(modifier = Modifier.height(16.dp))

            Text("Shipping Address", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium))
            Spacer(modifier = Modifier.height(8.dp))

            Text("Profile", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium))
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onClose,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9E7D85))
            ) {
                Text(text = "Sign Out", color = Color.White)
            }
        }
    }
}

@Composable
fun ProfileHeader(onSettingsClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // User details
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Icon",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Username",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium,  color = (MaterialTheme.colorScheme.onPrimary))
            )
        }

        // Right-side icons
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications Icon",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}



@Composable
fun OrderStatusSection() {
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 22.dp)
    ) {
        Text(
            text = "My Order",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame1dark else R.drawable.frame1)
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame2dark else R.drawable.frame2)
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame3dark else R.drawable.frame3)
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame4dark else R.drawable.frame4)
        }
    }
}

@Composable
fun PaymentSection() {
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp)
    ) {
        Text(
            text = "Payments",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame5dark else R.drawable.frame5)
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame6dark else R.drawable.frame6)
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame7dark else R.drawable.frame7)
        }
    }
}

@Composable
fun ImageCard(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .size(85.dp)
            .background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(5.dp))
    )
}

@Composable
fun MoreToLoveSection() {
    val moreToLoveItems = getMoreToLoveList()

    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
    ) {
        // Title with arrow
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "More to Love → ",
                style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold,color = (MaterialTheme.colorScheme.onPrimary)),
                modifier = Modifier.weight(1f)
                    .padding(start = 120.dp)
            )
        }

        // LazyRow for scrolling items
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(moreToLoveItems) { item ->
                ProductCardUser(
                    imageRes = item.imageRes,
                    title = item.title,
                    price = item.price
                )
            }

            // Add "View More" button after the last item
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "View More",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = (MaterialTheme.colorScheme.onPrimary)
                        ),
                        modifier = Modifier
                            .clickable {
                                // Add action for view more here (like navigating to another screen)
                            }
                            .padding(top= 80.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCardUser(imageRes: Int, title: String, price: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .width(160.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(start = 16.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .width(100.dp)

        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = (MaterialTheme.colorScheme.onPrimary)),
            maxLines = 1
        )
        Text(
            text = price,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = (MaterialTheme.colorScheme.onPrimary))
        )

    }
}
