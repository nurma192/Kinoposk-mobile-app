package com.assignmentsmobile.assignment_2.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.Section
import com.assignmentsmobile.assignment_2.data.repository.FilmCollectionRepository
import kotlinx.coroutines.launch


class FilmCollectionsViewModel(private val repository: FilmCollectionRepository) : ViewModel() {
    val sections = MutableLiveData<MutableList<Section>>()

    fun loadFilmCollections() {
        viewModelScope.launch {
            val collectionTypes = mapOf(
                "Top Popular All" to "TOP_POPULAR_ALL",
                "Top Popular Movies" to "TOP_POPULAR_MOVIES",
                "Top 250 TV Shows" to "TOP_250_TV_SHOWS",
                "Top 250 Movies" to "TOP_250_MOVIES",
                "Vampire Theme" to "VAMPIRE_THEME",
                "Comics Theme" to "COMICS_THEME"
            )

            val sectionsList = mutableListOf<Section>()

            for ((sectionName, type) in collectionTypes) {
                val films = mutableListOf<Film>()
                films.addAll(repository.getFilmCollections(type, 1))
                films.addAll(repository.getFilmCollections(type, 2))

                sectionsList.add(Section(sectionName, films))
            }

            sections.postValue(sectionsList)
        }
    }


}