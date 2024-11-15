package com.assignmentsmobile.assignment_2.data.repository

import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.domain.FilmApiService
import retrofit2.Response

class FilmRepository(private val filmApiService: FilmApiService) {
    suspend fun getFilmById(filmId: Int): Response<Film> {
        return filmApiService.getFilmById(filmId)
    }
}
