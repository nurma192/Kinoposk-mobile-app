package com.assignmentsmobile.assignment_2.data.domain

import com.assignmentsmobile.assignment_2.data.FilmCollectionsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface KinopoiskCollections {
    @GET("api/v2.2/films/collections")
    suspend fun getFilmCollections(
        @Query("type") type: String,
        @Query("page") page: Int,
    ): FilmCollectionsResponse
}

