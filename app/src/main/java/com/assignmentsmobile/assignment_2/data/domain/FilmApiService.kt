package com.assignmentsmobile.assignment_2.data.domain

import com.assignmentsmobile.assignment_2.data.Film
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmApiService {
    @GET("/api/v2.2/films/{id}")
    suspend fun getFilmById(@Path("id") id: Int): Response<Film>
}