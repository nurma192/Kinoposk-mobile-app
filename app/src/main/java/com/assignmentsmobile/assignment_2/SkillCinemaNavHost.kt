package com.assignmentsmobile.assignment_2

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignmentsmobile.assignment_2.ui.pages.HomePage.HomepageScreen

@Composable
fun SkillCinemaNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = HomePage.route,
    ) {
        composable(route = HomePage.route){
            HomepageScreen(innerPadding)
        }
        composable(route = SearchPage.route){
            HomepageScreen(innerPadding)
        }
        composable(route = AccountPage.route){
            HomepageScreen(innerPadding)
        }
        composable(
            route = Film.routeWithArgs,
            arguments = Film.arguments
        ){navBackStackEntry ->
            val filmName =
                navBackStackEntry.arguments?.getString(Film.filmName)
//            FilmInfoPage(filmName)
        }
        composable(
            route = FilmType.routeWithArgs,
            arguments = FilmType.arguments
        ){navBackStackEntry ->
            val filmType =
                navBackStackEntry.arguments?.getString(FilmType.filmType)
//            FilmTypePage(filmType)
        }

    }
}

fun NavHostController.navigateSingle(route: String) =
    this.navigate(route) {
        launchSingleTop = true  // Avoid multiple instances of the same destination
        restoreState = true     // Restore previous state if it exists
        popUpTo(graph.findStartDestination().id) {
            saveState = true    // Save state when navigating between bottom tabs
        }
    }

fun NavHostController.navigateToSingleFilm(filmName: String) {
    this.navigateSingle("${Film.route}/$filmName")
}

fun NavHostController.navigateToSingleFilmType(filmType: String) {
    this.navigateSingle("${FilmType.route}/$filmType")
}