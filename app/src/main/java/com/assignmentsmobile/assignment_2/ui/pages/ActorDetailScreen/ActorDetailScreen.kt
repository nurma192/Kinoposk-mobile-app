package com.assignmentsmobile.assignment_2.ui.pages

import ActorInfoViewModel
import ActorInfoViewModelFactory
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.data.Actor
import com.assignmentsmobile.assignment_2.ui.components.CoilImage
import com.assignmentsmobile.assignment_2.ui.components.FilmView
import com.assignmentsmobile.assignment_2.ui.states.ScreenState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.viewmodel.compose.viewModel
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.domain.KinopoiskDomain
import com.assignmentsmobile.assignment_2.data.repository.ActorRepository
import com.assignmentsmobile.assignment_2.data.toFilm
import com.assignmentsmobile.assignment_2.ui.components.DetailPageHeader
import com.assignmentsmobile.assignment_2.ui.pages.HomePage.SeeAllButton

@Composable
fun ActorPage(
    viewModel: ActorInfoViewModel = viewModel(
        factory = ActorInfoViewModelFactory()
    ),
    id: Int,
    mainPadding: PaddingValues,
    onBackClicked: () -> Unit = {},
    onFilmClicked: (Int) -> Unit = {}
) {
    val actorDetailState by viewModel.actorDetailState.collectAsState()

    LaunchedEffect(id) {
        viewModel.getActorDetailById(id)
    }

    when (actorDetailState) {
        is ScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is ScreenState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (actorDetailState as ScreenState.Error).message,
                    color = Color.Red
                )
            }
        }
        is ScreenState.Success -> {
            val actor = (actorDetailState as? ScreenState.Success<Actor>)!!.data
            Scaffold(
                topBar = {
                    DetailPageHeader("", onBackClicked)
                }
            ) { innerPadding ->
                ActorPageContent(
                    actor,
                    Modifier.padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = mainPadding.calculateBottomPadding()
                    ),
                    onFilmClicked = onFilmClicked
                )
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No data available.")
            }
        }
    }
}

@Composable
fun ActorPageContent(actor: Actor, modifier: Modifier = Modifier, onFilmClicked: (Int) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        ActorHeader(actor)

        SectionTopic("Лучшее", "Все >")
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(actor.films.take(5)) { actorFilm ->
                val film = actorFilm.toFilm()
                FilmView(film, onFilmClicked = onFilmClicked)
            }
            item {
                SeeAllButton("Best", {})
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SectionTopic("Фильмография", "К списку >")
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = actor.films.size.toString() + " films",
            style = TextStyle(
                color = Color(0xff838390),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 12.sp
            )
        )
    }
}

@Composable
fun SectionTopic(name: String, link: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = link,
            color = Color(0xff3d3bff),
            fontSize = 14.sp
        )
    }
}

@Composable
fun ActorHeader(actor: Actor) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 200.dp )
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray)
        ) {
            CoilImage(
                url = actor.posterUrl,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = actor.nameRu,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = actor.profession,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}
