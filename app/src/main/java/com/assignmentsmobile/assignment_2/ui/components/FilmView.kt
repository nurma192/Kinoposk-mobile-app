package com.assignmentsmobile.assignment_2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.data.Film

@Composable
fun FilmView(
    film: Film,
    onFilmClicked: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier.clickable(onClick = { onFilmClicked(film.nameRu) })
    ) {
        val colorModifier: List<Color>
//        if (!film.isShowing) {
            colorModifier = listOf(
                Color(181, 181, 201, 102),
                Color(61, 59, 255, 102)
            )
//        } else {
//            colorModifier = listOf(
//                Color(181, 181, 201, 102),
//                Color(181, 181, 201, 102)
//            )
//        }
        Box(
            modifier = Modifier
                .size(width = 111.dp, height = 156.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .background(
                    Brush.verticalGradient(
                        colors = colorModifier,
                        startY = 0f,
                        endY = 100f
                    )
                )
                .padding(top = 6.dp, end = 6.dp, bottom = 6.dp),
            contentAlignment = Alignment.TopEnd
        ) {
//            if (film.isShowing) {
                RatingView(film.ratingKinopoisk.toString())
//            } else {
//                Column(
//                    modifier = Modifier
//                        .fillMaxHeight(),
//                    verticalArrangement = Arrangement.SpaceBetween,
//                    horizontalAlignment = Alignment.End
//                ) {
//                    RatingView(film.ratingKinopoisk)
//
//                    Image(
//                        painter = painterResource(id = R.drawable.ic_eyes),
//                        contentDescription = "Ic-eye"
//                    )
//
//                }
//            }
        }

        Text(
            text = film.nameRu,
            modifier = Modifier.padding(top = 8.dp),
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 14.sp
            )
        )
        Text(
            text = film.nameEn.toString(),
            modifier = Modifier.padding(top = 2.dp),
            style = TextStyle(
                color = Color(0xff838390),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 12.sp
            )
        )

    }
}

@Composable
fun RatingView(rating: String) {
    Box(
        modifier = Modifier
            .background(Color(0xff3d3bff), shape = RoundedCornerShape(10.dp))
            .padding(vertical = 2.dp, horizontal = 4.dp)

    ) {
        Text(
            text = rating,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 6.sp,
                color = Color(0xffffffff),
            )
        )
    }
}