package com.assignmentsmobile.assignment_2.data

import kotlinx.serialization.Serializable

@Serializable
data class FilmCollectionsResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<Film>
)

data class Film(
    val kinopoiskId: Int,
    val imdbId: String,
    val nameRu: String,
    val nameEn: String?,
    val nameOriginal: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val ratingKinopoisk: Double,
    val ratingImdb: Double,
    val year: Int,
    val type: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val coverUrl: String,
    val logoUrl: String,
    val description: String,
    val ratingAgeLimits: String,
    val filmLength: Int,
    val shortDescription: String
)

@Serializable
data class Country(
    val country: String
)

@Serializable
data class Genre(
    val genre: String
)


data class Section(
    val sectionName: String,
    val list: List<Film>
)

data class Staff(
    val staffId: Int,
    val nameRu: String,
    val nameEn: String,
    val description: String,
    val posterUrl: String,
    val professionText: String,
    val professionKey: String
)

data class StaffList(
    val actors: List<Staff>,
    val otherStaff: List<Staff>
)

data class FilmImage(
    val imageUrl: String,
    val previewUrl: String,
    val type: String
)

data class FilmImagesList(
    val total: Int,
    val totalPages: Int,
    val items: List<FilmImage>
)

data class SimilarFilm(
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    val nameOriginal: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val relationType: String
)

data class SimilarFilmList(
    val total: Int,
    val items: List<SimilarFilm>
)
