package com.example.myapp.DataClases

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("api/products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<ClothingItem>
}

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://679a-2402-d000-810c-42a6-b534-53f5-4575-65fa.ngrok-free.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ProductApi = retrofit.create(ProductApi::class.java)
}