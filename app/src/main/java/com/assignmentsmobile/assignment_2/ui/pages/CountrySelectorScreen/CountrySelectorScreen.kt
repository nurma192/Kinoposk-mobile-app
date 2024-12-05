package com.assignmentsmobile.assignment_2.ui.pages.CountrySelectorScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.data.Filter

@Composable
fun CountrySelectorScreen(
    onBackClicked: () -> Unit,
    filters: Filter
) {
    val countries = listOf("Россия", "Великобритания", "Германия", "США", "Франция")
    val searchQuery = remember { mutableStateOf(TextFieldValue()) }

    val filteredCountries = countries.filter { country ->
        country.contains(searchQuery.value.text, ignoreCase = true)
    }

    Column {
        HeaderSection(onBackClicked)
        SearchField(searchQuery)
        CountryList(filteredCountries, filters, onBackClicked)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderSection(
    onBackClicked: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.padding(top = 16.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                    text = "Страна",
                    style = TextStyle(
                        color = Color(0xff272727),
                        fontFamily = FontFamily(Font(R.font.graphik_bold)),
                        fontSize = 16.sp
                    )
                )
                Box(modifier = Modifier.size(30.dp)) {}
            }
        }
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(searchQuery: MutableState<TextFieldValue>) {
    Box(modifier = Modifier.padding(horizontal = 26.dp)) {
        TextField(
            value = searchQuery.value,
            onValueChange = {
                searchQuery.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            placeholder = {
                Text(
                    text = "Введите страну",
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
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun CountryList(countries: List<String>, filters: Filter, onBackClicked: () -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(countries){ country ->
            CountryItem(
                country = country,
                filters,
                onBackClicked
            )
        }
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun CountryItem(
    country: String,
    filters: Filter,
    onBackClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                filters.country = country
                onBackClicked()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 26.dp),
            text = country,
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                fontSize = 18.sp
            )
        )
    }
    Divider(color = Color(0x70b5b5c9), thickness = 1.5.dp)
}
