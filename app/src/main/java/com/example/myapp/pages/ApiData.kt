//package com.example.myapp.pages
//
//import android.util.Log
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.slideInVertically
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import coil.compose.AsyncImage
//import com.google.gson.Gson
//import com.google.gson.annotations.SerializedName
//import com.google.gson.reflect.TypeToken
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import okhttp3.OkHttpClient
//import okhttp3.Request
//
////data class ClothingItem(
////    val name: String,
////    val price: String,
////    val imageUrl: String,
////    val category: String
////)
//
//data class ClothingItem(
//    @SerializedName("Product_Name") val name: String,
//    @SerializedName("Price") val price: String,
//    @SerializedName("Image") val imageUrl: String,
//    @SerializedName("Category") val category: String
//)
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ClothingCollectionScreen(navController: NavController) {
//    val categories = listOf("All", "Dress", "Top", "Bottom", "Skirt")
//
//    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
//    var isVisible by rememberSaveable { mutableStateOf(false) }
//    var clothingItems by remember { mutableStateOf(listOf<ClothingItem>()) }
//
//    LaunchedEffect(Unit) {
//        clothingItems = loadClothingItemsFromGitHub()
//        delay(200)
//        isVisible = true
//    }
//
//    val filteredItems = if (categories[selectedTabIndex] == "All") {
//        clothingItems
//    } else {
//        clothingItems.filter { it.category == categories[selectedTabIndex] }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Clothing Collection") },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    titleContentColor = MaterialTheme.colorScheme.onPrimary
//                )
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//                .background(MaterialTheme.colorScheme.background)
//        ) {
//            ScrollableTabRow(
//                selectedTabIndex = selectedTabIndex,
//                edgePadding = 0.dp,
//                containerColor = MaterialTheme.colorScheme.surface,
//                indicator = {}
//            ) {
//                categories.forEachIndexed { index, category ->
//                    Tab(
//                        selected = selectedTabIndex == index,
//                        onClick = { selectedTabIndex = index },
//                        selectedContentColor = Color.White,
//                        unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
//                        text = {
//                            Box(
//                                modifier = Modifier
//                                    .background(
//                                        if (selectedTabIndex == index) Color.Black else Color.Transparent,
//                                        shape = RoundedCornerShape(16.dp)
//                                    )
//                                    .padding(horizontal = 6.dp, vertical = 8.dp)
//                            ) {
//                                Text(
//                                    text = category,
//                                    color = if (selectedTabIndex == index) Color.White else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
//                                    fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
//                                )
//                            }
//                        }
//                    )
//                }
//            }
//
//            AnimatedVisibility(
//                visible = isVisible,
//                enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn(animationSpec = tween(500))
//            ) {
//                LazyVerticalGrid(
//                    columns = GridCells.Fixed(2),
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(12.dp),
//                    verticalArrangement = Arrangement.spacedBy(12.dp),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    items(filteredItems) { item ->
//                        ClothingItemCard(item)
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ClothingItemCard(item: ClothingItem) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .aspectRatio(0.75f),
//        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
//    ) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            AsyncImage(
//                model = item.imageUrl,
//                contentDescription = item.name,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
//            Column(
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//                    .background(Color.Black.copy(alpha = 0.6f))
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                Text(
//                    text = item.name,
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp
//                )
//                Text(
//                    text = item.price,
//                    color = Color.White,
//                    fontSize = 12.sp
//                )
//            }
//        }
//    }
//}
//
//suspend fun loadClothingItemsFromGitHub(): List<ClothingItem> {
//    val client = OkHttpClient()
//    val request = Request.Builder()
////        .url("https://raw.githubusercontent.com/LW500RTX/clothing_items.json/refs/heads/main/clothing_items.json?token=GHSAT0AAAAAADD7OZT3RZJGH6NFFWBQOZLO2CDIFSQ")
//        .url("https://9f30-2402-d000-810c-42a6-a9e8-667f-1830-34d3.ngrok-free.app/api/products")
//        .build()
//
//    return withContext(Dispatchers.IO) {
//        try {
//            val response = client.newCall(request).execute()
//            if (response.isSuccessful) {
//                val body = response.body?.string()
//                if (!body.isNullOrEmpty()) {
//                    val type = object : TypeToken<List<ClothingItem>>() {}.type
//                    Gson().fromJson(body, type)
//                } else emptyList()
//            } else {
//                Log.e("GitHubFetch", "Request failed: ${response.message}")
//                emptyList()
//            }
//        } catch (e: Exception) {
//            Log.e("GitHubFetch", "Error fetching JSON", e)
//            emptyList()
//            }
//        }
//}

//package com.example.myapp.pages
//
//import android.util.Log
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.slideInVertically
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
//import androidx.compose.runtime.*
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import coil.compose.AsyncImage
//import com.google.gson.Gson
//import com.google.gson.annotations.SerializedName
//import com.google.gson.reflect.TypeToken
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import okhttp3.OkHttpClient
//import okhttp3.Request
//
//// Update the data class to match your JSON structure
//data class ClothingItem(
//    @SerializedName("Product_Name") val name: String,
//    @SerializedName("Price") val price: String,
//    @SerializedName("Image") val image: String,
//    @SerializedName("Category") val category: String
//) {
//    val fullImageUrl: String
//        get() = "https://30d1-2402-d000-810c-42a6-a9e8-667f-1830-34d3.ngrok-free.app/$image"
//}
//
//@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
//@Composable
//fun ClothingCollectionScreen(navController: NavController) {
//    val categories = listOf("All", "Dress", "Top", "Bottom", "Skirt")
//    val pagerState = rememberPagerState(initialPage = 0, pageCount = { categories.size })
//    val coroutineScope = rememberCoroutineScope()
//
//    var allItems by remember { mutableStateOf(listOf<ClothingItem>()) }
//    var isLoading by remember { mutableStateOf(true) }
//    var error by remember { mutableStateOf<String?>(null) }
//
//    LaunchedEffect(Unit) {
//        try {
//            allItems = loadClothingItems()
//        } catch (e: Exception) {
//            error = "Failed to load items: ${e.message}"
//        } finally {
//            isLoading = false
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Clothing Collection") },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    titleContentColor = MaterialTheme.colorScheme.onPrimary
//                )
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//                .background(MaterialTheme.colorScheme.background)
//        ) {
//            // Tab Row
//            TabRow(
//                selectedTabIndex = pagerState.currentPage,
//                containerColor = MaterialTheme.colorScheme.surface,
//                indicator = { tabPositions ->
//                    TabRowDefaults.Indicator(
//                        modifier = Modifier
//                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
//                            .height(4.dp),
//                        color = MaterialTheme.colorScheme.primary
//                    )
//                }
//            ) {
//                categories.forEachIndexed { index, category ->
//                    Tab(
//                        selected = pagerState.currentPage == index,
//                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
//                        text = {
//                            Text(
//                                text = category,
//                                color = if (pagerState.currentPage == index)
//                                    MaterialTheme.colorScheme.primary
//                                else
//                                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
//                            )
//                        }
//                    )
//                }
//            }
//
//            // Horizontal Pager
//            HorizontalPager(state = pagerState) { page ->
//                when {
//                    isLoading -> {
//                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                            CircularProgressIndicator()
//                        }
//                    }
//                    error != null -> {
//                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                            Text(text = error ?: "Error")
//                        }
//                    }
//                    else -> {
//                        val filteredItems = if (categories[page] == "All") {
//                            allItems
//                        } else {
//                            allItems.filter { it.category.equals(categories[page], ignoreCase = true) }
//                        }
//
//                        if (filteredItems.isEmpty()) {
//                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                                Text("No items found")
//                            }
//                        } else {
//                            LazyVerticalGrid(
//                                columns = GridCells.Fixed(2),
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(12.dp),
//                                verticalArrangement = Arrangement.spacedBy(12.dp),
//                                horizontalArrangement = Arrangement.spacedBy(12.dp)
//                            ) {
//                                items(filteredItems) { item ->
//                                    ClothingItemCard(item)
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun ClothingItemCard(item: ClothingItem) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .aspectRatio(0.75f),
//        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
//    ) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            AsyncImage(
//                model = item.fullImageUrl,
//                contentDescription = item.name,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
//            Column(
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//                    .background(Color.Black.copy(alpha = 0.6f))
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                Text(
//                    text = item.name,
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp
//                )
////                Text(
////                    text = item.price,
////                    color = Color.White,
////                    fontSize = 12.sp
////                )
//                Text(
//                    text = "LKR ${item.price}",
//                    color = Color.White,
//                    fontSize = 12.sp
//                )
//            }
//        }
//    }
//}
//
//// Load clothing items from your ngrok API
//suspend fun loadClothingItems(): List<ClothingItem> {
//    val client = OkHttpClient()
//    val request = Request.Builder()
//        .url("https://30d1-2402-d000-810c-42a6-a9e8-667f-1830-34d3.ngrok-free.app/api/products")
//        .build()
//
//    return withContext(Dispatchers.IO) {
//        try {
//            val response = client.newCall(request).execute()
//            if (response.isSuccessful) {
//                val body = response.body?.string()
//                if (!body.isNullOrEmpty()) {
//                    val type = object : TypeToken<List<ClothingItem>>() {}.type
//                    Gson().fromJson(body, type)
//                } else emptyList()
//            } else {
//                Log.e("API", "Failed: ${response.message}")
//                emptyList()
//            }
//        } catch (e: Exception) {
//            Log.e("API", "Error fetching items", e)
//            emptyList()
//        }
//    }
//}

package com.example.myapp.pages

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.compose.backgroundDark
import com.example.compose.backgroundLight
import com.example.compose.onPrimaryContainerDark
import com.example.compose.onPrimaryContainerLight
import com.example.compose.onPrimaryDark
import com.example.compose.onPrimaryLight
import com.example.compose.onSecondaryDark
import com.example.compose.primaryContainerDark
import com.example.compose.primaryContainerLight
import com.example.compose.primaryDark
import com.example.compose.primaryLight
import com.example.compose.secondaryContainerDark
import com.example.compose.secondaryContainerLight
import com.example.compose.secondaryDark
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ClothingCollectionScreen(navController: NavController) {
    val categories = listOf("All", "Dress", "Top", "Bottom", "Skirt")
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { categories.size })
    val coroutineScope = rememberCoroutineScope()
    val isDarkTheme = isSystemInDarkTheme()

    // Theme colors based on light/dark mode
    val primaryColor = if (isDarkTheme) primaryDark else primaryLight
    val onPrimaryColor = if (isDarkTheme) onPrimaryDark else onPrimaryLight
    val primaryContainerColor = if (isDarkTheme) primaryContainerDark else primaryContainerLight
    val onPrimaryContainerColor = if (isDarkTheme) onPrimaryContainerDark else onPrimaryContainerLight
    val backgroundColor = if (isDarkTheme) backgroundDark else backgroundLight
    val cardColor = if (isDarkTheme) secondaryContainerDark else secondaryContainerLight

    var allItems by remember { mutableStateOf(listOf<ClothingItem>()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            allItems = loadClothingItems()
        } catch (e: Exception) {
            error = "Failed to load items: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Clothing Collection",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = onPrimaryContainerColor
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryContainerColor,
                    titleContentColor = onPrimaryContainerColor
                ),
                modifier = Modifier.shadow(4.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            // Spacing above tabs
            Spacer(modifier = Modifier.height(8.dp))

            // Tab Row with theme colors
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = backgroundColor,
                contentColor = primaryColor,
                edgePadding = 16.dp,
                divider = {},
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .height(3.dp)
                            .background(
                                color = primaryColor,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 16.dp)
                    )
                }
            ) {
                categories.forEachIndexed { index, category ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Medium
                                ),
                                color = if (pagerState.currentPage == index)
                                    primaryColor
                                else
                                    onPrimaryColor.copy(alpha = 0.8f)
                            )
                        }
                    )
                }
            }

            // Spacing between tabs and content
            Spacer(modifier = Modifier.height(8.dp))

            // Horizontal Pager
            HorizontalPager(state = pagerState) { page ->
                when {
                    isLoading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(48.dp),
                                strokeWidth = 4.dp,
                                color = primaryColor
                            )
                        }
                    }
                    error != null -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(
                                text = error ?: "Error",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                    else -> {
                        val filteredItems = if (categories[page] == "All") {
                            allItems
                        } else {
                            allItems.filter { it.category.equals(categories[page], ignoreCase = true) }
                        }

                        if (filteredItems.isEmpty()) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(
                                    "No items found in this category",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = onPrimaryColor.copy(alpha = 0.6f)
                                )
                            }
                        } else {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(filteredItems) { item ->
                                    ClothingItemCard(item, cardColor)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClothingItemCard(item: ClothingItem, containerColor: Color) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.7f),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = item.fullImageUrl,
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 0.6f
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = item.name,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "LKR ${item.price}",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

// Data classes and network functions remain unchanged
data class ClothingItem(
    @SerializedName("Product_Name") val name: String,
    @SerializedName("Price") val price: String,
    @SerializedName("Image") val image: String,
    @SerializedName("Category") val category: String
) {
    val fullImageUrl: String
        get() = "https://30d1-2402-d000-810c-42a6-a9e8-667f-1830-34d3.ngrok-free.app/$image"
}

suspend fun loadClothingItems(): List<ClothingItem> {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://30d1-2402-d000-810c-42a6-a9e8-667f-1830-34d3.ngrok-free.app/api/products")
        .build()

    return withContext(Dispatchers.IO) {
        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val body = response.body?.string()
                if (!body.isNullOrEmpty()) {
                    val type = object : TypeToken<List<ClothingItem>>() {}.type
                    Gson().fromJson(body, type)
                } else emptyList()
            } else {
                Log.e("API", "Failed: ${response.message}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("API", "Error fetching items", e)
            emptyList()
        }
    }
}