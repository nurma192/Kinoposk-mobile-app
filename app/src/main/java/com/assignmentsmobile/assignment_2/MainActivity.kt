package com.assignmentsmobile.assignment_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.assignmentsmobile.assignment_2.ui.theme.Assignment_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment_2Theme {
                HomepageScreen()
//                OnboardingScreen()
            }
        }
    }
}
