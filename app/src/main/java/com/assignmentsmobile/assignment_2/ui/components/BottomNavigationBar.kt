package com.assignmentsmobile.assignment_2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.assignmentsmobile.assignment_2.Destination
import com.assignmentsmobile.assignment_2.bottomBarPages

@Composable
fun BottomNavigationBar(
    allPages: List<Destination>,
    onTabSelected: (Destination) -> Unit,
    currentPage: Destination
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            ),
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary,

        )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterHorizontally),

            ) {
            allPages.forEach { page ->
                if(page == currentPage){
                    Image(
                        modifier = Modifier.clickable {
                            onTabSelected(page)
                        },
                        painter = painterResource(id = page.selectedImage),
                        contentDescription = "Icon Home"
                    )
                }
                else {
                    Image(
                        modifier = Modifier.clickable {
                            onTabSelected(page)
                        },
                        painter = painterResource(id = page.image),
                        contentDescription = "Icon Home"
                    )
                }
            }

        }
    }
}



