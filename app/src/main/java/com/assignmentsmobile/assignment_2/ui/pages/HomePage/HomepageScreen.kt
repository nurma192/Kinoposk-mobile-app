package com.assignmentsmobile.assignment_2.ui.pages.HomePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignmentsmobile.assignment_2.ui.components.AppHeader
import com.assignmentsmobile.assignment_2.ui.components.BottomNavigationBar


@Composable
fun HomepageScreen(
    innerPadding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding.calculateTopPadding()
            ).background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Nurma's code


    }
}
