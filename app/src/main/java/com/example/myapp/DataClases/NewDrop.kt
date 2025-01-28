package com.example.myapp.DataClases

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NewDrop(
    val id: String,
    @DrawableRes val imageResourceId: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val priceResourceId: Int,
    val description: String,
    val stockLevel: String
)
