package com.example.tfg.Retrofit


import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(Url.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }

}