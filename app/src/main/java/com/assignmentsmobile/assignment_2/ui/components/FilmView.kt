package com.assignmentsmobile.assignment_2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
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
        modifier = Modifier
            .clickable(onClick = { onFilmClicked(film.nameRu) })
    ) {
        val colorModifier: List<Color> = listOf(
            Color(181, 181, 201, 102),
            Color(61, 59, 255, 102)
        )

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
                ,
            contentAlignment = Alignment.TopEnd
        ) {
            if(film.coverUrl != null){
                CoilImage(
                    url = film.coverUrl,
                    modifier = Modifier.fillMaxSize()
                )
            }

            if (getFilmRating(film) != 0.0) {
                RatingView(getFilmRating(film), Modifier.padding(top = 6.dp, end = 6.dp))
            }else{
                RatingView(10.0, Modifier.padding(top = 6.dp, end = 6.dp))
            }
        }

        Text(
            text = if(film.nameRu.length > 20) "${film.nameRu.take(20)}..." else film.nameRu,
            modifier = Modifier.padding(top = 8.dp).widthIn(max = 111.dp).height(35.dp),
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 14.sp
            )
        )

        Text(
            text = film.genres[0].genre,
            modifier = Modifier.padding(top = 2.dp),
            style = TextStyle(
                color = Color(0xff838390),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 12.sp
            )
        )
    }
}


fun getFilmRating(film: Film): Double {
    if(film.ratingKinopoisk != 0.0){
        return film.ratingKinopoisk
    }
    return film.ratingImdb
}

@Composable
fun RatingView(rating: Double, modifier: Modifier) {
    Box(
        modifier = modifier
            .background(Color(0xff3d3bff), shape = RoundedCornerShape(10.dp))
            .padding(vertical = 2.dp, horizontal = 4.dp)

    ) {
        Text(
            text = rating.toString(),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 8.sp,
                color = Color(0xffffffff),
            )
        )
    }
}