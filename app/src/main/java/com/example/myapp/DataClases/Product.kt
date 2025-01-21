package com.example.myapp.DataClases

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Product(
    @DrawableRes val imageResourceId: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val priceResourceId: Int
)
