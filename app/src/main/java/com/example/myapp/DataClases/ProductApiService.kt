package com.example.myapp.DataClases

import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Response<List<ApiProduct>>
}

data class ApiProduct(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val size: String?,
    val color: String?,
    val image_url: String
)
