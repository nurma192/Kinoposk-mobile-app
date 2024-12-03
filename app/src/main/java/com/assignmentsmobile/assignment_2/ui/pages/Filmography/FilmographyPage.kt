import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.assignmentsmobile.assignment_2.data.Actor
import com.assignmentsmobile.assignment_2.data.ActorFilm
import com.assignmentsmobile.assignment_2.data.ActorFilmList
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.Section
import com.assignmentsmobile.assignment_2.data.domain.KinopoiskDomain
import com.assignmentsmobile.assignment_2.data.viewmodel.FilmInfoViewModel
import com.assignmentsmobile.assignment_2.data.viewmodel.FilmInfoViewModelFactory
import com.assignmentsmobile.assignment_2.ui.components.DetailPageHeader
import com.assignmentsmobile.assignment_2.ui.components.FilmView
import com.assignmentsmobile.assignment_2.ui.states.ScreenState
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first

@Composable
fun FilmographyPage(
    actorFilmList: List<ActorFilm>?,
    actorName: String?,
    onBackClicked: () -> Unit,
    screenState: ScreenState<List<Section>>,
    innerPadding: PaddingValues,
    onFilmClicked: (Int) -> Unit = {},
    viewModel: FilmInfoViewModel = viewModel(
        factory = FilmInfoViewModelFactory(
            KinopoiskDomain.filmApiService,
            KinopoiskDomain.staffApiService,
            KinopoiskDomain.filmImagesApiService,
            KinopoiskDomain.similarFilmsApiService,
        )
    ),
) {


    val filmInfoState by viewModel.filmInfoState.collectAsState()
    val filmsAllInfo = remember { mutableStateListOf<Film>() }
    var professionKey by remember { mutableStateOf("ACTOR") }
    val professions: List<String> =
        actorFilmList?.map { it.professionKey }?.distinct() ?: emptyList()



    LaunchedEffect(professionKey) {
        if (actorFilmList != null) {
            filmsAllInfo.clear()
            actorFilmList.filter { it.professionKey == professionKey }.take(20).forEach { film ->
                Log.d("show film professionKey", film.professionKey.toString())
                val state = viewModel.fetchFilmDirectly(film.filmId)

                if (state is ScreenState.Success) {
                    val fetchedFilm = state.data

                    filmsAllInfo.add(fetchedFilm)
                    Log.d("show film fetched ID", fetchedFilm.kinopoiskId.toString())
                } else if (state is ScreenState.Error) {
                    Log.e("Film fetch error", "Error fetch ${film.filmId}: ${state.message}")
                }
            }
//            Log.d("All films", filmsAllInfo.size.toString())
//            for (film in filmsAllInfo) {
//                Log.d("film.kinopoiskId", film.kinopoiskId.toString())
//            }
        }
    }


    var rowMaxWidth by remember { mutableStateOf(0.dp) }

    Scaffold(
        topBar = {
            DetailPageHeader(actorName, onBackClicked = onBackClicked)
        }
    ) { innerPaddingListPage ->
        when (screenState) {
            is ScreenState.Initial -> {
                Text("Press the button to load film collections.")
            }

            is ScreenState.Error -> {
                Text("Error : ${screenState.message}", color = Color.Red)
            }

            ScreenState.Loading -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = innerPadding.calculateBottomPadding(),
                            top = innerPaddingListPage.calculateTopPadding()
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()

                }
            }

            is ScreenState.Success -> {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = innerPaddingListPage.calculateTopPadding(),
                            start = 10.dp,
                            end = 10.dp
                        ),
                ) {
                    items(professions) { profession ->
                        Button(
                            onClick = {
                                professionKey = profession
                            },
                            modifier = Modifier.padding(horizontal = 2.dp)
                        ) {
                            Text(text = getRusProfessionKey(profession))

                        }
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { layoutCoordinates ->
                            rowMaxWidth = layoutCoordinates.size.width.dp
                        }
                        .padding(
                            top = innerPaddingListPage.calculateTopPadding() + 60.dp,
                            start = rowMaxWidth / 18,
                            end = rowMaxWidth / 20
                        ),
                    horizontalArrangement = Arrangement.Center
                ) {

                    items(filmsAllInfo) { film: Film ->
                        FilmView(film, onFilmClicked)
                    }
                    item {
                        Spacer(modifier = Modifier.padding(40.dp))
                    }
                }
            }
        }
    }


}

fun getRusProfessionKey(key: String): String {
    if(key == "ACTOR"){
        return "Актер"
    }else if(key == "HRONO_TITR_MALE"){
        return "Актер дубляжа"
    }else if(key == "HRONO_TITR_FEMALE"){
        return "Актиса дубляжа"
    }else if(key == "HERSELF"){
        return "Актиса: играет саму себя"
    }else if(key == "HIMSELF"){
        return "Актер: играет саму себя"
    }else if(key == "PRODUCER"){
        return "Продюсер"
    }else if(key == "WRITER"){
        return "Писатель"
    }else if(key == "DIRECTOR"){
        return "Директор"
    }
    return key
}
