package com.assignmentsmobile.assignment_2.ui.pages.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
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
import com.assignmentsmobile.assignment_2.data.sectionItems
import com.assignmentsmobile.assignment_2.ui.components.FilmView

@Preview(showBackground = true)
@Composable
fun HomepageScreen(
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding.calculateTopPadding(),
                start = 26.dp
            ),
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        // Nurma's code
        items(sectionItems) { section: Section ->
            SectionView(section)
        }


    }
}

@Composable
fun SectionView(section: Section) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
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
                modifier = Modifier.padding(end = 10.dp),
                style = TextStyle(
                    color = Color(0xff3d3bff),
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 16.sp
                )
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(section.list) { film: Film ->
                FilmView(film)
            }
            item(){
                SeeAllButton()
            }
        }
    }
}



@Composable
fun SeeAllButton() {
    Column(
        modifier = Modifier
            .size(width = 111.dp, height = 156.dp)
            .padding(top = 31.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            modifier = Modifier
                .size(32.dp),
            onClick = {}
        ) {
            Image(
                modifier = Modifier
                    .background(Color(0xffffffff), shape = CircleShape)
                    .padding(6.dp)
                    .size(20.dp),
                painter = painterResource(id = R.drawable.ic_eyes),
                contentDescription = "Ic_Arrow"
            )
        }
        Text(
            text = "Показать все",
            modifier = Modifier.clickable {  },
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontSize = 12.sp
            )
        )
    }
}

