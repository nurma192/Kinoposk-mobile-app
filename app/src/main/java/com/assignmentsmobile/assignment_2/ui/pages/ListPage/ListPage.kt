package com.assignmentsmobile.assignment_2.ui.pages.ListPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.Section
import com.assignmentsmobile.assignment_2.ui.components.DetailPageHeader
import com.assignmentsmobile.assignment_2.ui.components.FilmView
import com.assignmentsmobile.assignment_2.ui.pages.HomePage.SectionView
import com.assignmentsmobile.assignment_2.ui.states.ScreenState

@Composable

fun ListPage(
    filmType: String?,
    onBackClicked: () -> Unit,
    onFilmClicked: (String) -> Unit = {},
    screenState: ScreenState<List<Section>>,
    innerPadding: PaddingValues,

) {
    var rowMaxWidth by remember { mutableStateOf(0.dp) }

    Scaffold(
        topBar = {
            DetailPageHeader(filmType, onBackClicked = onBackClicked)
        }
    ) { innerPaddingListPage ->
        when(screenState){
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
                val sections = screenState.data
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { layoutCoordinates ->
                            rowMaxWidth = layoutCoordinates.size.width.dp
                        }
                        .padding(
                            top = innerPaddingListPage.calculateTopPadding(),
                            start = rowMaxWidth / 20,
                            end = rowMaxWidth / 20
                        ),
                ) {

                    val thisSection =  sections.find { item -> item.sectionName == filmType }
                    if(thisSection != null){
                        items(thisSection.list) { film: Film ->
                            FilmView(film, onFilmClicked)
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.padding(40.dp))
                    }
                }
            }
        }

    }
//    Column(
//        modifier = Modifier.padding(top = 30.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//
//    }
}