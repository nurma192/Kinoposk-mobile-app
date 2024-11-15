package com.assignmentsmobile.assignment_2.data.repository

import com.assignmentsmobile.assignment_2.data.SimilarFilmList
import com.assignmentsmobile.assignment_2.data.domain.SimilarFilmsApiService

class SimilarFilmsListRepository(private val similarFilmsApiService: SimilarFilmsApiService){
    suspend fun getSimilarFilms(filmId: Int): SimilarFilmList {
        return similarFilmsApiService.getSimilarFilms(filmId)
    }
}