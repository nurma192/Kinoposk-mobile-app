package com.assignmentsmobile.assignment_2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }
    TopAppBar(
        modifier = Modifier.padding(start = 10.dp, end = 26.dp),
        title = {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                placeholder = {
                    Text(
                        text = "Фильмы, актёры, режиссёры",
                        style = TextStyle(
                            color = Color(0xff838390),
                            fontFamily = FontFamily(Font(R.font.graphik_regular)),
                            fontSize = 16.sp
                        )
                    )
                },
                leadingIcon = {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.ic_search_2),
                        contentDescription = "Ic_Search"
                    )
                },
                trailingIcon = {
                    Row(
                        modifier = Modifier.padding(end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(24.dp)
                                .background(Color.Gray)
                        )
                        Image(
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    //FilterPage
                                },
                            painter = painterResource(R.drawable.ic_filter),
                            contentDescription = "Ic_Filter"
                        )
                    }
                },
                textStyle = TextStyle(
                    color = Color(0xff838390),
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 16.sp
                ),
                shape = RoundedCornerShape(56.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0x60b5b5c9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )
        }
    )
}
