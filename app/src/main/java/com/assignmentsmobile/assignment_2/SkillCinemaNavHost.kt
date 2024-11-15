package com.assignmentsmobile.assignment_2

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.assignmentsmobile.assignment_2.data.viewmodel.FilmCollectionsViewModel
import com.assignmentsmobile.assignment_2.ui.pages.FilmInfo.FilmInfoPage
import com.assignmentsmobile.assignment_2.ui.pages.HomePage.HomePage
import com.assignmentsmobile.assignment_2.ui.pages.ListPage.ListPage

@Composable
fun SkillCinemaNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {

    val filmCollectionsViewModel: FilmCollectionsViewModel = viewModel()
    val screenState by filmCollectionsViewModel.screenState.collectAsState()


    NavHost(
        navController = navController,
        startDestination = Destination.HomePage.route,
    ) {
        composable(route = Destination.HomePage.route) {
            HomePage(
                innerPadding = innerPadding,
                screenState = screenState,
                onFilmClicked = { filmId ->
                    navController.navigateToSingleFilm(filmId)
                },
                onFilmTypeClicked = { filmType ->
                    navController.navigateToSingleFilmType(filmType)
                },

            )
        }
        composable(route = Destination.SearchPage.route) {
            HomePage(
                innerPadding = innerPadding,
                screenState = screenState,
                onFilmClicked = { filmId ->
                    navController.navigateToSingleFilm(filmId)
                },
                onFilmTypeClicked = { filmType ->
                    navController.navigateToSingleFilmType(filmType)
                }
            )
        }
        composable(route = Destination.AccountPage.route) {
            HomePage(
                innerPadding = innerPadding,
                screenState  = screenState,
                onFilmClicked = { filmId ->
                    navController.navigateToSingleFilm(filmId)
                },
                onFilmTypeClicked = { filmType ->
                    navController.navigateToSingleFilmType(filmType)
                }
            )
        }
        composable(
            route = Destination.Film.routeWithArgs,
            arguments = Destination.Film.arguments
        ) { navBackStackEntry ->
            val filmId = navBackStackEntry.arguments!!.getInt(Destination.Film.filmId)
            FilmInfoPage(
                filmId = filmId,
                onBackClicked = {
                    navController.popBackStack()
                }
            )

        }
        composable(route = Destination.FilmType.routeWithArgs) { navBackStackEntry ->
            val filmType = navBackStackEntry.arguments?.getString(Destination.FilmType.filmType)
            ListPage(
                filmType = filmType,
                onBackClicked = { navController.popBackStack() },
                screenState = screenState,
                innerPadding = innerPadding,
                onFilmClicked = { filmId ->
                    navController.navigateToSingleFilm(filmId)
                },
            )
        }
    }
}


fun NavHostController.navigateSingle(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }


fun NavHostController.navigateToSingleFilm(filmId: Int) {
    this.navigateSingle("${Destination.Film.route}/$filmId")
}

fun NavHostController.navigateToSingleFilmType(filmType: String) {
    this.navigateSingle("${Destination.FilmType.route}/$filmType")
}