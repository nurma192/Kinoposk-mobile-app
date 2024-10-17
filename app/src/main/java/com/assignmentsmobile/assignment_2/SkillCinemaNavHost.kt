package com.assignmentsmobile.assignment_2

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
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
        startDestination = Destination.HomePage.route,
    ) {
        composable(route = Destination.HomePage.route) {
            HomepageScreen(innerPadding)
        }
        composable(route = Destination.SearchPage.route) {
            HomepageScreen(innerPadding)
        }
        composable(route = Destination.AccountPage.route) {
            HomepageScreen(innerPadding)
        }
        composable(
            route = Destination.Film.routeWithArgs,
            arguments = Destination.Film.arguments
        ) { navBackStackEntry ->
            val filmName = navBackStackEntry.arguments?.getString(Destination.Film.filmName)
            // FilmInfoPage(filmName)
        }
        composable(
            route = Destination.FilmType.routeWithArgs,
            arguments = Destination.FilmType.arguments
        ) { navBackStackEntry ->
            val filmType = navBackStackEntry.arguments?.getString(Destination.FilmType.filmType)
            // FilmTypePage(filmType)
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
    this.navigateSingle("${Destination.Film.route}/$filmName")
}

fun NavHostController.navigateToSingleFilmType(filmType: String) {
    this.navigateSingle("${Destination.FilmType.route}/$filmType")
}