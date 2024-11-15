package com.assignmentsmobile.assignment_2

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Destination(val route: String, val image: Int, val selectedImage: Int) {
    data object HomePage : Destination("home", R.drawable.ic_home, R.drawable.ic_home_selected)
    data object SearchPage : Destination("search", R.drawable.ic_search, R.drawable.ic_search_selected)
    data object AccountPage : Destination("profile", R.drawable.ic_profile, R.drawable.ic_profile_selected)

    data object Film : Destination("film", R.drawable.ic_home, R.drawable.ic_home_selected) {
        const val routeWithArgs = "film/{filmId}"
        const val filmId = "filmId"
        val arguments = listOf(navArgument(filmId) { type = NavType.IntType })
    }

    data object FilmType : Destination("filmType", R.drawable.ic_home, R.drawable.ic_home_selected) {
        const val routeWithArgs = "filmType/{filmType}"
        const val filmType = "filmType"
        val arguments = listOf(navArgument(filmType) { type = NavType.StringType })
    }

    data object Gallery : Destination("gallery", R.drawable.ic_home, R.drawable.ic_home_selected)
}

val bottomBarPages = listOf(Destination.HomePage, Destination.SearchPage, Destination.AccountPage)