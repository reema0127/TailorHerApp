//package com.example.myapp.pages
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.example.myapp.R
//import com.example.myapp.DataClases.loadAboutUsContent
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AboutUs(navController: NavHostController) {
//    val context = LocalContext.current
//    val textColor = Color.White
//    val aboutUs = remember { loadAboutUsContent(context) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        aboutUs.title,
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color.Black.copy(alpha = 0.4f)
//                ),
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            Icons.Default.ArrowBack,
//                            contentDescription = "Back",
//                            tint = Color.White
//                        )
//                    }
//                }
//            )
//        }
//    ) { padding ->
//        Box(modifier = Modifier.fillMaxSize()) {
//            // Background Image
//            Image(
//                painter = painterResource(id = R.drawable.aboutusbg),
//                contentDescription = "About Us Background",
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )
//
//            // Gradient overlay
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        Brush.verticalGradient(
//                            colors = listOf(
//                                Color.Black.copy(alpha = 0.3f),
//                                Color.Black.copy(alpha = 0.7f)
//                            ),
//                            startY = 0f,
//                            endY = Float.POSITIVE_INFINITY
//                        )
//                    )
//            )
//
//            // Scrollable Content
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//                    .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
//                    .verticalScroll(rememberScrollState()),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Top
//            ) {
//                Spacer(modifier = Modifier.height(80.dp))
//
//                Text(
//                    text = aboutUs.title,
//                    style = MaterialTheme.typography.headlineLarge.copy(
//                        fontWeight = FontWeight.Bold,
//                        color = textColor
//                    ),
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                Text(
//                    text = aboutUs.description,
//                    style = MaterialTheme.typography.bodyLarge.copy(
//                        color = textColor,
//                        lineHeight = 24.sp
//                    ),
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(32.dp))
//
//                Text(
//                    text = aboutUs.missionTitle,
//                    style = MaterialTheme.typography.titleLarge.copy(
//                        fontWeight = FontWeight.Bold,
//                        color = textColor
//                    ),
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Text(
//                    text = aboutUs.missionText,
//                    style = MaterialTheme.typography.bodyLarge.copy(
//                        color = textColor,
//                        lineHeight = 24.sp
//                    ),
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//        }
//    }
//}
//package com.example.myapp.pages
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.example.myapp.R
//import com.example.myapp.DataClases.AboutUs
//import com.example.myapp.DataClases.AboutUsService
//import com.example.myapp.DataClases.provideRetrofit
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import retrofit2.HttpException
//import java.io.IOException
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AboutUs(navController: NavHostController) {
//    val context = LocalContext.current
//    val textColor = Color.White
//
//    val aboutUsState = produceState<AboutUs?>(initialValue = null) {
//        value = try {
//            withContext(Dispatchers.IO) {
//                val retrofit = provideRetrofit()
//                val service = retrofit.create(AboutUsService::class.java)
//                service.getAboutUs()
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//            null
//        } catch (e: HttpException) {
//            e.printStackTrace()
//            null
//        }
//    }
//
//    val aboutUs = aboutUsState.value
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        aboutUs?.title ?: "About Us",
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color.Black.copy(alpha = 0.4f)
//                ),
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            Icons.Default.ArrowBack,
//                            contentDescription = "Back",
//                            tint = Color.White
//                        )
//                    }
//                }
//            )
//        }
//    ) { padding ->
//
//        if (aboutUs == null) {
//            // Show loading
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                CircularProgressIndicator()
//            }
//        } else {
//            Box(modifier = Modifier.fillMaxSize()) {
//                // Background Image
//                Image(
//                    painter = painterResource(id = R.drawable.aboutusbg),
//                    contentDescription = "About Us Background",
//                    modifier = Modifier.fillMaxSize(),
//                    contentScale = ContentScale.Crop
//                )
//
//                // Gradient overlay
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(
//                            Brush.verticalGradient(
//                                colors = listOf(
//                                    Color.Black.copy(alpha = 0.3f),
//                                    Color.Black.copy(alpha = 0.7f)
//                                ),
//                                startY = 0f,
//                                endY = Float.POSITIVE_INFINITY
//                            )
//                        )
//                )
//
//                // Scrollable Content
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(padding)
//                        .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
//                        .verticalScroll(rememberScrollState()),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Top
//                ) {
//                    Spacer(modifier = Modifier.height(80.dp))
//
//                    Text(
//                        text = aboutUs.title,
//                        style = MaterialTheme.typography.headlineLarge.copy(
//                            fontWeight = FontWeight.Bold,
//                            color = textColor
//                        ),
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    Spacer(modifier = Modifier.height(24.dp))
//
//                    Text(
//                        text = aboutUs.description,
//                        style = MaterialTheme.typography.bodyLarge.copy(
//                            color = textColor,
//                            lineHeight = 24.sp
//                        ),
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    Spacer(modifier = Modifier.height(32.dp))
//
//                    Text(
//                        text = aboutUs.missionTitle,
//                        style = MaterialTheme.typography.titleLarge.copy(
//                            fontWeight = FontWeight.Bold,
//                            color = textColor
//                        ),
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    Text(
//                        text = aboutUs.missionText,
//                        style = MaterialTheme.typography.bodyLarge.copy(
//                            color = textColor,
//                            lineHeight = 24.sp
//                        ),
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                }
//            }
//        }
//    }
//}

package com.example.myapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapp.R
import com.example.myapp.DataClases.AboutUs
import com.example.myapp.DataClases.loadAboutUsContent
import com.example.myapp.DataClases.loadAboutUsFromNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUs(navController: NavHostController) {
    val context = LocalContext.current
    val textColor = Color.White

    val aboutUsState = produceState<AboutUs?>(initialValue = null) {
        value = try {
            // Try network first
            withContext(Dispatchers.IO) {
                loadAboutUsFromNetwork()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            // Fallback to local
            withContext(Dispatchers.IO) {
                loadAboutUsContent(context)
            }
        } catch (e: HttpException) {
            e.printStackTrace()
            withContext(Dispatchers.IO) {
                loadAboutUsContent(context)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.IO) {
                loadAboutUsContent(context)
            }
        }
    }

    val aboutUs = aboutUsState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        aboutUs?.title ?: "About Us",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black.copy(alpha = 0.4f)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->

        if (aboutUs == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.aboutusbg),
                    contentDescription = "About Us Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.3f),
                                    Color.Black.copy(alpha = 0.7f)
                                ),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(80.dp))

                    Text(
                        text = aboutUs.title,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = aboutUs.description,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = textColor,
                            lineHeight = 24.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = aboutUs.missionTitle,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = aboutUs.missionText,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = textColor,
                            lineHeight = 24.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
