package com.example.myapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.R



@Composable
fun WinterProductGridPage() {
    val products = listOf(
        Product(R.drawable.winter1, "Wool Sweater", "LKR 3,500"),
        Product(R.drawable.winter2, "Winter Jacket", "LKR 7,000"),
        Product(R.drawable.winter3, "Beanie Hat", "LKR 1,200"),
        Product(R.drawable.winter4, "Leather Boots", "LKR 6,800"),
        Product(R.drawable.winter5, "Knitted Scarf", "LKR 2,000"),
        Product(R.drawable.winter6, "Thermal Gloves", "LKR 1,600")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            ProductCardWinter(product)
        }
    }
}

@Composable
fun ProductCardWinter(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(product.name, fontSize = 16.sp)
            Text(product.price, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
        }
    }
}
