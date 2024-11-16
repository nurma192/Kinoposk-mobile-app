package com.assignmentsmobile.assignment_2.ui.pages

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
import com.assignmentsmobile.assignment_2.data.viewmodel.FilmInfoViewModel
import com.assignmentsmobile.assignment_2.ui.components.CoilImage
import com.assignmentsmobile.assignment_2.ui.components.FilmView
import com.assignmentsmobile.assignment_2.ui.states.ScreenState
import androidx.compose.foundation.lazy.items
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.toFilm

@Composable
fun ActorPageScreen(viewModel: FilmInfoViewModel, actorId: Int) {
    val actorDetailState by viewModel.actorDetailState.collectAsState()

    LaunchedEffect(actorId) {
        viewModel.getActorDetailById(actorId)
    }

    when (val state = actorDetailState) {
        is ScreenState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is ScreenState.Success -> {
            ActorPageContent(state.data)
        }
        is ScreenState.Error -> {
            Text(text = "Error: ${state.message}", color = Color.Red)
        }
        ScreenState.Initial -> {
            // Optionally handle initial state or leave it as is if not needed
        }
    }
}

@Composable
fun ActorPageContent(actor: Actor) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ActorHeader(actor)

        // Лучшее Section
        Text(
            text = "Лучшее",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(actor.films.take(5)) { actorFilm ->
                val film = actorFilm.toFilm()
                FilmView(film, onFilmClicked = {})
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Фильмография Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Фильмография",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "К списку",
                color = Color(0xff3d3bff),
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(actor.films) { actorFilm ->
                val film = actorFilm.toFilm()
                FilmView(film = film, onFilmClicked = {})
            }
        }
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
                .size(100.dp)
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
                fontWeight = FontWeight.Bold
            )
            Text(
                text = actor.profession,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}
