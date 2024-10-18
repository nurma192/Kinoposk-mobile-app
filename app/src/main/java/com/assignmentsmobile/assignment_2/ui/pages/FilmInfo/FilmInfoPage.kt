package com.assignmentsmobile.assignment_2.ui.pages.FilmInfo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assignmentsmobile.assignment_2.data.filmItems

@Composable
fun FilmInfoPage(
    filmName: String?,
    innerPadding: PaddingValues
){
    val film = filmItems.find{it.filmName == filmName}
    Column(
        modifier = Modifier.padding(
            top = innerPadding.calculateTopPadding()
        )
    ){
        Text(
            text = film?.filmName ?: "Film not found"
        )
        Text(
            text = film?.genre ?: "Film not found"
        )
    }
}