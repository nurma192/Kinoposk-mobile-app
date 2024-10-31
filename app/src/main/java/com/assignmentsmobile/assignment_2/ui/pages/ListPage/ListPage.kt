package com.assignmentsmobile.assignment_2.ui.pages.ListPage

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.ui.components.AppHeader2

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListPage(
    filmType: String?,
//    films: List<Film>
) {
    var rowMaxWidth by remember { mutableStateOf(0.dp) }

    Scaffold (
        topBar = {
            AppHeader2(filmType)
        }
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    rowMaxWidth = layoutCoordinates.size.width.dp
                }
                .padding(top = 100.dp, start = rowMaxWidth / 18, end = rowMaxWidth / 22),
        ) {
            items(10) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 111.dp, height = 156.dp)
                            .clip(shape = RoundedCornerShape(4.dp))
                            .padding(top = 6.dp, end = 6.dp, bottom = 6.dp)
                            .background(Color(181, 181, 201, 102))
                            .clickable {  },
                        contentAlignment = Alignment.TopEnd,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(text = "7.8")
                        }
                    }
                    Text(
                        text = "Hello",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(
                            color = Color(0xff272727),
                            fontFamily = FontFamily(Font(R.font.graphik_regular)),
                            fontSize = 14.sp
                        )
                    )
                    Text(
                        text = "NoHello",
                        modifier = Modifier.padding(top = 2.dp),
                        style = TextStyle(
                            color = Color(0xff838390),
                            fontFamily = FontFamily(Font(R.font.graphik_regular)),
                            fontSize = 12.sp
                        )
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.padding(40.dp))
            }
        }
    }
}