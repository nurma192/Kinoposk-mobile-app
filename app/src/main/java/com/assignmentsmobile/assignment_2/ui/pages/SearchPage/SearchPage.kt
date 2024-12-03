package com.assignmentsmobile.assignment_2.ui.pages.SearchPage

import SearchFilmsRepository
import SearchFilmsViewModel
import SearchFilmsViewModelFactory
import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.data.SearchedFilm
import com.assignmentsmobile.assignment_2.ui.components.CoilImage
import com.assignmentsmobile.assignment_2.ui.components.SearchBar
import com.assignmentsmobile.assignment_2.ui.states.ScreenState
import kotlin.math.log

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchPage(
    onFilterClicked: () -> Unit,
    searchFilmsRepository: SearchFilmsRepository,
    onFilmClicked: (Int) -> Unit = {},
) {
    val viewModel: SearchFilmsViewModel = viewModel(
        factory = SearchFilmsViewModelFactory(searchFilmsRepository)
    )
    val filmsState by viewModel.filmsState.collectAsState()
    var page by remember { mutableIntStateOf(1) }
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchBar(
                onFilterClicked,
                onSubmit = { keyword ->
                    viewModel.searchFilms(keyword, 1)
                },
                searchText,
                onSearchTextChanged = { newText ->
                    searchText = newText
                }
            )
        }
    ) { innerPaddingSearchPage ->
        when (val state = filmsState) {
            is ScreenState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            is ScreenState.Error -> {
                Text(text = (filmsState as ScreenState.Error).message)
            }

            is ScreenState.Success -> {
                val data = state.data

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(
                        top = innerPaddingSearchPage.calculateTopPadding() + 15.dp,
                        bottom = innerPaddingSearchPage.calculateBottomPadding() + 60.dp,
                        start = 26.dp,
                        end = 26.dp
                    )
                ) {

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        if (data.films.isEmpty()) {
                            item {
                                Text("Результатов не найдено")
                            }
                        } else {
                            logFilms(data.films)
                            items(data.films) { film: SearchedFilm ->
                                FilmViewInSearch(film, onFilmClicked)
                            }
                        }
                    }

                    if(data.films.isNotEmpty()){
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = {
                                if(page != 1){
                                    page--
                                    viewModel.searchFilms(searchText, page)
                                }
                            }) {
                                Text("< Prevpage")
                            }
                            Text("${page}/${data.pagesCount + 1}")
                            Button(onClick = {
                                if(page != data.pagesCount+1){
                                    page++
                                    viewModel.searchFilms(searchText, page)
                                }
                            }) {
                                Text("Nextpage >")
                            }
                        }
                    }
                }
            }

            else -> {
                Box(
                    modifier = Modifier.padding(
                        top = innerPaddingSearchPage.calculateTopPadding() + 15.dp,
                        bottom = innerPaddingSearchPage.calculateBottomPadding(),
                        start = 26.dp,
                        end = 26.dp
                    )
                ) {
                    Text("Введите запрос для поиска фильмов")
                }
            }

        }

    }
}

@Composable
fun FilmViewInSearch(film: SearchedFilm, onFilmClicked: (Int) -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.clickable(onClick = { onFilmClicked(film.filmId) })
    ) {
        Box(
            modifier = Modifier
                .size(width = 114.dp, height = 150.dp)
                .clip(shape = RoundedCornerShape(6.dp))
                .background(Color(0x60b5b5c9))
                .padding(top = 6.dp, start = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(width = 24.dp, height = 15.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(Color(0xffffffff))
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = film.rating ?: "0",
                    style = TextStyle(
                        color = Color(0xff272727),
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontSize = 8.sp
                    )
                )
            }
            if (film.posterUrl != "") {
                CoilImage(
                    url = film.posterUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Column {
            Text(
                text = film.nameRu ?: film.nameEn ?: "NameRu",
                style = TextStyle(
                    color = Color(0xff272727),
                    fontFamily = FontFamily(Font(R.font.graphik_bold)),
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${film.year ?: "year"}, ${film.genres[0].genre ?: "genre"}",
                style = TextStyle(
                    color = Color(0xff838390),
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 14.sp
                )
            )
        }
    }
}

fun logFilms(films: List<SearchedFilm>) {
    films.forEachIndexed { index, film ->
        Log.d(
            "Film $index", """
            Name (RU): ${film.nameRu}
            Name (EN): ${film.nameEn}
            Type: ${film.type}
            Year: ${film.year}
            Description: ${film.description}
            Film Length: ${film.filmLength}
            Rating: ${film.rating}
            Rating Votes: ${film.ratingVoteCount}
           
            Poster URL: ${film.posterUrl}
            Poster Preview: ${film.posterUrlPreview}
        """.trimIndent()
        )
    }
}
