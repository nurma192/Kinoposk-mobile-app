package com.assignmentsmobile.assignment_2.ui.components


import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import coil.request.CachePolicy

@Composable
fun CoilImage(url: String, modifier: Modifier = Modifier, contentScale: ContentScale) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
            diskCachePolicy(CachePolicy.ENABLED)
            memoryCachePolicy(CachePolicy.ENABLED)
        }
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = contentScale
    )
}