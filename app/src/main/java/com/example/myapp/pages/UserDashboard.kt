package com.example.myapp.pages

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.myapp.AuthState
import com.example.myapp.AutheticationViewModel
import com.example.myapp.R
import com.example.myapp.getMoreToLoveList
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.platform.LocalContext
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext


//@Composable
//fun UserDashboard(
//    modifier: Modifier = Modifier,
//    navController: NavController,
//    autheticationViewModel: AutheticationViewModel
//) {
//    var isSettingsVisible by remember { mutableStateOf(false) }
//    var isProfileVisible by remember { mutableStateOf(false) }
//
//    val scrollState = rememberScrollState()
//    val authState = autheticationViewModel.authState.observeAsState()
//
//    LaunchedEffect(authState.value) {
//        if (authState.value is AuthState.Unauthenticated) {
//            navController.navigate("login")
//        }
//    }
//
//    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.primary)
//    ) {
//        // Main content with blur effect when profile is visible
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .then(
//                    if (isProfileVisible) {
//                        Modifier
//                            .blur(8.dp)
//                            .drawWithContent {
//                                drawContent()
//                                drawRect(Color.Black.copy(alpha = 0.3f))
//                            }
//                    } else {
//                        Modifier
//                    }
//                )
//                .verticalScroll(scrollState)
//                .padding(bottom = 16.dp)
//        ) {
//            ProfileHeader(
//                onSettingsClick = { isSettingsVisible = true },
//                onProfileClick = { isProfileVisible = true }
//            )
//
//            MailMeSection() // ← Add this here
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            if (isLandscape) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp),
//                    horizontalArrangement = Arrangement.spacedBy(10.dp)
//                ) {
//                    OrderStatusSection(modifier = Modifier.weight(1f))
//                    PaymentSection(modifier = Modifier.weight(1f))
//                }
//            } else {
//                OrderStatusSection()
//                Spacer(modifier = Modifier.height(10.dp))
//                PaymentSection()
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            MoreToLoveSection()
//            Spacer(modifier = Modifier.height(16.dp))
//            Product()
//            Spacer(modifier = Modifier.height(60.dp))
//        }
//
//        // Settings Slide-In Panel (unchanged)
//        AnimatedVisibility(
//            visible = isSettingsVisible,
//            enter = slideInHorizontally(initialOffsetX = { it }),
//            exit = slideOutHorizontally(targetOffsetX = { it })
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clickable(
//                        onClick = { isSettingsVisible = false },
//                        indication = null,
//                        interactionSource = remember { MutableInteractionSource() }
//                    )
//            ) {
//                SettingsPanel(
//                    modifier = Modifier.align(Alignment.TopEnd),
//                    onClose = { isSettingsVisible = false },
//                    autheticationViewModel = autheticationViewModel
//                )
//            }
//        }
//
//        // Profile Modal Overlay
//        AnimatedVisibility(
//            visible = isProfileVisible,
//            enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
//            exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Black.copy(alpha = 0.3f)) // Semi-transparent overlay
//                    .clickable(
//                        onClick = { isProfileVisible = false },
//                        indication = null,
//                        interactionSource = remember { MutableInteractionSource() }
//                    )
//            ) {
//                Surface(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(24.dp)
//                        .align(Alignment.Center),
//                    shape = RoundedCornerShape(16.dp),
//                    tonalElevation = 8.dp
//                ) {
//                    ProfilePage(onDismiss = { isProfileVisible = false })
//                }
//            }
//        }
//    }
//}
@Composable
fun UserDashboard(
    modifier: Modifier = Modifier,
    navController: NavController,
    autheticationViewModel: AutheticationViewModel
) {
    var isSettingsVisible by remember { mutableStateOf(false) }
    var isProfileVisible by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val authState = autheticationViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        if (authState.value is AuthState.Unauthenticated) {
            navController.navigate("login")
        }
    }

    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val isConnected = rememberNetworkConnectivity()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column {
            // Show persistent No Internet bar at the top
            if (!isConnected) {
                NoInternetBanner()
            }

            // Main content with blur if profile modal is open
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        if (isProfileVisible) {
                            Modifier
                                .blur(8.dp)
                                .drawWithContent {
                                    drawContent()
                                    drawRect(Color.Black.copy(alpha = 0.3f))
                                }
                        } else {
                            Modifier
                        }
                    )
                    .verticalScroll(scrollState)
                    .padding(bottom = 16.dp)
            ) {
                ProfileHeader(
                    onSettingsClick = { isSettingsVisible = true },
                    onProfileClick = { isProfileVisible = true }
                )

                MailMeSection()
                Spacer(modifier = Modifier.height(16.dp))

                if (isLandscape) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OrderStatusSection(modifier = Modifier.weight(1f))
                        PaymentSection(modifier = Modifier.weight(1f))
                    }
                } else {
                    OrderStatusSection()
                    Spacer(modifier = Modifier.height(10.dp))
                    PaymentSection()
                }

                Spacer(modifier = Modifier.height(16.dp))
                MoreToLoveSection()
                Spacer(modifier = Modifier.height(16.dp))
                Product()
                Spacer(modifier = Modifier.height(60.dp))
            }
        }

        // Settings Panel
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
                        interactionSource = remember { MutableInteractionSource() }
                    )
            ) {
                SettingsPanel(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClose = { isSettingsVisible = false },
                    autheticationViewModel = autheticationViewModel
                )
            }
        }

        // Profile Modal Overlay
        AnimatedVisibility(
            visible = isProfileVisible,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .clickable(
                        onClick = { isProfileVisible = false },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(16.dp),
                    tonalElevation = 8.dp
                ) {
                    ProfilePage(onDismiss = { isProfileVisible = false })
                }
            }
        }
    }
}


@Composable
fun MailMeSection() {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Circle Icon Button
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = CircleShape
                )
                .clickable {
                    val emailIntent = Intent(
                        Intent.ACTION_SENDTO,
                        Uri.parse("mailto:support@example.com?subject=Suggestion")
                    )
                    emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(emailIntent)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = if (isDarkTheme) R.drawable.maillight else R.drawable.maillight
                ),
                contentDescription = "Mail Icon",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Text
        Text(
            text = "Drop your suggestions via mail",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}


@Composable
fun OrderStatusSection(modifier: Modifier = Modifier) {
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame1dark else R.drawable.frame1)
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame2dark else R.drawable.frame2)
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame3dark else R.drawable.frame3)
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame4dark else R.drawable.frame4)
        }
    }
}

@Composable
fun PaymentSection(modifier: Modifier = Modifier) {
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame5dark else R.drawable.frame5)
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame6dark else R.drawable.frame6)
            ImageCard(imageRes = if (isDarkTheme) R.drawable.frame7dark else R.drawable.frame7)
        }
    }
}

@Composable
fun SettingsPanel(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    autheticationViewModel: AutheticationViewModel
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
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
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
                )
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Currency", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onPrimary))
            Spacer(modifier = Modifier.height(8.dp))
            Text("LKR", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light, color = MaterialTheme.colorScheme.onPrimary))

            Spacer(modifier = Modifier.height(16.dp))

            Text("Shipping Address", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onPrimary))
            Spacer(modifier = Modifier.height(8.dp))

            Text("Profile", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onPrimary))
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    autheticationViewModel.signout() // Call the sign-out function
                    onClose()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Text(text = "Sign Out", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Composable
fun ProfileHeader(
    onSettingsClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = onProfileClick)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Icon",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Username",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onPrimary)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
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
                    .padding(start = 20.dp)
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

@Composable
fun Product() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ProductCarddashboard(
                imageRes = R.drawable.top1,
                name = "Beige Babydoll top",
                price = "LKR 7500"
            )
            ProductCarddashboard(
                imageRes = R.drawable.dress5,
                name = "Floral Dress",
                price = "LKR 8200"
            )
            ProductCarddashboard(
                imageRes = R.drawable.set1,
                name = "Floral Set",
                price = "LKR 8200"
            )
            ProductCarddashboard(
                imageRes = R.drawable.top3,
                name = "Chiffon Ruffle Top",
                price = "LKR 6500"
            )
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ProductCarddashboard(
                    imageRes = R.drawable.top1,
                    name = "Beige Babydoll top",
                    price = "LKR 7500"
                )
                ProductCarddashboard(
                    imageRes = R.drawable.dress5,
                    name = "Floral Dress",
                    price = "LKR 8200"
                )
            }
        }
    }
}

@Composable
fun ProductCarddashboard(imageRes: Int, name: String, price: String) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Product name
        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = (MaterialTheme.colorScheme.onPrimary),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Product price
        Text(
            text = price,
            fontSize = 14.sp,
            color = (MaterialTheme.colorScheme.onPrimary),
        )
    }
}

