package com.assignmentsmobile.assignment_2.data.domain

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KinopoiskDomain {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"
    private const val API_KEY = "3e1e6ade-3ca8-49bf-9325-eccfdaca3abc"
//    91d38748-8b3a-4d64-9e3b-5c71cc0e6b96
//    bebdfd16-562c-44b7-9bc8-975cd979c3a6
//    8676224b-2e1c-446e-953c-dcbba3e78ed8
//    88efea52-b9f2-4bb9-b1d1-1af821253424
//    3e1e6ade-3ca8-49bf-9325-eccfdaca3abc


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
    val filmsSearchApiService: FilmsSearchApiService by lazy {
        retrofit.create(FilmsSearchApiService::class.java)
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
