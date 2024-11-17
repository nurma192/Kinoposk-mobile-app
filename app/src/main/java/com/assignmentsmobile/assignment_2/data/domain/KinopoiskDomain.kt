package com.assignmentsmobile.assignment_2.data.domain

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KinopoiskDomain {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"
    private const val API_KEY = "91d38748-8b3a-4d64-9e3b-5c71cc0e6b96"


    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(API_KEY))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiCollections: KinopoiskCollections by lazy {
        retrofit.create(KinopoiskCollections::class.java)
    }

    val filmApiService: FilmApiService by lazy {
        retrofit.create(FilmApiService::class.java)
    }

    val staffApiService: StaffApiService by lazy {
        retrofit.create(StaffApiService::class.java)
    }

    val actorApiService: ActorApiService by lazy {
        retrofit.create(ActorApiService::class.java)
    }


    val filmImagesApiService: FilmImagesApiService by lazy {
        retrofit.create(FilmImagesApiService::class.java)
    }

    val similarFilmsApiService: SimilarFilmsApiService by lazy {
        retrofit.create(SimilarFilmsApiService::class.java)
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
