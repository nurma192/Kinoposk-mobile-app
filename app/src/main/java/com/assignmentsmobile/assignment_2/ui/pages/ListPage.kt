package com.assignmentsmobile.assignment_2.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R

@Preview(showBackground = true)
@Composable
fun ListPage() {
    var rowMaxWidth by remember { mutableStateOf(0.dp) }

    Column(modifier = Modifier.padding(top = 30.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    rowMaxWidth = layoutCoordinates.size.width.dp
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "Icon_Arrow_Left"
            )
            Text(
                text = "Сериалы",
                style = TextStyle(
                    color = Color(0xff272727),
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.width(rowMaxWidth/34))
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {

        }
    }
}