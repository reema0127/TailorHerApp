package com.example.myapp

import com.example.myapp.DataClases.MoreToLove
import com.example.myapp.DataClases.NewDrop
import com.example.myapp.DataClases.Product

class DataSource {
    fun loadNewDrops(): List<NewDrop> {
        return listOf(
            NewDrop(R.drawable.white_1, R.string.frosted_charm, R.string.price_4500),
            NewDrop(R.drawable.white_2, R.string.moonlit_gown, R.string.price_6500),
            NewDrop(R.drawable.white_3, R.string.luna_glow, R.string.price_4000),
            NewDrop(R.drawable.white_4, R.string.moonlit_gown, R.string.price_6500),
            NewDrop(R.drawable.white_5, R.string.luna_glow, R.string.price_4000)
        )
    }

    fun loadProduct(): List<Product> {
        return listOf(
            Product(R.drawable.product1, R.string.frosted_charm, R.string.price_4500),
            Product(R.drawable.product2, R.string.moonlit_gown, R.string.price_6500),
            Product(R.drawable.product3, R.string.luna_glow, R.string.price_4000),
            Product(R.drawable.product4, R.string.moonlit_gown, R.string.price_6500),

        )
    }
}

fun getMoreToLoveList(): List<MoreToLove> {
    return listOf(
        MoreToLove(R.drawable.moretolove1, "Petal Bliss", "LKR 4000"),
        MoreToLove(R.drawable.moretolove2, "Pink Perfection", "LKR 5000"),
        MoreToLove(R.drawable.moretolove3, "Crimson Bloom", "LKR 4500"),
        MoreToLove(R.drawable.moretolove4, "Petal Bliss", "LKR 4000"),
        MoreToLove(R.drawable.moretolove5, "Pink Perfection", "LKR 5000"),
        MoreToLove(R.drawable.skirt2, "Crimson Bloom", "LKR 4500"),


    )
}