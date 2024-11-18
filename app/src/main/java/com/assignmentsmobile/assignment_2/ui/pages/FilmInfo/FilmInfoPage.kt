
package com.assignmentsmobile.assignment_2.ui.pages.FilmInfo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.FilmImagesList
import com.assignmentsmobile.assignment_2.data.SimilarFilmList
import com.assignmentsmobile.assignment_2.data.Staff
import com.assignmentsmobile.assignment_2.data.StaffList
import com.assignmentsmobile.assignment_2.data.domain.KinopoiskDomain
import com.assignmentsmobile.assignment_2.data.toFilm
import com.assignmentsmobile.assignment_2.data.viewmodel.FilmInfoViewModel
import com.assignmentsmobile.assignment_2.data.viewmodel.FilmInfoViewModelFactory
import com.assignmentsmobile.assignment_2.ui.components.CoilImage
import com.assignmentsmobile.assignment_2.ui.components.FilmView
import com.assignmentsmobile.assignment_2.ui.states.ScreenState

@Composable
fun FilmInfoPage(
    filmId: Int,
    onBackClicked: () -> Unit,
    onFilmClicked: (Int) -> Unit = {},
    viewModel: FilmInfoViewModel = viewModel(
        factory = FilmInfoViewModelFactory(
            KinopoiskDomain.filmApiService,
            KinopoiskDomain.staffApiService,
            KinopoiskDomain.filmImagesApiService,
            KinopoiskDomain.similarFilmsApiService,
        )
    ),
    onGalleryClicked: (FilmImagesList) -> Unit = {},
    onActorClicked: (Int) -> Unit = {}
) {
    val filmInfoState by viewModel.filmInfoState.collectAsState()
    val staffInfoState by viewModel.staffInfoState.collectAsState()
    val filmImagesState by viewModel.filmImagesState.collectAsState()
    val similarFilmState by viewModel.similarFilmState.collectAsState()

    LaunchedEffect(filmId) {
        viewModel.getFilmById(filmId)
        viewModel.getStaffById(filmId)
        viewModel.getFilmImages(filmId)
        viewModel.getSimilarFilms(filmId)
    }

    val film = (filmInfoState as? ScreenState.Success<Film>)?.data
    val staffList = (staffInfoState as? ScreenState.Success<StaffList>)?.data
    val filmImagesList = (filmImagesState as? ScreenState.Success<FilmImagesList>)?.data
    val similarFilmsList = (similarFilmState as? ScreenState.Success<SimilarFilmList>)?.data

    if (film == null) {
        when (filmInfoState) {
            is ScreenState.Initial -> return
            is ScreenState.Error -> {
                Text(text = "Error: ${(filmInfoState as ScreenState.Error).message}")
                return
            }
            is ScreenState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
                return
            }
            is ScreenState.Success -> return
        }
    }

    if (staffList == null) {
        when (staffInfoState) {
            is ScreenState.Initial -> return
            is ScreenState.Error -> {
                Text(text = "Error: ${(staffInfoState as ScreenState.Error).message}")
                return
            }
            is ScreenState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
                return
            }
            is ScreenState.Success -> return
        }
    }

    if (filmImagesList == null) {
        when (filmImagesState) {
            is ScreenState.Initial -> return
            is ScreenState.Error -> {
                Text(text = "Error: ${(filmImagesState as ScreenState.Error).message}")
                return
            }
            is ScreenState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
                return
            }
            is ScreenState.Success -> return
        }
    }

    if (similarFilmsList == null) {
        when (similarFilmState) {
            is ScreenState.Initial -> return
            is ScreenState.Error -> {
                Text(text = "Error: ${(similarFilmState as ScreenState.Error).message}")
                return
            }
            is ScreenState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
                return
            }
            is ScreenState.Success -> return
        }
    }

    FilmInfoContent(
        film = film,
        staffList = staffList,
        filmImagesList = filmImagesList,
        similarFilmsList = similarFilmsList,
        onBackClicked = onBackClicked,
        onFilmClicked = onFilmClicked,
        onGalleryClicked = onGalleryClicked,
        onActorClicked = onActorClicked
    )

}

@Composable
fun FilmInfoContent(
    film: Film,
    staffList: StaffList,
    filmImagesList: FilmImagesList,
    similarFilmsList: SimilarFilmList,
    onBackClicked: () -> Unit,
    onFilmClicked: (Int) -> Unit = {},
    onGalleryClicked: (FilmImagesList) -> Unit,
    onActorClicked: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
    ) {
        Header(film, onBackClicked)
        InFilmActor(staffList, onActorClicked)
        InFilmWorker(staffList)
        FilmGallery(filmImagesList, onGalleryClicked)
        SimilarFilms(similarFilmsList, film, onFilmClicked)
    }
}

@Composable
fun InFilmActor(
    staffList: StaffList,
    onActorClicked: (Int) -> Unit = {}
){
    RowInfo("В фильме снимались", staffList.actors.size.toString())
    Spacer(modifier = Modifier.height(32.dp))
    LazyHorizontalGrid(
        rows = GridCells.Fixed(4),
        modifier = Modifier.height(300.dp)
    ) {
        items(staffList.actors.take(20)) { actor ->
            PersonView(
                otherStaff = actor,
                onActorClicked = onActorClicked
            )
        }

    }
    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
fun InFilmWorker(
    staffList: StaffList
){
    RowInfo("Над фильмом работали", staffList.otherStaff.size.toString())
    Spacer(modifier = Modifier.height(32.dp))
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.height(150.dp)
    ) {
        items(staffList.otherStaff.take(10)) { otherStaff ->
            PersonView(otherStaff, {})
        }
    }
    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
fun PersonView(
    otherStaff: Staff,
    onActorClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 26.dp)
            .width(205.dp).clickable { onActorClicked(otherStaff.staffId) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .clickable { onActorClicked(otherStaff.staffId)}
                .size(width = 49.dp, height = 68.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .background(Color(0x66b5b5c9))
        ) {
            if(otherStaff.posterUrl != null && otherStaff.posterUrl != "")
                CoilImage(
                    url = otherStaff.posterUrl,
                    contentScale = ContentScale.Crop
                )
        }
        Column(modifier = Modifier.padding(start = 16.dp)) {
            if(otherStaff.nameRu != null && otherStaff.nameRu != "")
                Text(
                    modifier = Modifier.clickable { onActorClicked(otherStaff.staffId) },
                    text = otherStaff.nameRu, style = TextStyle(
                        color = Color(0xff272727),
                        fontFamily = FontFamily(Font(R.font.graphik_regular)),
                        fontSize = 14.sp
                    )
                )
            if(otherStaff.professionText != null && otherStaff.professionText != "")
                Text(
                    modifier = Modifier.clickable { onActorClicked(otherStaff.staffId) },
                    text = otherStaff.professionText, style = TextStyle(
                        color = Color(0xff838390),
                        fontFamily = FontFamily(Font(R.font.graphik_regular)),
                        fontSize = 12.sp
                    )
                )
        }
    }
}


@Composable
fun FilmGallery(
    filmImagesList: FilmImagesList,
    onGalleryClicked: (FilmImagesList) -> Unit
){
    RowInfo("Галерея", filmImagesList.total.toString(), Modifier.clickable { onGalleryClicked(filmImagesList) })
    Spacer(modifier = Modifier.height(32.dp))
    LazyRow(
        modifier = Modifier.padding(start = 26.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filmImagesList.items.take(7)) { item ->
            if(item.imageUrl != null && item.imageUrl != "")
                Box(
                    Modifier
                        .size(width = 192.dp, height = 108.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(Color(0x40b5b5c9))
                ) {
                    CoilImage(
                        url = item.imageUrl,
                        contentScale = ContentScale.Crop
                    )
                }
        }
    }
    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
fun SimilarFilms(
    similarFilmsList: SimilarFilmList,
    film: Film,
    onFilmClicked: (Int) -> Unit = {}
){
    RowInfo("Похожие фильмы", similarFilmsList.total.toString())
    Spacer(modifier = Modifier.height(32.dp))
    LazyRow(
        modifier = Modifier.padding(start = 26.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(similarFilmsList.items) { item ->
            val filmViewItem: Film = item.toFilm(film.genres)
            FilmView(filmViewItem, onFilmClicked)
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 80.dp))
}


@Composable
fun FilmText(text: String){
    Text(
        modifier = Modifier.padding(horizontal = 26.dp),
        text = text,
        style = TextStyle(
            color = Color(0xff272727),
            fontFamily = FontFamily(Font(R.font.graphik_medium)),
            fontSize = 16.sp
        )
    )
}

@Composable
fun RowInfo(
    text: String,
    amount: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text, style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_bold)),
                fontSize = 18.sp
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Text(
                modifier = modifier,
                text = amount, style = TextStyle(
                    color = Color(0xff3d3bff),
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontSize = 14.sp
                )
            )
            IconButton(modifier = Modifier.size(18.dp), onClick = {}) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Icon_Arrow_Right",
                    modifier = modifier
                )
            }
        }

    }
}

@Composable
fun Header(
    film: Film,
    onBackClicked: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0x001b1b1b), Color(0xff1b1b1b)),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        var cover = ""
        if(film.coverUrl != null && film.coverUrl != ""){
            CoilImage(
                url = film.coverUrl,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        else if (film.posterUrl != null && film.posterUrl != "") {
            CoilImage(
                url = film.posterUrl,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            cover = "poster"
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 42.dp, start = 26.dp)
        ) {
            IconButton(modifier = Modifier.size(24.dp), onClick = {
                onBackClicked()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Icon_Arrow_Left"
                )
            }
        }
        Column(
            modifier = Modifier.padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(film.logoUrl != null && film.logoUrl != "" && cover != "poster"){
                CoilImage(
                    url = film.logoUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )
            }
            else{
                Spacer(modifier = Modifier.height(200.dp).fillMaxWidth())
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = film.ratingImdb.toString(), style = TextStyle(
                        color = Color(0xffb5b5c9),
                        fontFamily = FontFamily(Font(R.font.graphik_bold)),
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = "  " + film.nameRu, style = TextStyle(
                        color = Color(0xffb5b5c9),
                        fontFamily = FontFamily(Font(R.font.graphik_bold)),
                        fontSize = 16.sp
                    )
                )
            }
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = film.year.toString() + ", " + film.genres.joinToString { it.genre },
                style = TextStyle(
                    color = Color(0xffb5b5c9),
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 14.sp
                )
            )
            Text(
                modifier = Modifier.padding(bottom = 5.dp),
                text = film.countries.joinToString { it.country } + ", " +
                        "${if (film.filmLength >= 60) "${film.filmLength / 60} ч ${film.filmLength % 60} мин" else "${film.filmLength % 60} мин"}, " +
                        film.ratingAgeLimits.filter { it.isDigit() } + "+",
                style = TextStyle(
                    color = Color(0xffb5b5c9),
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 14.sp
                )
            )
            Row(
                modifier = Modifier.padding(top = 8.dp, bottom = 17.dp),
                horizontalArrangement = Arrangement.spacedBy(22.dp)
            ) {

                IconButton(modifier = Modifier.size(18.dp), onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = "Icon_Like"
                    )
                }
                IconButton(modifier = Modifier.size(18.dp), onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_flag),
                        contentDescription = "Icon_Flag"
                    )
                }
                IconButton(modifier = Modifier.size(18.dp), onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = "Icon_Eye"
                    )
                }
                IconButton(modifier = Modifier.size(18.dp), onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = "Icon_Share"
                    )
                }
                IconButton(modifier = Modifier.size(18.dp), onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = "Icon_More"
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(40.dp))
    var shortDescription = ""
    if(film.shortDescription != null && film.shortDescription != "") shortDescription = film.shortDescription
    FilmText(shortDescription)
    Spacer(modifier = Modifier.height(20.dp))
    var desctiption = ""
    if(film.description != null && film.description != "") desctiption = film.description
    FilmText(desctiption)
    Spacer(modifier = Modifier.height(40.dp))
}