package com.assignmentsmobile.assignment_2.data.local

import androidx.room.TypeConverter
import com.assignmentsmobile.assignment_2.data.Country
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FilmListConverter {
    @TypeConverter
    fun fromFilmList(value: List<Film>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toFilmList(value: String): List<Film> {
        val listType = object : TypeToken<List<Film>>() {}.type
        return Gson().fromJson(value, listType)
    }
}

class CountryListConverter {
    @TypeConverter
    fun fromCountryList(value: List<Country>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCountryList(value: String): List<Country> {
        val listType = object : TypeToken<List<Country>>() {}.type
        return Gson().fromJson(value, listType)
    }
}

class GenreListConverter {
    @TypeConverter
    fun fromGenreList(value: List<Genre>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toGenreList(value: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
