package com.assignmentsmobile.assignment_2

import FilmographyPage
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignmentsmobile.assignment_2.data.ActorFilm
import com.assignmentsmobile.assignment_2.data.ActorFilmList
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.FilmImagesList
import com.assignmentsmobile.assignment_2.data.domain.KinopoiskDomain

import com.assignmentsmobile.assignment_2.data.viewmodel.FilmCollectionsViewModel
import com.assignmentsmobile.assignment_2.data.viewmodel.FilmInfoViewModelFactory
import com.assignmentsmobile.assignment_2.data.viewmodel.FilmInfoViewModel
import com.assignmentsmobile.assignment_2.ui.pages.ActorPage
import com.assignmentsmobile.assignment_2.ui.pages.FilmInfo.FilmInfoPage
import com.assignmentsmobile.assignment_2.ui.pages.GalleryPage.GalleryPage
import com.assignmentsmobile.assignment_2.ui.pages.HomePage.HomePage
import com.assignmentsmobile.assignment_2.ui.pages.ListPage.ListPage

@Composable
fun SkillCinemaNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {

    val filmCollectionsViewModel: FilmCollectionsViewModel = viewModel()
    val screenState by filmCollectionsViewModel.screenState.collectAsState()
    var gallery by remember { mutableStateOf<FilmImagesList?>(null) }
    var filmographyFilms by remember { mutableStateOf<List<ActorFilm>?>(null) }
    var actorInfoId by remember { mutableIntStateOf(0) }

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
        composable(route = Destination.Gallery.route){
            GalleryPage(
                innerPadding,
                gallery,
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Destination.Actor.route){
            ActorPage(
                id = actorInfoId,
                mainPadding = innerPadding,
                onBackClicked = {
                    navController.popBackStack()
                },
                onFilmClicked = { filmId ->
                    navController.navigateToSingleFilm(filmId)
                },
                onFilmographyClicked = { actorName, films ->
                    Log.d("onFilmographyClicked", actorName)
                    filmographyFilms = films
                    navController.navigateToActorFilmography(actorName)
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
                },
                onFilmClicked = { id ->
                    navController.navigateToSingleFilm(id)
                },
                onGalleryClicked = { galleryList ->
                    gallery = galleryList
                    navController.navigateSingle(Destination.Gallery.route)
                },
                onActorClicked = { actorId ->
                    actorInfoId = actorId
                    navController.navigate(Destination.Actor.route)
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
        composable(route = Destination.Filmography.routeWithArgs) { navBackStackEntry ->
            val actorName = navBackStackEntry.arguments?.getString(Destination.Filmography.actorName)
            Log.d("actorName", actorName.toString())
            FilmographyPage(
                actorFilmList = filmographyFilms,
                actorName = actorName,
                onBackClicked = { navController.popBackStack() },
                innerPadding = innerPadding,
                screenState = screenState,
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
    }


fun NavHostController.navigateToSingleFilm(filmId: Int) {
    val route = "${Destination.Film.route}/$filmId"
    this.navigate(route)
}

fun NavHostController.navigateToActorFilmography(actorName: String) {
    val route = "${Destination.Filmography.route}/$actorName/filmography"
    Log.d("route", route)
    this.navigate(route)
}

fun NavHostController.navigateToSingleFilmType(filmType: String) {
    this.navigateSingle("${Destination.FilmType.route}/$filmType")
}