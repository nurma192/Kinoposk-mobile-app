package com.assignmentsmobile.assignment_2.data

import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.assignmentsmobile.assignment_2.data.local.CountryListConverter
import com.assignmentsmobile.assignment_2.data.local.FilmListConverter
import com.assignmentsmobile.assignment_2.data.local.GenreListConverter
import kotlinx.serialization.Serializable

val breakingBad = Film(
    kinopoiskId = 404900,
    imdbId = "",
    nameRu = "Во все тяжкие",
    nameEn = "Breaking Bad",
    nameOriginal = "Breaking Bad",
    countries = listOf(Country("США")),  // List of countries (using the Country class)
    genres = listOf(Genre("триллер"), Genre("драма"), Genre("криминал")),  // List of genres (using the Genre class)
    ratingKinopoisk = 8.9,
    ratingImdb = 9.5,
    year = 2008,
    type = "TV_SERIES",
    posterUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp/404900.jpg",
    posterUrlPreview = "https://kinopoiskapiunofficial.tech/images/posters/kp_small/404900.jpg",
    coverUrl = "https://avatars.mds.yandex.net/get-ott/1531675/2a0000017c07954f2e92e530a0947de62952/orig",
    logoUrl = "https://avatars.mds.yandex.net/get-ott/200035/2a0000017802c0dff697971233b0e9def244/orig",
    description = "Школьный учитель химии Уолтер Уайт узнаёт, что болен раком лёгких...",
    shortDescription = "Умирающий учитель химии начинает варить мет ради благополучия семьи...",
    ratingAgeLimits = "age18",
    filmLength = 47,
    professionKey = null
)

val gameOfThrones = Film(
    kinopoiskId = 464963,
    imdbId = "",
    nameRu = "Игра престолов",
    nameEn = "Game of Thrones",
    nameOriginal = "Game of Thrones",
    countries = listOf(Country("США"), Country("Великобритания")),  // List of countries
    genres = listOf(Genre("драма"), Genre("мелодрама"), Genre("приключения"), Genre("боевик"), Genre("фэнтези")),  // List of genres
    ratingKinopoisk = 9.0,
    ratingImdb = 9.2,
    year = 2011,
    type = "TV_SERIES",
    posterUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp/464963.jpg",
    posterUrlPreview = "https://kinopoiskapiunofficial.tech/images/posters/kp_small/464963.jpg",
    coverUrl = "https://avatars.mds.yandex.net/get-ott/200035/2a0000017d5bad9aee6287b862b1414eb223/orig",
    logoUrl = "https://avatars.mds.yandex.net/get-ott/239697/2a00000170b077ba4dca5c9303185c5e8003/orig",
    description = "К концу подходит время благоденствия, и лето, длившееся почти десятилетие...",
    shortDescription = "Рыцари, мертвецы и драконы — в эпической битве за судьбы мира...",
    ratingAgeLimits = "age18",
    filmLength = 55,
    professionKey = null
)

@Serializable
data class FilmCollectionsResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<Film>
)

@Entity(tableName = "section_table")
data class Section(
    @PrimaryKey val sectionName: String,
    @TypeConverters(FilmListConverter::class) val list: List<Film>
)

@Entity(tableName = "films")
data class Film(
    @PrimaryKey val kinopoiskId: Int,
    val imdbId: String,
    val nameRu: String,
    val nameEn: String?,
    val nameOriginal: String,
    @TypeConverters(CountryListConverter::class) val countries: List<Country>,
    @TypeConverters(GenreListConverter::class) val genres: List<Genre>,
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

val dbList = MutableLiveData<List<Section>>()

fun initializeSections() {
    val watchedSection = Section("watched", listOf(breakingBad, gameOfThrones))
    val favoritesSection = Section("favorites", listOf(breakingBad, gameOfThrones))
    val interestSection = Section("interest", listOf(breakingBad, gameOfThrones))

    dbList.value = listOf(watchedSection, favoritesSection, interestSection)
}
fun addFilmToSection(sectionName: String, film: Film) {
    val currentList = dbList.value ?: return

    val section = currentList.find { it.sectionName == sectionName }

    if (section != null) {
        if (film !in section.list) {
            val updatedSection = section.copy(list = section.list + film)
            dbList.value = currentList.map { if (it.sectionName == sectionName) updatedSection else it }
        }
    } else {
        val newSection = Section(sectionName, listOf(film))
        dbList.value = currentList + newSection
    }
}

fun removeSectionByName(sectionName: String) {
    val currentList = dbList.value ?: return

    val updatedList = currentList.map { section ->
        if (section.sectionName == sectionName) {
            section.copy(list = emptyList())
        } else {
            section
        }
    }

    dbList.value = updatedList
}



@Serializable
data class Country(
    val country: String
)

@Serializable
data class Genre(
    val genre: String
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

data class Actor(
    val personId: Int,
    val nameRu: String,
    val posterUrl: String,
    val profession: String,
    val films: List<ActorFilm>
)

data class ActorFilm(
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    var rating: String,
    val professionKey: String
)

data class ActorFilmList(
    val actorId: Int,
    val actorName: String,
    val films: List<ActorFilm>
)

data class Filter(
    val type: String,
    val country: String,
    val rating: String,
    val sortBy: String,
    val watched: Boolean
)

data class SearchedFilms(
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int,
    val films: List<SearchedFilm>
)

data class SearchedFilm(
    val filmId: Int,
    val nameRu: String = "nameRU",
    val nameEn: String = "nameEN",
    val type: String = "Type",
    val year: String = "Year",
    val description: String = "Description",
    val filmLength: String = "00:00",
    val countries: List<Country>,
    val genres: List<Genre>,
    val rating: String = "rating",
    val ratingVoteCount: Int = 0,
    val posterUrl: String = "",
    val posterUrlPreview: String = "",
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
    if (this.nameOriginal == null) this.nameOriginal = ""
    if (this.nameRu == null) this.nameRu = ""
    if (this.nameEn == null) this.nameEn = ""
    if (this.posterUrl == null) this.posterUrl = ""
    if (this.posterUrlPreview == null) this.posterUrlPreview = ""

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