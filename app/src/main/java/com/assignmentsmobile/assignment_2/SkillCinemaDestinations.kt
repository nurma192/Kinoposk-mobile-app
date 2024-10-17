package com.assignmentsmobile.assignment_2

import androidx.compose.material.icons.Icons
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

interface Destination {
    val image: Int
    val selectedImage: Int
    val route: String
}


object HomePage: Destination{
    override val image = R.drawable.ic_home
    override val selectedImage = R.drawable.ic_home_selected
    override val route = "homepage"
}

object SearchPage: Destination{
    override val image = R.drawable.ic_search
    override val selectedImage = R.drawable.ic_home_selected
    override val route = "searchpage"
}

object AccountPage: Destination{
    override val image = R.drawable.ic_profile
    override val selectedImage = R.drawable.ic_home_selected
    override val route = "accountpage"
}

    // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
    // part of the RallyTabRow selection
object Film: Destination {
    override val image = R.drawable.ic_home_selected
    override val selectedImage = R.drawable.ic_home_selected
    override val route = "single_film"
    val filmName = "film_name"
    val routeWithArgs = "${route}/{${filmName}}"
    val arguments = listOf(
        navArgument(filmName) { type = NavType.StringType }
    )
}

object FilmType: Destination {
    override val image = R.drawable.ic_home_selected
    override val selectedImage = R.drawable.ic_home_selected
    override val route = "single_film_type"
    val filmType = "film_type"
    val routeWithArgs = "${route}/{${filmType}}"
    val arguments = listOf(
        navArgument(filmType) { type = NavType.StringType }
    )
}


val bottomBarPages = listOf(HomePage, SearchPage, AccountPage)