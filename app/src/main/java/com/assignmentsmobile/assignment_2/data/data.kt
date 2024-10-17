package com.assignmentsmobile.assignment_2.data

import androidx.compose.ui.graphics.Color

data class Film(
    val color: Color,
    var rating: String,
    val filmName: String,
    var genre: String,
    var isShowing: Boolean
)

data class Section(
    val sectionName: String,
    val list: List<Film>
)

val filmItems: List<Film> = listOf(
    Film(Color(0xffb5b5c9), "7.8", "Близкие", "драма", false),
    Film(Color(0xffb5b5c9), "8", "Game of Trones", "драма", true),
    Film(Color(0xffb5b5c9), "7.8", "Braking bad", "драма", true),
    Film(Color(0xffb5b5c9), "8", "You", "драма", false),
    Film(Color(0xffb5b5c9), "6", "Sheker", "драма", false),
    Film(Color(0xffb5b5c9), "7", "Hello world", "драма", false),
    Film(Color(0xffb5b5c9), "10", "Valli", "драма", false),
    Film(Color(0xffb5b5c9), "10", "Zhezduha", "драма", false)
)

var sectionItems: List<Section> = listOf(
    Section("Премьеры", filmItems),
    Section("Популярное", filmItems),
    Section("Боевики США", filmItems),
    Section("Топ-250", filmItems),
    Section("Драмы Франции", filmItems),
    Section("Сериалы", filmItems)
)