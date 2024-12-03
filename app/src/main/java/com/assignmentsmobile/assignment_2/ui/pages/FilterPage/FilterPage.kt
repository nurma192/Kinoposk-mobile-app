package com.assignmentsmobile.assignment_2.ui.pages.FilterPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.data.Filter

@Composable
fun FilterPage(
    onBackClicked: () -> Unit,
    onCountryClicked: () -> Unit,
    onDateRangeClicked: () -> Unit,
    onGenreClicked: () -> Unit,
    filters: Filter
) {
    Column(modifier = Modifier.padding(top = 42.dp, start = 26.dp, end = 26.dp)) {
        Header(onBackClicked)
        AllFilmsSerials()
        Country(onCountryClicked,filters)
        Genre(onGenreClicked)
        Year(onDateRangeClicked)
        Rating()
        DatePopularityRating()
        NotViewed()
    }
}

@Composable
fun Header(onBackClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(modifier = Modifier.size(30.dp),
            onClick = {
                onBackClicked()
            }
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "Icon_Arrow_Left"
            )
        }
        Text(
            text = "Настройка поиска",
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                fontSize = 16.sp
            )
        )
        Box(modifier = Modifier.size(30.dp)) {}
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun AllFilmsSerials() {
    Text(
        modifier = Modifier.clickable {

        },
        text = "Показывать",
        style = TextStyle(
            color = Color(0xff838390),
            fontFamily = FontFamily(Font(R.font.graphik_regular)),
            fontSize = 16.sp
        )
    )
    Spacer(modifier = Modifier.height(24.dp))
    var rowMaxWidth by remember { mutableStateOf(0.dp) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .onGloballyPositioned { layoutCoordinates ->
            rowMaxWidth = layoutCoordinates.size.width.dp
        }) {
        val blue = Color(0xff3d3bff)
        val white = Color(0xffffffff)
        val gray = Color(0xff272727)
        var box1Color by remember { mutableStateOf(blue) }
        var box2Color by remember { mutableStateOf(white) }
        var box3Color by remember { mutableStateOf(white) }
        var box1TextColor by remember { mutableStateOf(white) }
        var box2TextColor by remember { mutableStateOf(gray) }
        var box3TextColor by remember { mutableStateOf(gray) }
        Box(
            modifier = Modifier
                .size(height = 40.dp, width = rowMaxWidth / 10)
                .clip(shape = RoundedCornerShape(topStart = 56.dp, bottomStart = 56.dp))
                .background(box1Color)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(topStart = 56.dp, bottomStart = 56.dp)
                )
                .clickable {
                    box1Color = blue
                    box2Color = white
                    box3Color = white
                    box1TextColor = white
                    box2TextColor = gray
                    box3TextColor = gray

                    //filter алу
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Все",
                style = TextStyle(
                    color = box1TextColor,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 18.sp
                )
            )
        }
        Box(
            modifier = Modifier
                .size(height = 40.dp, width = rowMaxWidth / 6)
                .background(box2Color)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val halfStroke = strokeWidth / 2
                    drawLine(
                        color = Color.Black,
                        start = androidx.compose.ui.geometry.Offset(0f, halfStroke),
                        end = androidx.compose.ui.geometry.Offset(size.width, halfStroke),
                        strokeWidth = strokeWidth
                    )
                    drawLine(
                        color = Color.Black,
                        start = androidx.compose.ui.geometry.Offset(0f, size.height - halfStroke),
                        end = androidx.compose.ui.geometry.Offset(
                            size.width,
                            size.height - halfStroke
                        ),
                        strokeWidth = strokeWidth
                    )
                }
                .clickable {
                    box1Color = white
                    box2Color = blue
                    box3Color = white
                    box1TextColor = gray
                    box2TextColor = white
                    box3TextColor = gray

                    //filter алу
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Фильмы",
                style = TextStyle(
                    color = box2TextColor,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 18.sp
                )
            )
        }
        Box(
            modifier = Modifier
                .size(height = 40.dp, width = rowMaxWidth / 8)
                .clip(shape = RoundedCornerShape(topEnd = 56.dp, bottomEnd = 56.dp))
                .background(box3Color)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(topEnd = 56.dp, bottomEnd = 56.dp)
                )
                .clickable {
                    box1Color = white
                    box2Color = white
                    box3Color = blue
                    box1TextColor = gray
                    box2TextColor = gray
                    box3TextColor = white

                    //filter алу
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Сериалы",
                style = TextStyle(
                    color = box3TextColor,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 18.sp
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(48.dp))
}

@Composable
fun Country(
    onCountryClicked: () -> Unit,
    filters: Filter
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Страна",
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
        Text(
            modifier = Modifier.clickable {
                onCountryClicked()
            },
            text = if (filters.country === "") "Все" else filters.country,
            style = TextStyle(
                color = Color(0xff838390),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .size(1.dp)
            .background(Color(0x70b5b5c9))
    )
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun Genre(
    onGenreClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Жанр",
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
        Text(
            modifier = Modifier.clickable {
                onGenreClicked()
            },
            text = "Комедия",
            style = TextStyle(
                color = Color(0xff838390),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .size(1.dp)
            .background(Color(0x70b5b5c9))
    )
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun Year(
    onDateRangeClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Год",
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
        Text(
            modifier = Modifier.clickable {
                onDateRangeClicked()
            },
            text = "с 1998 до 2017",
            style = TextStyle(
                color = Color(0xff838390),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .size(1.dp)
            .background(Color(0x70b5b5c9))
    )
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun Rating() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Рейтинг",
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
        Text(
            text = "любой",
            style = TextStyle(
                color = Color(0xff838390),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(24.dp))

    CustomRangeSlider()

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .size(1.dp)
            .background(Color(0x70b5b5c9))
    )
    Spacer(modifier = Modifier.height(24.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomRangeSlider() {
    var sliderRange by remember { mutableStateOf(0f..100f) }

    RangeSlider(
        value = sliderRange,
        onValueChange = { sliderRange = it },
        valueRange = 0f..100f,
        onValueChangeFinished = {
            //Do Filter
        },
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.Blue,
            inactiveTrackColor = Color.LightGray,
            activeTickColor = Color.Transparent,
            inactiveTickColor = Color.Transparent
        ),
        startThumb = {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.White, shape = CircleShape)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = CircleShape
                    )
            )
        },
        endThumb = {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.White, shape = CircleShape)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = CircleShape
                    )
            )
        }
    )
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 11.dp, end = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "1",
            style = TextStyle(
                color = Color(0xff838390),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
        Text(
            text = "10",
            style = TextStyle(
                color = Color(0xff838390),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun DatePopularityRating(){
    Text(
        modifier = Modifier.clickable {

        },
        text = "Сортировать",
        style = TextStyle(
            color = Color(0xff838390),
            fontFamily = FontFamily(Font(R.font.graphik_regular)),
            fontSize = 16.sp
        )
    )
    Spacer(modifier = Modifier.height(24.dp))
    var rowMaxWidth by remember { mutableStateOf(0.dp) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .onGloballyPositioned { layoutCoordinates ->
            rowMaxWidth = layoutCoordinates.size.width.dp
        }) {
        val blue = Color(0xff3d3bff)
        val white = Color(0xffffffff)
        val gray = Color(0xff272727)
        var box1Color by remember { mutableStateOf(blue) }
        var box2Color by remember { mutableStateOf(white) }
        var box3Color by remember { mutableStateOf(white) }
        var box1TextColor by remember { mutableStateOf(white) }
        var box2TextColor by remember { mutableStateOf(gray) }
        var box3TextColor by remember { mutableStateOf(gray) }
        Box(
            modifier = Modifier
                .size(height = 40.dp, width = rowMaxWidth / 10)
                .clip(shape = RoundedCornerShape(topStart = 56.dp, bottomStart = 56.dp))
                .background(box1Color)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(topStart = 56.dp, bottomStart = 56.dp)
                )
                .clickable {
                    box1Color = blue
                    box2Color = white
                    box3Color = white
                    box1TextColor = white
                    box2TextColor = gray
                    box3TextColor = gray

                    //filter алу
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Дата",
                style = TextStyle(
                    color = box1TextColor,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 18.sp
                )
            )
        }
        Box(
            modifier = Modifier
                .size(height = 40.dp, width = rowMaxWidth / 6)
                .background(box2Color)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val halfStroke = strokeWidth / 2
                    drawLine(
                        color = Color.Black,
                        start = androidx.compose.ui.geometry.Offset(0f, halfStroke),
                        end = androidx.compose.ui.geometry.Offset(size.width, halfStroke),
                        strokeWidth = strokeWidth
                    )
                    drawLine(
                        color = Color.Black,
                        start = androidx.compose.ui.geometry.Offset(0f, size.height - halfStroke),
                        end = androidx.compose.ui.geometry.Offset(
                            size.width,
                            size.height - halfStroke
                        ),
                        strokeWidth = strokeWidth
                    )
                }
                .clickable {
                    box1Color = white
                    box2Color = blue
                    box3Color = white
                    box1TextColor = gray
                    box2TextColor = white
                    box3TextColor = gray

                    //filter алу
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Популярность",
                style = TextStyle(
                    color = box2TextColor,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 18.sp
                )
            )
        }
        Box(
            modifier = Modifier
                .size(height = 40.dp, width = rowMaxWidth / 8)
                .clip(shape = RoundedCornerShape(topEnd = 56.dp, bottomEnd = 56.dp))
                .background(box3Color)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(topEnd = 56.dp, bottomEnd = 56.dp)
                )
                .clickable {
                    box1Color = white
                    box2Color = white
                    box3Color = blue
                    box1TextColor = gray
                    box2TextColor = gray
                    box3TextColor = white

                    //filter алу
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Рейтинг",
                style = TextStyle(
                    color = box3TextColor,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 18.sp
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .size(1.dp)
            .background(Color(0x70b5b5c9))
    )
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun NotViewed(){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(36.dp)){
        Box(modifier = Modifier.size(46.dp)) {
            Icon(modifier = Modifier.size(30.dp).align(Alignment.Center),
                painter = painterResource(R.drawable.ic_eye), contentDescription = "Ic_Eye", tint = Color(0xff272727))
        }
        Text(
            modifier = Modifier.clickable {

            },
            text = "Не просмотрен",
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 20.sp
            )
        )
    }
}