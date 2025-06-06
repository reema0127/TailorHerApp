package com.example.myapp

import com.example.myapp.DataClases.MoreToLove
import com.example.myapp.DataClases.NewDrop
import com.example.myapp.DataClases.Product

class DataSource {
    fun loadIvoryDrops(): List<NewDrop> {
        return listOf(
            NewDrop(
                id = "1",
                imageResourceId = R.drawable.white_1,
                titleResourceId = R.string.frosted_charm,
                priceResourceId = R.string.price_4500,
                description = "A beautiful frosted charm dress with intricate lace details.",
                stockLevel = "10 remaining"
            ),
            NewDrop(
                id = "2",
                imageResourceId = R.drawable.white_2,
                titleResourceId = R.string.moonlit_gown,
                priceResourceId = R.string.price_6500,
                description = "A stunning moonlit gown with a delicate silk fabric.",
                stockLevel = "100"
            ),
            NewDrop(
                id = "3",
                imageResourceId = R.drawable.white_3,
                titleResourceId = R.string.luna_glow,
                priceResourceId = R.string.price_4000,
                description = "A glowing luna dress perfect for evening parties.",
                stockLevel = "25"
            ),
            NewDrop(
                id = "4",
                imageResourceId = R.drawable.white_4,
                titleResourceId = R.string.Celestial_whisper,
                priceResourceId = R.string.price_6500,
                description = "A beautiful moonlit gown with a flowing train.",
                stockLevel = "Almost Out of stock"
            ),
            NewDrop(
                id = "5",
                imageResourceId = R.drawable.white_5,
                titleResourceId = R.string.Ethereal_bloom,
                priceResourceId = R.string.price_4000,
                description = "A chic and elegant luna glow dress with a delicate back design.",
                stockLevel = "100"
            ),
            NewDrop(
                id = "6",
                imageResourceId = R.drawable.white_6,
                titleResourceId = R.string.Starlit_grace,
                priceResourceId = R.string.price_4000,
                description = "A chic and elegant luna glow dress with a delicate back design.",
                stockLevel = "100"
            )
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