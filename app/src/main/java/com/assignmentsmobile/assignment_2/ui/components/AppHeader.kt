package com.assignmentsmobile.assignment_2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
            .fillMaxWidth()
            .background(Color.White),
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_skillcinema),
                contentDescription = "Icon_SkillCinema",
                modifier = Modifier.width(120.dp).height(18.dp)
                )
        }

    )



}