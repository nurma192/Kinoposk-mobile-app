package com.assignmentsmobile.assignment_2.ui.pages.SearchPage

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.ui.components.SearchBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchPage(
    onFilterClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            SearchBar(onFilterClicked)
        }
    ) { innerPaddingSearchPage ->
        //if else kosy kerek list bar zhogyna
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(
                top = innerPaddingSearchPage.calculateTopPadding() + 15.dp,
                bottom = innerPaddingSearchPage.calculateBottomPadding(),
                start = 26.dp,
                end = 26.dp
            )
        ) {
            item {
                FilmViewInSearch()
            }
            item {
                FilmViewInSearch()
            }
            item {
                FilmViewInSearch()
            }
            item {
                FilmViewInSearch()
            }
            item {
                FilmViewInSearch()
            }
            item {
                FilmViewInSearch()
            }
            item{
                Spacer(modifier = Modifier.padding(bottom = 80.dp))
            }
        }
    }
}

@Composable
fun FilmViewInSearch(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
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
                    text = "7.8",
                    style = TextStyle(
                        color = Color(0xff272727),
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontSize = 8.sp
                    )
                )
            }
        }
        Column {
            Text(
                text = "Топи",
                style = TextStyle(
                    color = Color(0xff272727),
                    fontFamily = FontFamily(Font(R.font.graphik_bold)),
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "2021, триллер",
                style = TextStyle(
                    color = Color(0xff838390),
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 14.sp
                )
            )
        }
    }
}
