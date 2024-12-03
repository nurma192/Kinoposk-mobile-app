package com.assignmentsmobile.assignment_2.ui.pages.SearchPage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.assignmentsmobile.assignment_2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelectorScreen(
    onBackClicked: () -> Unit
) {
    val countries = listOf("Россия", "Великобритания", "Германия", "США", "Франция")
    val searchQuery = remember { mutableStateOf(TextFieldValue()) }

    val filteredCountries = countries.filter { country ->
        country.contains(searchQuery.value.text, ignoreCase = true)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderSection(onBackClicked)
            Spacer(modifier = Modifier.height(16.dp))
            SearchField(searchQuery)
            Spacer(modifier = Modifier.height(16.dp))
            CountryList(filteredCountries)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderSection(
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Страна",
                    style = TextStyle(fontWeight = FontWeight.Normal),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(painter = painterResource(id = R.drawable.ic_small_left_arrow), contentDescription = "Back")
            }
        },
        modifier = Modifier.border(1.dp, Color.Gray)
    )
}

@Composable
fun SearchField(searchQuery: MutableState<TextFieldValue>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            SearchIcon(modifier = Modifier.align(Alignment.CenterStart))
            BasicTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 48.dp, top = 8.dp)
                    .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.medium)
                    .padding(12.dp),
                textStyle = TextStyle(color = Color.Gray)
            )
        }
    }
}

@Composable
fun SearchIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "Search",
        modifier = modifier
            .size(32.dp)
            .padding(start = 16.dp)
    )
}

@Composable
fun CountryList(countries: List<String>) {
    Column(modifier = Modifier.padding(16.dp)) {
        countries.forEachIndexed { index, country ->
            CountryItem(country = country, isLastItem = index == countries.size - 1)
        }
    }
}

@Composable
fun CountryItem(country: String, isLastItem: Boolean) {
    Button(
        onClick = { /* Handle country selection */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = if (!isLastItem) 8.dp else 0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(16.dp)
    ) {
        Text(
            text = country,
            style = TextStyle(color = Color.Black, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }

    if (!isLastItem) {
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}
