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
    val shortDescription: String,
    val professionKey: String?
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
    var nameRu: String,
    var nameEn: String,
    var nameOriginal: String,
    var posterUrl: String,
    var posterUrlPreview: String,
    val relationType: String
)

data class SimilarFilmList(
    val total: Int,
    val items: List<SimilarFilm>
)

data class Actor (
    val personId: Int,
    val nameRu: String,
    val posterUrl:String,
    val profession:String,
    val films:List<ActorFilm>
)
data class ActorFilm(
    val filmId:Int,
    val nameRu:String,
    val nameEn: String,
    var rating: String,
    val professionKey: String
)
data class ActorFilmList(
    val actorId:Int,
    val actorName: String,
    val films: List<ActorFilm>
)

data class SearchedFilms(
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int,
    val films: List<Film>
)

fun ActorFilm.toFilm(): Film {
    return Film(
        kinopoiskId = this.filmId,
        imdbId = "",
        nameRu = this.nameRu,
        nameEn = this.nameEn,
        nameOriginal = "",
        countries = emptyList(),
        genres = emptyList(),
        ratingKinopoisk = this.rating.toDouble(),
        ratingImdb = 0.0,
        year = 0,
        type = "",
        posterUrl = "",
        posterUrlPreview = "",
        coverUrl = "",
        logoUrl = "",
        description = "",
        ratingAgeLimits = "0+",
        filmLength = 0,
        shortDescription = "",
        professionKey = this.professionKey
    )
}

fun SimilarFilm.toFilm(genres: List<Genre>): Film {
    if(this.nameOriginal == null) this.nameOriginal = ""
    if(this.nameRu == null) this.nameRu = ""
    if(this.nameEn == null) this.nameEn = ""
    if(this.posterUrl == null) this.posterUrl = ""
    if(this.posterUrlPreview == null) this.posterUrlPreview = ""

    return Film(
        kinopoiskId = this.filmId,
        imdbId = "",
        nameRu = this.nameRu,
        nameEn = this.nameEn,
        nameOriginal = this.nameOriginal,
        countries = emptyList(),
        genres = genres,
        ratingKinopoisk = 0.0,
        ratingImdb = 0.0,
        year = 0,
        type = "",
        posterUrl = "",
        posterUrlPreview = this.posterUrlPreview,
        coverUrl = this.posterUrl,
        logoUrl = "",
        description = "",
        ratingAgeLimits = "0+",
        filmLength = 0,
        shortDescription = "",
        professionKey = ""
    )
}