package com.example.myapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.myapp.AuthState
import com.example.myapp.AutheticationViewModel
import com.example.myapp.DataSource
import com.example.myapp.DataClases.NavItem
import com.example.myapp.DataClases.NewDrop
import com.example.myapp.DataClases.Product
import com.example.myapp.R
import com.example.myapp.ui.theme.ivory

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    autheticationViewModel: AutheticationViewModel
) {
    val authState = autheticationViewModel.authState.observeAsState()

    // Redirect unauthenticated users
    LaunchedEffect(authState.value) {
        if (authState.value is AuthState.Unauthenticated) {
            navController.navigate("login")
        }
    }

    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home, 0),
        NavItem("Cart", Icons.Default.ShoppingCart, 3),
        NavItem("Account", Icons.Default.AccountCircle, 0)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }
    val customPink = Color(0xFFF8C9D5)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = customPink) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            BadgedBox(badge = {
                                if (navItem.badgeCount > 0) {
                                    Badge {
                                        Text(text = navItem.badgeCount.toString())
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = navItem.icon,
                                    contentDescription = "${navItem.label} Icon"
                                )
                            }
                        },
                        label = { Text(text = navItem.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex = selectedIndex
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int
) {
    when (selectedIndex) {
        0 -> FashionStoreUI()
        1 -> CartPageUI()
        2 -> UserDashboard()
    }
}

@Composable
fun FashionStoreUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFFFF8F0))
    ) {
        TopBar()
        SaleItems()
        Spacer(modifier = Modifier.height(15.dp))
        IvoryDropSection() // Newly added section
        ProductScreen()
        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
fun TopBar() {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8C9D5))
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
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .height(46.dp)
            )

            // Notification Icon
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification Icon",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }

        // Search Bar with Icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search Icon
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )

            // Text Field
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .width(300.dp),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                singleLine = true
            )
        }
    }
}





@Composable
fun SaleItems() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(275.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        SaleItem(imageRes = R.drawable.dress1, discount = "Sale 20%", price = "LKR 7500")
        SaleItem(imageRes = R.drawable.dress2, discount = "Sale 15%", price = "LKR 8200")
    }
}
@Composable
fun SaleItem(imageRes: Int, discount: String, price: String) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .height(350.dp)
            .background(Color(0xFFF8C9D5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = discount,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
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
                    .background(Color(0xFFF8C9D5), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)


            ) {
                Text(
                    text = price,
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center),

                )
            }
        }
    }
}
@Composable
fun IvoryDropSection() {
    var selectedIvoryDrop by remember { mutableStateOf<NewDrop?>(null) } // Track selected item

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
            // Section Title
            Text(
                text = "IVORY DROP",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = ivory,
                color = Color.Black,
                modifier = Modifier.padding(start = 30.dp, bottom = 5.dp)
            )
            Text(
                text = "TIMELESS BEAUTY",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = ivory,
                color = Color.Black,
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
                        onCardClick = { selectedIvoryDrop = it } // Handle click
                    )
                }
            }
        }
    }


}


@Composable
fun NewDropCard(newDrop: NewDrop, navController: NavController, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(160.dp)
            .padding(horizontal = 4.dp)
            .clickable {
                navController.navigate("detail/${newDrop.id}")
            },
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF8F0))
        ) {// Image
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
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
            // Price
            Text(
                text = stringResource(ivoryDrop.priceResourceId),
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun ProductScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top section with products
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
                imageRes = R.drawable.dress4,
                name = "Green Flowy Dress",
                price = "LKR 8200"
            )
        }


    }
}

@Composable
fun ProductCard(imageRes: Int, name: String, price: String) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .background(Color.White)
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
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Product price
        Text(
            text = price,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}




