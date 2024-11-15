package com.assignmentsmobile.assignment_2.ui.pages.GalleryPage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.assignmentsmobile.assignment_2.data.FilmImagesList
import com.assignmentsmobile.assignment_2.ui.components.CoilImage
import com.assignmentsmobile.assignment_2.ui.components.DetailPageHeader

@Composable
fun GalleryPage(
    mainPadding: PaddingValues,
    filmImagesList: FilmImagesList?,
    onBackClicked: () -> Unit = {}
){
    Scaffold(
        topBar = {
            DetailPageHeader("Gallery", onBackClicked = onBackClicked)
        }
    ) {innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                bottom = mainPadding.calculateBottomPadding())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(filmImagesList?.items ?: emptyList()){image ->
                if(image.imageUrl != null && image.imageUrl != ""){
                    Row(
                        Modifier
                            .size(width = 300.dp, height = 200.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(Color(0x40b5b5c9))
                    ) {
                        CoilImage(
                            url = image.imageUrl,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}