package com.assignmentsmobile.assignment_2.data.domain

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KinopoiskDomain {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"
    private const val API_KEY = "7255ac58-4f72-408a-a8e0-b30279d32851"

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val apiCollections: KinopoiskCollections by lazy {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(API_KEY))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(KinopoiskCollections::class.java)
    }
}

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()
        val requestWithApiKey = originalRequest.newBuilder()
            .header("X-API-KEY", apiKey)
            .header("Content-Type", "application/json")
            .build()

        return chain.proceed(requestWithApiKey)
    }
}
