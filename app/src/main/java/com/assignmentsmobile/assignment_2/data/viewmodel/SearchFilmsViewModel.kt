import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.assignmentsmobile.assignment_2.data.SearchedFilms
import com.assignmentsmobile.assignment_2.ui.states.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchFilmsViewModel(
    private val searchFilmsRepository: SearchFilmsRepository
) : ViewModel() {
    private val _filmsState = MutableStateFlow<ScreenState<SearchedFilms>>(ScreenState.Initial)
    val filmsState: StateFlow<ScreenState<SearchedFilms>> = _filmsState

    fun searchFilms(keyword: String, page: Int) {
        viewModelScope.launch {
            _filmsState.value = ScreenState.Loading
            try {
                val result = searchFilmsRepository.getFilmsByKeyword(keyword, page)
                if (result.isSuccess) {
                    _filmsState.value = ScreenState.Success(result.getOrNull()!!)
                } else {
                    _filmsState.value =
                        ScreenState.Error("Error: ${result.exceptionOrNull()?.message}")
                }
            } catch (e: Exception) {
                _filmsState.value = ScreenState.Error("Error: ${e.message}")
            }
        }
    }
}

class SearchFilmsViewModelFactory(
    private val searchFilmsRepository: SearchFilmsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchFilmsViewModel::class.java)) {
            return SearchFilmsViewModel(searchFilmsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}