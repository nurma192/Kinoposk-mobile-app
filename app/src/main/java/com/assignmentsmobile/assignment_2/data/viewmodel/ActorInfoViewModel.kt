    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import androidx.lifecycle.viewModelScope
    import com.assignmentsmobile.assignment_2.data.Actor
    import com.assignmentsmobile.assignment_2.data.StaffList
    import com.assignmentsmobile.assignment_2.data.domain.ActorApiService
    import com.assignmentsmobile.assignment_2.data.domain.KinopoiskDomain
    import com.assignmentsmobile.assignment_2.data.repository.ActorRepository
    import com.assignmentsmobile.assignment_2.ui.states.ScreenState
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.launch
    import kotlinx.coroutines.withContext
    
    class ActorInfoViewModel(
        private val actorRepository: ActorRepository = ActorRepository(KinopoiskDomain.actorApiService)
    ) : ViewModel() {
    
        private val _actorDetailState = MutableStateFlow<ScreenState<Actor>>(ScreenState.Initial)
        val actorDetailState: StateFlow<ScreenState<Actor>> = _actorDetailState
    
        fun getActorDetailById(actorId: Int) {
            viewModelScope.launch {
                _actorDetailState.value = ScreenState.Loading
                try {
                    val result = actorRepository.getActorDetailById(actorId)
                    if (result.isSuccessful && result.body() != null) {
                        val actor = result.body()!!
                        _actorDetailState.value = ScreenState.Success(actor)
                    } else {
                        _actorDetailState.value = ScreenState.Error("Error: ${result.message()}")
                    }
                } catch (e: Exception) {
                    _actorDetailState.value = ScreenState.Error("Network error: ${e.message}")
                }
            }
        }
    }
    
    
    class ActorInfoViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ActorInfoViewModel::class.java)) {
                return ActorInfoViewModel(
                    actorRepository = ActorRepository(KinopoiskDomain.actorApiService)
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }