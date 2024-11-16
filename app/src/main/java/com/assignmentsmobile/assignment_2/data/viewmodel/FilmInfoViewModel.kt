package com.assignmentsmobile.assignment_2.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.assignmentsmobile.assignment_2.data.Actor
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.FilmImagesList
import com.assignmentsmobile.assignment_2.data.SimilarFilmList
import com.assignmentsmobile.assignment_2.data.StaffList
import com.assignmentsmobile.assignment_2.data.domain.ActorApiService
import com.assignmentsmobile.assignment_2.data.domain.FilmApiService
import com.assignmentsmobile.assignment_2.data.domain.FilmImagesApiService
import com.assignmentsmobile.assignment_2.data.domain.SimilarFilmsApiService
import com.assignmentsmobile.assignment_2.data.domain.StaffApiService
import com.assignmentsmobile.assignment_2.data.repository.ActorRepository
import com.assignmentsmobile.assignment_2.data.repository.FilmImagesListRepository
import com.assignmentsmobile.assignment_2.data.repository.FilmRepository
import com.assignmentsmobile.assignment_2.data.repository.SimilarFilmsListRepository
import com.assignmentsmobile.assignment_2.data.repository.StaffRepository
import com.assignmentsmobile.assignment_2.ui.states.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmInfoViewModel(
    private val filmRepository: FilmRepository,
    private val staffRepository: StaffRepository,
    private val imagesListRepository: FilmImagesListRepository,
    private val similarFilmsListRepository: SimilarFilmsListRepository,
    private val actorRepository: ActorRepository

) : ViewModel() {
    private val _actorDetailState = MutableStateFlow<ScreenState<Actor>>(ScreenState.Initial)
    val actorDetailState: StateFlow<ScreenState<Actor>> = _actorDetailState


    private val _filmInfoState = MutableStateFlow<ScreenState<Film>>(ScreenState.Initial)
    val filmInfoState: StateFlow<ScreenState<Film>> = _filmInfoState

    private val _staffInfoState = MutableStateFlow<ScreenState<StaffList>>(ScreenState.Initial)
    val staffInfoState: StateFlow<ScreenState<StaffList>> = _staffInfoState

    private val _filmImagesState =
        MutableStateFlow<ScreenState<FilmImagesList>>(ScreenState.Initial)
    val filmImagesState: StateFlow<ScreenState<FilmImagesList>> = _filmImagesState

    private val _similarFilmState =
        MutableStateFlow<ScreenState<SimilarFilmList>>(ScreenState.Initial)
    val similarFilmState: StateFlow<ScreenState<SimilarFilmList>> = _similarFilmState

    fun getFilmById(filmId: Int) {
        viewModelScope.launch {
            _filmInfoState.value = ScreenState.Loading
            try {
                val response = filmRepository.getFilmById(filmId)
                if (response.isSuccessful && response.body() != null) {
                    _filmInfoState.value = ScreenState.Success(response.body()!!)
                } else {
                    _filmInfoState.value = ScreenState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _filmInfoState.value = ScreenState.Error("Network error: ${e.message}")
            }
        }
    }

    fun getActorDetailById(actorId: Int) {
        viewModelScope.launch {
            _actorDetailState.value = ScreenState.Loading
            try {
                val response = actorRepository.getActorDetailById(actorId)
                if (response.isSuccessful && response.body() != null) {
                    _actorDetailState.value = ScreenState.Success(response.body()!!)
                } else {

                    _actorDetailState.value = ScreenState.Error("Error: ${response.message()}")

                }
            } catch (e: Exception) {
                _actorDetailState.value = ScreenState.Error("Network error: ${e.message}")
            }
        }

    }

    fun getStaffById(filmId: Int) {
        viewModelScope.launch {
            _staffInfoState.value = ScreenState.Loading
            try {
                val response = staffRepository.getStaffById(filmId)
                if (response.isSuccessful && response.body() != null) {
                    val staffList = response.body()!!
                    val actors = staffList.filter { it.professionKey == "ACTOR" }
                    val otherStaff = staffList.filter { it.professionKey != "ACTOR" }
                    val staffListData = StaffList(actors, otherStaff)
                    _staffInfoState.value = ScreenState.Success(staffListData)
                } else {
                    _staffInfoState.value = ScreenState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _staffInfoState.value = ScreenState.Error("Network error: ${e.message}")
            }
        }
    }

    fun getFilmImages(filmId: Int) {
        viewModelScope.launch {
            _filmImagesState.value = ScreenState.Loading
            try {
                val result = imagesListRepository.getFilmImages(filmId, "SHOOTING", 1)
                result.fold(
                    onSuccess = { images ->
                        _filmImagesState.value = ScreenState.Success(images)
                    },
                    onFailure = { error ->
                        _filmImagesState.value = ScreenState.Error("Error: ${error.message}")
                    }
                )
            } catch (e: Exception) {
                _filmImagesState.value = ScreenState.Error("Network error: ${e.message}")
            }
        }
    }

    fun getSimilarFilms(filmId: Int) {
        viewModelScope.launch {
            _similarFilmState.value = ScreenState.Loading
            try {
                val result = similarFilmsListRepository.getSimilarFilms(filmId)
                _similarFilmState.value = ScreenState.Success(result)
            } catch (e: Exception) {
                _similarFilmState.value = ScreenState.Error("Network error: ${e.message}")
            }
        }
    }
}

class FilmInfoViewModelFactory(
    private val filmApiService: FilmApiService,
    private val staffApiService: StaffApiService,
    private val filmImagesApiService: FilmImagesApiService,
    private val similarFilmsApiService: SimilarFilmsApiService,
    private val actorApiService: ActorApiService

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmInfoViewModel::class.java)) {
            val filmRepository = FilmRepository(filmApiService)
            val staffRepository = StaffRepository(staffApiService)
            val filmImagesListRepository = FilmImagesListRepository(filmImagesApiService)
            val similarFilmsListRepository = SimilarFilmsListRepository(similarFilmsApiService)
            val actorRepository = ActorRepository(actorApiService)
            return FilmInfoViewModel(
                filmRepository,
                staffRepository,
                filmImagesListRepository,
                similarFilmsListRepository,
                actorRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
