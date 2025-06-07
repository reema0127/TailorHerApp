package com.example.myapp.DataClases

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


fun loadAboutUsContent(context: Context): AboutUs{
    val json = context.assets.open("about_us.json")
        .bufferedReader()
        .use { it.readText() }

    return Gson().fromJson(json, AboutUs::class.java)
}

interface AboutUsService {
    @GET("about_us.json")
    suspend fun getAboutUs(): AboutUs
}

fun provideRetrofitJSON(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/reema0127/Tailor_Her_App/refs/heads/main/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

suspend fun loadAboutUsFromNetwork(): AboutUs {
    val retrofit = provideRetrofitJSON()
    val service = retrofit.create(AboutUsService::class.java)
    return service.getAboutUs()
}

//json loader for details screen
suspend fun fetchNewDropsFromRemote(): List<NewDrop>? {
    return try {
        val url = URL("https://yourdomain.com/newdrops.json") // Replace with your URL
        val connection = url.openConnection() as HttpURLConnection
        connection.connectTimeout = 5000
        connection.readTimeout = 5000
        connection.connect()

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val json = connection.inputStream.bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<NewDrop>>() {}.type
            Gson().fromJson<List<NewDrop>>(json, type)
        } else null
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun loadLocalNewDrops(context: Context): List<NewDrop> {
    val inputStream: InputStream = context.assets.open("newdrops_local.json")
    val json = inputStream.bufferedReader().use { it.readText() }
    val type = object : TypeToken<List<NewDrop>>() {}.type
    return Gson().fromJson(json, type)
}

suspend fun loadNewDrops(context: Context): List<NewDrop> {
    return fetchNewDropsFromRemote() ?: loadLocalNewDrops(context)
}

