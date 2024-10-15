package com.assignmentsmobile.assignment_2

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomepageScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 26.dp, end = 26.dp, top = 55.dp, bottom = 0.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        Image(
            modifier = Modifier.padding(bottom = 17.dp),
            painter = painterResource(id = R.drawable.ic_skillcinema),
            contentDescription = "Icon_Skillcinema"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Section()
        }
    }
}

@Composable
fun Section() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Премьеры",
                style = TextStyle(
                    color = Color(0xff272727),
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontSize = 20.sp
                )
            )
            Text(
                text = "Все",
                modifier = Modifier.clickable { },
                style = TextStyle(
                    color = Color(0xff3d3bff),
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 16.sp
                )
            )
        }
        FilmView()
        LazyRow {

        }
    }
}

@Composable
fun FilmView() {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Box(//box for test but there is should be image
            modifier = Modifier
                .size(width = 111.dp, height = 156.dp)
                .background(Color.Green)
                .padding(start = 88.dp, top = 6.dp, end = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(width = 17.dp, height = 10.dp)
                    .background(Color(0xff3d3bff))
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "7.8",
                    style = TextStyle(
                        color = Color(0xffffffff),
                        fontFamily = FontFamily(Font(R.font.graphik_regular)),
                        fontSize = 6.sp
                    )
                )
            }
        }
        Text(modifier = Modifier.padding(top = 6.dp),
            text = "Близкие",
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 14.sp
            )
        )
        Text(
            text = "драма",
            style = TextStyle(
                color = Color(0xff838390),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 12.sp
            )
        )
    }
}

//data class FilmItem (
//    val id: Int,
//    val imagePath: Int,
//    val rating: String,
//    val filmName: String,
//    val genre: String
//)
