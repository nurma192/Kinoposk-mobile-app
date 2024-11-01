package com.assignmentsmobile.assignment_2.ui.states

sealed class ScreenState<out T> {
    object Initial : ScreenState<Nothing>()
    object Loading : ScreenState<Nothing>()
    data class Success<out T>(val data: T) : ScreenState<T>()
    data class Error(val message: String) : ScreenState<Nothing>()


}