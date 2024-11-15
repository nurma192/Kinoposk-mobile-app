package com.assignmentsmobile.assignment_2.data.repository

import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.domain.KinopoiskDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FilmCollectionRepository {
    suspend fun getFilmCollections(type: String, page: Int): List<Film> {
        return fetchFilmCollections(type, page) ?: emptyList()
    }
}

suspend fun fetchFilmCollections(filmCollection: String, page: Int): List<Film>? {
    return withContext(Dispatchers.IO) {
        try {
            val response = KinopoiskDomain.apiCollections.getFilmCollections(filmCollection, page)
            response.items
        } catch (e: Exception) {
            null
        }
    }
}


