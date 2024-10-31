package com.assignmentsmobile.assignment_2.data

import kotlinx.serialization.Serializable

@Serializable
data class FilmCollectionsResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<Film>
)

@Serializable
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
    val ratingAgeLimits: String
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