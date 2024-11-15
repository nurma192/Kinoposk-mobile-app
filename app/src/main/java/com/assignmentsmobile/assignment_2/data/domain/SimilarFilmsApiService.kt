package com.assignmentsmobile.assignment_2.data.domain

import com.assignmentsmobile.assignment_2.data.SimilarFilmList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimilarFilmsApiService {
    @GET("/api/v2.2/films/{id}/similars")
    suspend fun getSimilarFilms(@Path("id") filmId: Int): SimilarFilmList
}