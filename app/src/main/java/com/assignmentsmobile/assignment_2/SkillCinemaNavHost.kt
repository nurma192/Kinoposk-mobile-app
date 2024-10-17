package com.assignmentsmobile.assignment_2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SkillCinemaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "valid",
        modifier = modifier
    ) {
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