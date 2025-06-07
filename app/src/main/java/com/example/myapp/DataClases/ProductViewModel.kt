package com.example.myapp.DataClases

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    var products by mutableStateOf<List<ApiProduct>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                val response = RetrofitClient.productApiService.getProducts()
                if (response.isSuccessful) {
                    products = response.body() ?: emptyList()
                } else {
                    error = "Failed to load products: ${response.code()}"
                }
            } catch (e: Exception) {
                error = "Network error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }


}