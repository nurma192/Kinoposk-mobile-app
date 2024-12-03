package com.assignmentsmobile.assignment_2.data.domain

import com.assignmentsmobile.assignment_2.data.SearchedFilms
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsSearchApiService {
    @GET("/api/v2.2/films/search-by-keyword/")
    suspend fun getFilmByKeyword(
        @Query("keyword") keyword: String,
        @Query("page") page: Int
    ): Response<SearchedFilms>
}