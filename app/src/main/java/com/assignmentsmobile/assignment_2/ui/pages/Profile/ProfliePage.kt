package com.assignmentsmobile.assignment_2.ui.pages.Profile

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.ui.components.FilmView
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip

data class Collection(val name: String, val icon: Int, val count: Int)

@Composable
@Preview(showBackground = true)
fun ProfilePage() {
    // Example list of films for preview purposes
    val films = listOf(
        Film(
            kinopoiskId = 123456,
            imdbId = "tt1234567",
            nameRu = "Фильм 1",
            nameEn = "Film 1",
            nameOriginal = "Original Film 1",
            countries = listOf(),
            genres = listOf(),
            ratingKinopoisk = 7.8,
            ratingImdb = 7.5,
            year = 2023,
            type = "Movie",
            posterUrl = "",
            posterUrlPreview = "",
            coverUrl = "",
            logoUrl = "",
            description = "This is a detailed description of the film.",
            ratingAgeLimits = "16+",
            filmLength = 120,
            shortDescription = "A short description of the movie.",
            professionKey = "director"
        ),
        Film(
            kinopoiskId = 789012,
            imdbId = "tt7890123",
            nameRu = "Фильм 2",
            nameEn = "Film 2",
            nameOriginal = "Original Film 2",
            countries = listOf(),
            genres = listOf(),
            ratingKinopoisk = 8.2,
            ratingImdb = 8.0,
            year = 2022,
            type = "Movie",
            posterUrl = "",
            posterUrlPreview = "",
            coverUrl = "",
            logoUrl = "",
            description = "A drama film about love and sacrifice.",
            ratingAgeLimits = "18+",
            filmLength = 95,
            shortDescription = "A romantic drama about two souls finding each other.",
            professionKey = "actor"
        )
    )

    // UI Layout
    Column(modifier = Modifier.fillMaxSize().padding(top = 54.dp, start = 25.dp, end = 20.dp).verticalScroll(
        rememberScrollState()
    )) {

        // Просмотрено Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Просмотрено", style = TextStyle(
                    color = Color(0xff272727),
                    fontFamily = FontFamily(Font(R.font.graphik_bold)),
                    fontSize = 18.sp
                )
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "15", style = TextStyle(
                        color = Color(0xff3d3bff),
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontSize = 14.sp
                    )
                )
                IconButton(modifier = Modifier.size(18.dp), onClick = {}) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "Icon_Arrow_Right"
                    )
                }
            }
        }

        // Films LazyRow
        LazyRow(
            modifier = Modifier.padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(films) { film ->
                // Call the FilmView component for each film in the list
                FilmView(film = film, onFilmClicked = { kinopoiskId ->
                    // Handle the click (e.g., navigate to the film's details page)
                })
            }
        }

        // Коллекции Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Коллекции", style = TextStyle(
                    color = Color(0xff272727),
                    fontFamily = FontFamily(Font(R.font.graphik_bold)),
                    fontSize = 18.sp
                )
            )
        }

        // Create New Collection Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle add new collection */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Add Collection",
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = "Создать свою коллекцию", style = TextStyle(
                    color = Color(0xff272727),
                    fontFamily = FontFamily(Font(R.font.graphik_bold)),
                    fontSize = 14.sp
                )
            )
        }
        CollectionsGrid()
        // Вами было интересно Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Вам было интересно", style = TextStyle(
                    color = Color(0xff272727),
                    fontFamily = FontFamily(Font(R.font.graphik_bold)),
                    fontSize = 18.sp
                )
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "15", style = TextStyle(
                        color = Color(0xff3d3bff),
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontSize = 14.sp
                    )
                )
                IconButton(modifier = Modifier.size(18.dp), onClick = {}) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "Icon_Arrow_Right"
                    )
                }
            }
        }

        LazyRow(
            modifier = Modifier.padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(films) { film ->
                // Call the FilmView component for each film in the list
                FilmView(film = film, onFilmClicked = { kinopoiskId ->
                    // Handle the click (e.g., navigate to the film's details page)
                })
            }
        }
    }
}

@Composable
fun CollectionsGrid() {
    val collections = listOf(
        Collection("Любимые", R.drawable.ic_like, 105),
        Collection("Хочу посмотреть", R.drawable.ic_flag, 120),
        Collection("Русское кино", R.drawable.ic_profile, 75)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(collections) { collection ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(10.dp)),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = Color.White),


            ) {
                Box(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = collection.icon),
                            contentDescription = collection.name,
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = collection.name,
                            style = TextStyle(
                                color = Color(0xff272727),
                                fontFamily = FontFamily(Font(R.font.graphik_bold)),
                                fontSize = 14.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .height(24.dp)
                                .width(40.dp)
                                .clickable(onClick = { /* Handle click here */ })
                                .background(Color(0xff3d3bff), shape = RoundedCornerShape(14.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = collection.count.toString(),
                                style = TextStyle(
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                                    fontSize = 8.sp
                                )
                            )
                        }


                    }
                    IconButton(
                        onClick = { /* Handle close button click */ },
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_delete_x),
                            contentDescription = "Close Icon"
                        )
                    }
                }
            }
        }
    }
}

