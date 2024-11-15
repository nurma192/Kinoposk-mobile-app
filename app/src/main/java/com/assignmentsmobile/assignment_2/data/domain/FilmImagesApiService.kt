package com.assignmentsmobile.assignment_2.data.domain

import com.assignmentsmobile.assignment_2.data.FilmImagesList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmImagesApiService {
    @GET("/api/v2.2/films/{id}/images")
    suspend fun getFilmImages(
        @Path("id") filmId: Int,
        @Query("type") type: String,
        @Query("page") page: Int
    ): FilmImagesList
}