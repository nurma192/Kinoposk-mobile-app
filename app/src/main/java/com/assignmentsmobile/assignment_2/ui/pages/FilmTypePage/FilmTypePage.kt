package com.assignmentsmobile.assignment_2.ui.pages.FilmTypePage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.assignmentsmobile.assignment_2.data.filmItems
import com.assignmentsmobile.assignment_2.data.sectionItems

@Composable
fun FilmTypePage(
    filmType: String?,
    innerPadding: PaddingValues
){
    val section = sectionItems.find{it.sectionName == filmType}
    Box(
        modifier = Modifier.padding(
            top = innerPadding.calculateTopPadding()
        )
    ){
        Text(
            text = section?.sectionName ?: "Section not found"
        )
    }

}