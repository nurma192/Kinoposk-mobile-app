package com.assignmentsmobile.assignment_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.assignmentsmobile.assignment_2.pages.OnBoardingScreen
import com.assignmentsmobile.assignment_2.ui.theme.Assignment_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkillCinemaApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SkillCinemaApp(){
    var isOnboardingCompleted by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    Assignment_2Theme {
        if (!isOnboardingCompleted) {
            OnBoardingScreen(
                onSkipClicked = {
                    isOnboardingCompleted = true
                    navController.navigate("")
                }
            )
        } else {
            Scaffold(
                bottomBar = {
                }
            ) { innerPadding ->
                SkillCinemaNavHost(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

