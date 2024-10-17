package com.assignmentsmobile.assignment_2

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Destination(val route: String, val image: Int, val selectedImage: Int) {
    object HomePage : Destination("home", R.drawable.ic_home, R.drawable.ic_home_selected)
    object SearchPage : Destination("search", R.drawable.ic_search, R.drawable.ic_home_selected)
    object AccountPage : Destination("profile", R.drawable.ic_profile, R.drawable.ic_home_selected)

    object Film : Destination("film", R.drawable.ic_home, R.drawable.ic_home_selected) {
        const val routeWithArgs = "film/{filmName}"
        const val filmName = "filmName"
        val arguments = listOf(navArgument(filmName) { type = NavType.StringType })
    }

    object FilmType : Destination("filmType", R.drawable.ic_home, R.drawable.ic_home_selected) {
        const val routeWithArgs = "filmType/{filmType}"
        const val filmType = "filmType"
        val arguments = listOf(navArgument(filmType) { type = NavType.StringType })
    }
}

val bottomBarPages = listOf(Destination.HomePage, Destination.SearchPage, Destination.AccountPage)