package com.assignmentsmobile.assignment_2.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignmentsmobile.assignment_2.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun AppHeader(){
    TopAppBar(
        modifier = Modifier
            .padding(start = 10.dp)
            .background(Color.Black),
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_skillcinema),
                contentDescription = "Icon_SkillCinema",
                )
        }

    )



}