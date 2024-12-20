package com.assignmentsmobile.assignment_2.ui.pages.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.assignmentsmobile.assignment_2.data.Section
import com.assignmentsmobile.assignment_2.ui.components.AppHeader
import com.assignmentsmobile.assignment_2.ui.components.FilmView
import com.assignmentsmobile.assignment_2.ui.states.ScreenState

@Composable
fun HomePage(
    innerPadding: PaddingValues,
    onFilmClicked: (Int) -> Unit = {},
    onFilmTypeClicked: (String) -> Unit = {},
    screenState: ScreenState<List<Section>>

) {
    Scaffold(
        topBar = {
            AppHeader()
        }
    ) { innerPaddingHomePage ->
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
                            top = innerPaddingHomePage.calculateTopPadding()
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            is ScreenState.Success -> {
                val sections = screenState.data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = innerPaddingHomePage.calculateTopPadding(),
                            start = 26.dp,
                        ),
                    verticalArrangement = Arrangement.spacedBy(36.dp)
                ) {
                    items(sections) { section ->
                        SectionView(section, onFilmTypeClicked, onFilmClicked)
                    }
                    item{
                        Spacer(modifier = Modifier.padding(bottom = 80.dp))
                    }

                }

            }
        }
    }
}


@Composable
fun SectionView(
    section: Section,
    onFilmTypeClicked: (String) -> Unit,
    onFilmClicked: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = section.sectionName,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontSize = 20.sp
                )
            )
            Text(
                text = "Все",
                modifier = Modifier
                    .padding(end = 26.dp)
                    .clickable(onClick = { onFilmTypeClicked(section.sectionName) }),
                style = TextStyle(
                    color = Color(0xff3d3bff),
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 16.sp
                )
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(section.list.take(7)) { film: Film ->
                FilmView(film, onFilmClicked)
            }
            item {
                SeeAllButton(section.sectionName, onFilmTypeClicked)
            }
        }
    }
}


@Composable
fun SeeAllButton(sectionName: String, onFilmTypeClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .size(width = 111.dp, height = 156.dp)
            .padding(top = 51.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            modifier = Modifier
                .size(32.dp),
            onClick = {
                onFilmTypeClicked(sectionName)
            }
        ) {
            Image(
                modifier = Modifier
                    .background(Color(0xffffffff), shape = CircleShape)
                    .padding(6.dp)
                    .size(20.dp),
                painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = "Ic_Arrow"
            )
        }
        Text(
            text = "Показать все",
            modifier = Modifier.clickable { },
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 12.sp
            )
        )
    }
}

