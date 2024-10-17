package com.assignmentsmobile.assignment_2.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignmentsmobile.assignment_2.R

@Composable
@Preview(showBackground = true)
fun BottomNavigationBar() {
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
     Row (
           modifier = Modifier.fillMaxWidth(),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.Center
     ) {
         Image(
             modifier = Modifier.clickable {  },
             painter = painterResource(id = R.drawable.ic_home),
             contentDescription = "Icon Home"
         )
         Spacer(modifier = Modifier.width(56.dp))

         Image(
             modifier = Modifier.clickable {  },
             painter = painterResource(id = R.drawable.ic_search),
             contentDescription = "Icon Search"
         )
         Spacer(modifier = Modifier.width(56.dp))

         Image(
             modifier = Modifier.clickable {  },
             painter = painterResource(id = R.drawable.ic_profile),
             contentDescription = "Icon Profile"
         )

     }



    }


}


