package com.assignmentsmobile.assignment_2.ui.pages.SearchPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R

@Composable
fun DateRangeSelector(
    onBackClicked: () -> Unit
) {
    var startRange by remember { mutableStateOf("1998 - 2009") }
    var endRange by remember { mutableStateOf("1998 - 2009") }

    val years = listOf(
        1998, 1999, 2000, 2001, 2002, 2003,
        2004, 2005, 2006, 2007, 2008, 2009
    )

    Column(modifier = Modifier.padding(16.dp)) {
        DateRangeHeader(onBackClicked)
        StartRangeSelector(startRange, years) { startRange = it }
        Spacer(modifier = Modifier.height(16.dp))
        EndRangeSelector(endRange, years) { endRange = it }
        Spacer(modifier = Modifier.height(36.dp))
        SelectButton()
    }
}

@Composable
fun DateRangeHeader(
    onBackClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onBackClicked()
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_small_left_arrow),
                contentDescription = "Previous",
                modifier = Modifier.size(12.dp),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 35.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Период",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun StartRangeSelector(startRange: String, years: List<Int>, onYearSelected: (String) -> Unit) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            text = "Искать в период с",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = startRange,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue,
                        fontSize = 19.sp
                    )
                    Row {
                        IconButton(onClick = { /* Handle left chevron */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_big_left_arrow),
                                contentDescription = "Previous Year",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        IconButton(onClick = { /* Handle right chevron */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_big_right_arrow),
                                contentDescription = "Next Year",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(years.size) { index ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .height(30.dp)
                                .fillMaxWidth()
                                .clickable { onYearSelected(years[index].toString()) }
                        ) {
                            Text(
                                text = years[index].toString(),
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(horizontal = 8.dp),
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EndRangeSelector(endRange: String, years: List<Int>, onYearSelected: (String) -> Unit) {
    Column(modifier = Modifier.padding(top = 10.dp)) {
        Text(
            text = "Искать в период до",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = endRange,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue,
                        fontSize = 19.sp
                    )
                    Row {
                        IconButton(onClick = { /* Handle left chevron */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_big_left_arrow),
                                contentDescription = "Previous Year",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        IconButton(onClick = { /* Handle right chevron */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_big_right_arrow),
                                contentDescription = "Next Year",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(years.size) { index ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .height(30.dp)
                                .fillMaxWidth()
                                .clickable { onYearSelected(years[index].toString()) }
                        ) {
                            Text(
                                text = years[index].toString(),
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(horizontal = 8.dp),
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SelectButton() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .clickable { /* Handle selection */ }
                .background(Color.Blue, RoundedCornerShape(8.dp))
                .padding(vertical = 12.dp, horizontal = 16.dp),
        ) {
            Text(
                text = "Выбрать",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
