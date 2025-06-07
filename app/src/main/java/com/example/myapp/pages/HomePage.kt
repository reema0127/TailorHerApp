    package com.example.myapp.pages

    import android.content.Intent
    import android.content.res.Configuration
    import android.net.Uri
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.isSystemInDarkTheme
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.LazyRow
    import androidx.compose.foundation.lazy.items
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.text.BasicTextField
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.AccountCircle
    import androidx.compose.material.icons.filled.AddCircle
    import androidx.compose.material.icons.filled.Edit
    import androidx.compose.material.icons.filled.Home
    import androidx.compose.material.icons.filled.Info
    import androidx.compose.material.icons.filled.Notifications
    import androidx.compose.material.icons.filled.Search
    import androidx.compose.material.icons.filled.ShoppingCart
    import androidx.compose.material3.*
    import androidx.compose.runtime.*
    import androidx.compose.runtime.livedata.observeAsState
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.shadow
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.graphics.RectangleShape
    import androidx.compose.ui.platform.LocalConfiguration
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.text.style.TextOverflow
    import androidx.compose.ui.window.Dialog
    import androidx.navigation.NavController
    import com.example.compose.primaryDark
    import com.example.compose.primaryLight
    import com.example.myapp.AuthState
    import com.example.myapp.AutheticationViewModel
    import com.example.myapp.DataSource
    import com.example.myapp.DataClases.NavItem
    import com.example.myapp.DataClases.NewDrop
    import com.example.myapp.DataClases.Product
    import com.example.myapp.R
    import com.example.myapp.ui.theme.instrumentsans
    import com.example.myapp.ui.theme.ivory


    @Composable
    fun HomePage(
        modifier: Modifier = Modifier,
        navController: NavController,
        autheticationViewModel: AutheticationViewModel
    ) {
        val authState = autheticationViewModel.authState.observeAsState()

        LaunchedEffect(authState.value) {
            if (authState.value is AuthState.Unauthenticated) {
                navController.navigate("login")
            }
        }

        val navItemList = listOf(
            NavItem("Home", Icons.Default.Home, 0),
            NavItem("Cart", Icons.Default.ShoppingCart, 3),
            NavItem("Collection", Icons.Filled.AddCircle, 0),
            NavItem("Account", Icons.Default.AccountCircle, 0)
        )

        val isConnected = rememberNetworkConnectivity()
        var selectedIndex by remember { mutableStateOf(0) }

        val context = navController.context
        if (!isConnected) {
            NoInternetBanner()
        }

        Scaffold(
            modifier = modifier.fillMaxSize(),
            bottomBar = {
                Box {
                    // Bottom Navigation Bar
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                    ) {
                        navItemList.forEachIndexed { index, navItem ->
                            // Insert extra spacing before and after the central item (index 1 and 2)
                            if (index == 2) {
                                Spacer(modifier = Modifier.width(50.dp)) // Adds space between Cart and Collection
                            }

                            val contentColor = MaterialTheme.colorScheme.onPrimaryContainer

                            NavigationBarItem(
                                selected = selectedIndex == index,
                                onClick = { selectedIndex = index },
                                icon = {
                                    BadgedBox(badge = {
                                        if (navItem.badgeCount > 0) {
                                            Badge(
                                                containerColor = contentColor,
                                                contentColor = MaterialTheme.colorScheme.primaryContainer
                                            ) {
                                                Text(navItem.badgeCount.toString())
                                            }
                                        }
                                    }) {
                                        Icon(
                                            imageVector = navItem.icon,
                                            contentDescription = navItem.label,
                                            tint = contentColor
                                        )
                                    }
                                },
                                label = {
                                    Text(navItem.label, color = contentColor)
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = contentColor,
                                    selectedTextColor = contentColor,
                                    unselectedIconColor = contentColor.copy(alpha = 0.7f),
                                    unselectedTextColor = contentColor.copy(alpha = 0.7f),
                                )
                            )
                        }
                    }

                    // Floating Call Button in Center with Shadow
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 35.dp) // Lift above the nav bar
                            .align(Alignment.BottomCenter),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:0771234567")
                                }
                                context.startActivity(intent)
                            },
                            modifier = Modifier
                                .size(64.dp)
                                .shadow(8.dp, shape = RoundedCornerShape(50)) // Shadow
                                .background(
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    shape = RoundedCornerShape(50)
                                )
                        ) {
                            val isDarkTheme = isSystemInDarkTheme()
                            val callIcon = if (isDarkTheme) {
                                painterResource(id = R.drawable.calldark) // Dark mode icon
                            } else {
                                painterResource(id = R.drawable.call) // Light mode icon
                            }
                            Image(
                                painter = callIcon,
                                contentDescription = "Call",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            ContentScreen(
                modifier = Modifier.padding(innerPadding),
                selectedIndex = selectedIndex,
                navController = navController,
                autheticationViewModel = autheticationViewModel
            )
        }
    }



    @Composable
    fun ContentScreen(
        modifier: Modifier = Modifier,
        selectedIndex: Int,
        navController: NavController,
        autheticationViewModel: AutheticationViewModel
    ) {
        when (selectedIndex) {
            0 -> FashionStoreUI(navController)
            1 -> CartPageUI(modifier,navController)
            2 -> ClothingCollectionScreen(navController)
            3 -> UserDashboard(modifier,navController, autheticationViewModel)
        }
    }

@Composable
    fun FashionStoreUI(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.primary)
        ) {
            TopBar(navController)
            SaleItems()
            Spacer(modifier = Modifier.height(15.dp))
            IvoryDropSection(navController)
            ProductScreen()
            Spacer(modifier = Modifier.height(60.dp))
        }
    }


    @Composable
    fun TopBar(navController: NavController) {
        var searchText by remember { mutableStateOf("") }

        val isDarkTheme = isSystemInDarkTheme()
        val backgroundColor = MaterialTheme.colorScheme.primaryContainer
        val textColor = MaterialTheme.colorScheme.onPrimaryContainer
        val logoRes = if (isSystemInDarkTheme()) R.drawable.logodark else R.drawable.logolight

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(horizontal = 8.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo Image
                Image(
                    painter = painterResource(id = logoRes),
                    contentDescription = "Logo",
                    modifier = Modifier.height(46.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Info Icon Button
                    IconButton(onClick = { navController.navigate("about_us") }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = textColor
                        )
                    }

                    // Notification Icon
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notification Icon",
                        tint = textColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Search Bar with Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )

                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier.width(300.dp),
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                    singleLine = true
                )
            }
        }
    }



    @Composable
    fun SaleItems() {
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(275.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SaleItem(imageRes = R.drawable.dress6, discount = "Sale 25%", price = "LKR 9500")
            SaleItem(imageRes = R.drawable.dress1, discount = "Sale 20%", price = "LKR 7500")

            // Show the last two items only in landscape mode
            if (isLandscape) {
                SaleItem(imageRes = R.drawable.dress2, discount = "Sale 15%", price = "LKR 8200")
                SaleItem(imageRes = R.drawable.dress7, discount = "Sale 10%", price = "LKR 8500")
            }
        }
    }


    @Composable
    fun SaleItem(imageRes: Int, discount: String, price: String) {
        Column(
            modifier = Modifier
                .width(160.dp)
                .height(350.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = discount,
                modifier = Modifier.padding(top = 8.dp),
                fontFamily = instrumentsans,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = (MaterialTheme.colorScheme.onPrimaryContainer),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(275.dp)
                    .padding(8.dp)

            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Dress Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )


                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .wrapContentWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)


                ) {
                    Text(
                        text = price,
                        color = (MaterialTheme.colorScheme.onPrimaryContainer),
                        fontFamily = instrumentsans,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center),

                    )
                }
            }
        }
    }


    @Composable
    fun IvoryDropSection(navController: NavController) {
        var selectedIvoryDrop by remember { mutableStateOf<NewDrop?>(null) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp, vertical = 10.dp)
            ) {

                Text(
                    text = "NEW DROP",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = ivory,
                    color = (MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier.padding(start = 30.dp, bottom = 5.dp)
                )
                Text(
                    text = "IVORY DROP",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = ivory,

                    color = (MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier.padding(start = 30.dp, bottom = 16.dp)
                )

                // LazyRow displaying Ivory Drop items
                LazyRow(
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 8.dp)
                ) {
                    val ivoryDrops = DataSource().loadIvoryDrops()
                    items(ivoryDrops) { ivoryDrop ->
                        IvoryDropCard(
                            ivoryDrop = ivoryDrop,
                            navController = navController // Pass the NavController
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun IvoryDropCard(ivoryDrop: NewDrop, navController: NavController, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier
                .width(160.dp)
                .padding(horizontal = 4.dp)
                .clickable {
                    // Pass the entire NewDrop object to the DetailScreen
                    navController.navigate("detail/${ivoryDrop.id}")
                },
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                // Image
                Image(
                    painter = painterResource(ivoryDrop.imageResourceId),
                    contentDescription = stringResource(ivoryDrop.titleResourceId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(150.dp)
                        .padding(10.dp)
                )
                // Title
                Text(
                    text = stringResource(ivoryDrop.titleResourceId),
                    fontSize = 16.sp,
                    color = (MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
                // Price
                Text(
                    text = stringResource(ivoryDrop.priceResourceId),
                    fontSize = 14.sp,
                    color = (MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
            }
        }
    }


    @Composable
    fun ProductScreen() {
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (isLandscape) {
            // Use a Row in landscape mode to place all products side by side
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ProductCard(
                    imageRes = R.drawable.dress3,
                    name = "Elegant Beige Dress",
                    price = "LKR 7500"
                )
                ProductCard(
                    imageRes = R.drawable.top3,
                    name = "Sunrise Top",
                    price = "LKR 3200"
                )
                ProductCard(
                    imageRes = R.drawable.top2,
                    name = "Floral Puffy Top",
                    price = "LKR 3500"
                )
                ProductCard(
                    imageRes = R.drawable.dress4,
                    name = "Green Flowy Dress",
                    price = "LKR 8200"
                )
            }
        } else {
            // Use a Column with two Rows in portrait mode
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
                    ProductCard(
                        imageRes = R.drawable.dress3,
                        name = "Elegant Beige Dress",
                        price = "LKR 7500"
                    )
                    ProductCard(
                        imageRes = R.drawable.top3,
                        name = "Sunrise Top",
                        price = "LKR 3200"
                    )
                }
            }
        }
    }

    @Composable
    fun ProductCard(imageRes: Int, name: String, price: String) {

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




