package com.assignmentsmobile.assignment_2.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.Section
import com.assignmentsmobile.assignment_2.data.repository.FilmCollectionRepository
import com.assignmentsmobile.assignment_2.ui.states.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class FilmCollectionsViewModel(private val repository: FilmCollectionRepository) : ViewModel() {
    private val _screenState = MutableStateFlow<ScreenState<List<Section>>>(ScreenState.Initial)
    val screenState: StateFlow<ScreenState<List<Section>>> = _screenState


    fun loadFilmCollections() {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            try {
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

                _screenState.value = ScreenState.Success(sectionsList)
            } catch (e: Exception) {
                _screenState.value =
                    ScreenState.Error("Failed to load film collections: ${e.message}")
            }


        }
    }


}