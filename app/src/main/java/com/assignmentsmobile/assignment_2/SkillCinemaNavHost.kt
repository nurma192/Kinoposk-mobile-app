package com.assignmentsmobile.assignment_2

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignmentsmobile.assignment_2.data.repository.FilmCollectionRepository
import com.assignmentsmobile.assignment_2.data.repository.FilmCollectionsViewModelFactory
import com.assignmentsmobile.assignment_2.data.viewmodel.FilmCollectionsViewModel
import com.assignmentsmobile.assignment_2.ui.pages.FilmInfo.FilmInfoPage
import com.assignmentsmobile.assignment_2.ui.pages.HomePage.HomePage
import com.assignmentsmobile.assignment_2.ui.pages.ListPage.ListPage

@Composable
fun SkillCinemaNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {

    val repository = FilmCollectionRepository()
    val filmCollectionsViewModel: FilmCollectionsViewModel = viewModel(
        factory = FilmCollectionsViewModelFactory(repository)
    )
    val screenState by filmCollectionsViewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        filmCollectionsViewModel.loadFilmCollections()
    }

    NavHost(
        navController = navController,
        startDestination = Destination.HomePage.route,
    ) {
        composable(route = Destination.HomePage.route) {
            HomePage(
                innerPadding = innerPadding,
                screenState = screenState,
                onFilmClicked = { filmName ->
                    navController.navigateToSingleFilm(filmName)
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
                onFilmClicked = { filmName ->
                    navController.navigateToSingleFilm(filmName)
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
                onFilmClicked = { filmName ->
                    navController.navigateToSingleFilm(filmName)
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
            val filmName = navBackStackEntry.arguments?.getString(Destination.Film.filmName)
            FilmInfoPage(filmName = filmName, innerPadding = innerPadding)
        }
        composable(route = Destination.FilmType.routeWithArgs) { navBackStackEntry ->
            val filmType = navBackStackEntry.arguments?.getString(Destination.FilmType.filmType)
            ListPage(filmType = filmType, onBackClicked = { navController.popBackStack() })
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

fun NavHostController.navigateToSingleFilm(filmName: String) {
    this.navigateSingle("${Destination.Film.route}/$filmName")
}

fun NavHostController.navigateToSingleFilmType(filmType: String) {
    this.navigateSingle("${Destination.FilmType.route}/$filmType")
}