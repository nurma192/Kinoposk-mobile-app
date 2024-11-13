package com.assignmentsmobile.assignment_2.data.repository

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.domain.KinopoiskDomain
import com.assignmentsmobile.assignment_2.data.viewmodel.FilmCollectionsViewModel
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

class FilmCollectionsViewModelFactory(
    private val repository: FilmCollectionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmCollectionsViewModel::class.java)) {
            return FilmCollectionsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
