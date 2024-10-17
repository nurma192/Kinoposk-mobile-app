package com.assignmentsmobile.assignment_2

import android.media.Image
import androidx.compose.ui.graphics.vector.ImageVector

interface Destination {
    val image: Int
    val route: String
}


object HomePage: Destination{
    override val image = R.drawable.ic_home
    override val route = "homepage"
}

object SearchPage: Destination{
    override val image = R.drawable.ic_search
    override val route = "homepage"
}

object AccountPage: Destination{
    override val image = R.drawable.ic_profile
    override val route = "homepage"
}

val bottomBarPages = listOf(HomePage, SearchPage, AccountPage)