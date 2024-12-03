package com.assignmentsmobile.assignment_2.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.Section
import com.assignmentsmobile.assignment_2.data.local.SectionDao
import com.assignmentsmobile.assignment_2.data.local.SectionDatabase
import com.assignmentsmobile.assignment_2.ui.states.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SectionViewModel(application: Application) : AndroidViewModel(application) {

    private val sectionDao: SectionDao = SectionDatabase.getDatabase(application).sectionDao()

    private val _sectionsState = MutableStateFlow<ScreenState<List<Section>>>(ScreenState.Initial)
    val sectionsState: StateFlow<ScreenState<List<Section>>> = _sectionsState

    init {
        initializeSections()
    }

    private fun initializeSections() {
        _sectionsState.value = ScreenState.Loading

        viewModelScope.launch {
            try {
                val existingSections = sectionDao.getSections()
                if (existingSections.isEmpty()) {
                    val defaultSections = getDefaultSections()
                    sectionDao.insertSections(defaultSections)
                    _sectionsState.value = ScreenState.Success(defaultSections)
                } else {
                    _sectionsState.value = ScreenState.Success(existingSections)
                }
            } catch (e: Exception) {
                _sectionsState.value = ScreenState.Error("Ошибка загрузки данных: ${e.message}")
            }
        }
    }

    private fun getDefaultSections(): List<Section> {
        return listOf(
            Section("Просмотрено", emptyList()),
            Section( "Любимые", emptyList()),
        )
    }

    fun addFilmToSection(sectionName: String, film: Film) {
        viewModelScope.launch {
            try {
                val currentSections = _sectionsState.value.let {
                    (it as? ScreenState.Success)?.data?.toMutableList() ?: mutableListOf()
                }
                val section = currentSections.find { it.sectionName == sectionName }
                if (section != null) {
                    val updatedSection = section.copy(list = section.list + film)
                    sectionDao.updateSection(updatedSection)
                    _sectionsState.value = ScreenState.Success(currentSections)
                } else {
                    val newSection = Section(sectionName, listOf(film))
                    sectionDao.insertSections(listOf(newSection))
                    currentSections.add(newSection)
                    _sectionsState.value = ScreenState.Success(currentSections)
                }
            } catch (e: Exception) {
                _sectionsState.value = ScreenState.Error("Ошибка добавления фильма: ${e.message}")
            }
        }
    }

    fun clearAllSections() {
        viewModelScope.launch {
            try {
                sectionDao.clearAllSections()
                _sectionsState.value = ScreenState.Success(emptyList())
            } catch (e: Exception) {
                _sectionsState.value = ScreenState.Error("Ошибка при очистке данных: ${e.message}")
            }
        }
    }
}


class SectionViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SectionViewModel::class.java)) {
            return SectionViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
