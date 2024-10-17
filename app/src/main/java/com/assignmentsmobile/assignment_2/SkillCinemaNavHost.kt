package com.assignmentsmobile.assignment_2

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignmentsmobile.assignment_2.ui.pages.HomePage.HomepageScreen

@Composable
fun SkillCinemaNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = HomePage.route,
    ) {
        composable(route = HomePage.route){
            HomepageScreen(innerPadding)
        }
        composable(route = SearchPage.route){
            HomepageScreen(innerPadding)
        }
        composable(route = AccountPage.route){
            HomepageScreen(innerPadding)
        }
//        composable(
//            route = SingleAccount.routeWithArgs,
//            arguments = SingleAccount.arguments,
//            deepLinks = SingleAccount.deepLinks
//        ) { navBackStackEntry ->
//            val accountType =
//                navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
//            SingleAccountScreen(accountType)
//        }
    }
}

//fun NavHostController.navigateSingleTopTo(route: String) =
//    this.navigate(route) { launchSingleTop = true }
//
//private fun NavHostController.navigateToSingleAccount(accountType: String) {
//    this.navigateSingleTopTo("${SingleAccount.route}/$accountType")
//}