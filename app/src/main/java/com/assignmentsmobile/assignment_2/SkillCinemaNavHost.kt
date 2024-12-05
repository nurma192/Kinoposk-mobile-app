package com.assignmentsmobile.assignment_2

import FilmographyPage
import SearchFilmsRepository
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignmentsmobile.assignment_2.data.ActorFilm
import com.assignmentsmobile.assignment_2.data.FilmImagesList
import com.assignmentsmobile.assignment_2.data.Filter
import com.assignmentsmobile.assignment_2.data.addFilmToSection
import com.assignmentsmobile.assignment_2.data.initializeSections
import com.assignmentsmobile.assignment_2.data.domain.KinopoiskDomain.filmsSearchApiService

import com.assignmentsmobile.assignment_2.data.viewmodel.FilmCollectionsViewModel
import com.assignmentsmobile.assignment_2.ui.pages.ActorPage
import com.assignmentsmobile.assignment_2.ui.pages.FilmInfo.FilmInfoPage
import com.assignmentsmobile.assignment_2.ui.pages.FilterPage.FilterPage
import com.assignmentsmobile.assignment_2.ui.pages.GalleryPage.GalleryPage
import com.assignmentsmobile.assignment_2.ui.pages.HomePage.HomePage
import com.assignmentsmobile.assignment_2.ui.pages.ListPage.ListPage
import com.assignmentsmobile.assignment_2.ui.pages.Profile.ProfilePage
import com.assignmentsmobile.assignment_2.ui.pages.CountrySelectorScreen.CountrySelectorScreen
import com.assignmentsmobile.assignment_2.ui.pages.DateRangeSelector.DateRangeSelector
import com.assignmentsmobile.assignment_2.ui.pages.GenreSelectorScreen.GenreSelectorScreen
import com.assignmentsmobile.assignment_2.ui.pages.SearchPage.SearchPage

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
    val searchFilmsRepository = SearchFilmsRepository(filmsSearchApiService)
    var filters by remember { mutableStateOf(Filter("", "", "", "", false)) }

//    val sectionViewModel: SectionViewModel = viewModel(
//        factory = SectionViewModelFactory(LocalContext.current.applicationContext as Application)
//    )

    initializeSections()

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
            SearchPage(
                onFilterClicked = {
                    navController.navigate(Destination.FilterPage.route)
                },
                searchFilmsRepository = searchFilmsRepository,
                onFilmClicked = { filmId ->
                    navController.navigateToSingleFilm(filmId)
                },
                filters = filters
            )

        }
        composable(route = Destination.FilterPage.route) {
            FilterPage(
                onBackClicked = {
                    navController.popBackStack()
                },
                onCountryClicked = {
                    navController.navigate(Destination.CountrySelectorScreen.route)
                },
                onDateRangeClicked = {
                    navController.navigate(Destination.DataRangeSelector.route)
                },
                onGenreClicked = {
                    navController.navigate(Destination.GenreSelectorScreen.route)
                },
                filters = filters
            )
        }
        composable(route = Destination.CountrySelectorScreen.route) {
            CountrySelectorScreen(
                onBackClicked = {
                    navController.popBackStack()
                },
                filters = filters
            )
        }
        composable(route = Destination.DataRangeSelector.route) {
            DateRangeSelector(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Destination.GenreSelectorScreen.route) {
            GenreSelectorScreen(
                onBackClicked = {
                    navController.popBackStack()
                },
                filters = filters
            )
        }
        composable(route = Destination.AccountPage.route) {
            ProfilePage(
                onFilmClicked = { filmId ->
                    navController.navigateToSingleFilm(filmId)
                }
            )
        }
        composable(route = Destination.Gallery.route) {
            GalleryPage(
                innerPadding,
                gallery,
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Destination.Actor.route) {
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
                },
                onWatchedClicked = { film ->
//                    sectionViewModel.addFilmToSection("Просмотрено", film)
                    addFilmToSection("watched", film)
                },
                onFavoriteClicked = { film ->
//                    sectionViewModel.addFilmToSection("Просмотрено", film)
                    addFilmToSection("favorite", film)
                },
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
            val actorName =
                navBackStackEntry.arguments?.getString(Destination.Filmography.actorName)
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