package com.assignmentsmobile.assignment_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun Section(){
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
    LazyRow {

    }
}

//data class Homepage (
//    val id: Int,
//    val imagePath: Int
//)
