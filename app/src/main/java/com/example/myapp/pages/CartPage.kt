package com.example.myapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.DataClases.CartItems
import com.example.myapp.R

@Composable
fun CartPageUI(modifier: Modifier = Modifier) {
    var selectedItems by remember { mutableStateOf(listOf<CartItems>()) }
    val cartItems = listOf(
        CartItems(
            imageRes = R.drawable.cart1,
            title = "Petal Bliss",
            description = "A floral corset top with a sweet heart neckline, pastel blooms, lace-up back.",
            size = "Size / C",
            price = "LKR 4000"
        ),
        CartItems(
            imageRes = R.drawable.cart2,
            title = "Elegant Dress",
            description = "An elegant evening dress with a sleek design and flowing silhouette.",
            size = "Size / M",
            price = "LKR 5500"
        ),
        CartItems(
            imageRes = R.drawable.cart3,
            title = "Elegant Dress",
            description = "An elegant evening dress with a sleek design and flowing silhouette.",
            size = "Size / M",
            price = "LKR 5500"
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 80.dp) // Add space to accommodate the CartFooter
    ) {
        TopBarCart()

        // Scrollable content for the cart items
        Box(
            modifier = Modifier
                .weight(1f) // Take the remaining space above the footer
                .verticalScroll(rememberScrollState()) // Allow scrolling for items
                .background(MaterialTheme.colorScheme.primary)
        ) {
            CartItems(
                items = cartItems,
                selectedItems = selectedItems,
                onItemSelected = { selectedItems = it }
            )
        }

        // Fixed Cart Footer at the bottom
        CartFooter(
            cartItems = cartItems,
            selectedItems = selectedItems,
            onSelectAll = { selectAll ->
                selectedItems = if (selectAll) cartItems else emptyList()
            },
            onCheckout = { /* Handle checkout */ }
        )
    }
}


@Composable
fun TopBarCart() {
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
                modifier = Modifier
                    .height(46.dp)
            )

            // Notification Icon
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification Icon",
                tint = textColor,
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
fun CartItems(
    items: List<CartItems>,
    selectedItems: List<CartItems>,
    onItemSelected: (List<CartItems>) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items.forEach { item ->
            CartItemCard(item, selectedItems.contains(item)) { isSelected ->
                val updatedSelection = if (isSelected) {
                    selectedItems + item
                } else {
                    selectedItems - item
                }
                onItemSelected(updatedSelection)
            }
        }
    }
}


@Composable
fun CartItemCard(
    item: CartItems,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    var quantity by remember { mutableStateOf(1) }

    // Calculate the total price based on quantity
    val totalPrice = item.price.replace("LKR ", "").replace(",", "").toInt() * quantity

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background( MaterialTheme.colorScheme.tertiary, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {

            // Checkbox with reduced padding
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange
//                modifier = Modifier
//                    .padding(end = 1.dp) // Adjust padding to reduce gap
            )

            // Image next to the checkbox
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(160.dp) // Adjusted size for better layout
            )

            // Content on the right
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween // Space out the content
            ) {
                // Top section with text
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = item.title, fontSize = 14.sp,     color = (MaterialTheme.colorScheme.onPrimaryContainer))
                    Text(text = item.description, fontSize = 12.sp,     color = (MaterialTheme.colorScheme.onPrimaryContainer))
                    Button(
                        onClick = { /* Handle size selection */ },
                        modifier = Modifier.padding(top = 4.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Text(text = item.size, fontSize = 12.sp,color = (MaterialTheme.colorScheme.onPrimary))
                    }
                    Text(text = "LKR ${String.format("%,d", totalPrice)}", fontSize = 14.sp, color = (MaterialTheme.colorScheme.onPrimary))
                }

                // Bottom section with quantity buttons
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End, // Align to the end
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    TextButton(
                        onClick = { if (quantity > 1) quantity-- },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Text(text = "-", fontSize = 24.sp, color = (MaterialTheme.colorScheme.onPrimary))
                    }
                    Text(
                        text = "$quantity",
                        fontSize = 16.sp,
                        color = (MaterialTheme.colorScheme.onPrimary),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TextButton(
                        onClick = { quantity++ },

                        modifier = Modifier.size(36.dp)
                    ) {
                        Text(text = "+", fontSize = 24.sp, color = (MaterialTheme.colorScheme.onPrimary))
                    }
                }
            }
        }
    }
}



@Composable
fun CartFooter(
    cartItems: List<CartItems>,
    selectedItems: List<CartItems>,
    onSelectAll: (Boolean) -> Unit,
    onCheckout: () -> Unit
) {
    val isAllSelected = selectedItems.size == cartItems.size
    val totalPrice = selectedItems.sumOf { it.price.replace("LKR ", "").replace(",", "").toInt() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isAllSelected,
                onCheckedChange = { onSelectAll(it) },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "All", fontSize = 16.sp,  color = (MaterialTheme.colorScheme.onPrimary))
        }
        Text(text = "LKR ${String.format("%,d", totalPrice)}", fontSize = 16.sp,  color = (MaterialTheme.colorScheme.onPrimary))

        Button(
            onClick = { /* Handle size selection */ },
            modifier = Modifier.padding(top = 4.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(
                text = "Checkout (${selectedItems.size})",
                fontSize = 16.sp,
                color = (MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}
