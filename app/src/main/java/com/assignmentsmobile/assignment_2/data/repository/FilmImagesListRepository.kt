package com.assignmentsmobile.assignment_2.data.repository


import com.assignmentsmobile.assignment_2.data.FilmImagesList
import com.assignmentsmobile.assignment_2.data.domain.FilmImagesApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmImagesListRepository(private val apiService: FilmImagesApiService){
    suspend fun getFilmImages(filmId: Int, type: String, page: Int): Result<FilmImagesList>{
        return withContext(Dispatchers.IO){
            try {
                val response = apiService.getFilmImages(filmId, type, page)
                Result.success(response)
            }catch (e: Exception){
                Result.failure(e)
            }
        }
    }
}
