package com.assignmentsmobile.assignment_2

interface Destination {
    val image: Int
    val selectedImage: Int
    val route: String
}


object HomePage: Destination{
    override val image = R.drawable.ic_home
    override val selectedImage = R.drawable.ic_home_selected
    override val route = "homepage"
}

object SearchPage: Destination{
    override val image = R.drawable.ic_search
    override val selectedImage = R.drawable.ic_home_selected
    override val route = "searchpage"
}

object AccountPage: Destination{
    override val image = R.drawable.ic_profile
    override val selectedImage = R.drawable.ic_home_selected
    override val route = "accountpage"
}

val bottomBarPages = listOf(HomePage, SearchPage, AccountPage)