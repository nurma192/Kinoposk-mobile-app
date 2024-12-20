package com.assignmentsmobile.assignment_2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.assignmentsmobile.assignment_2.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPageHeader(filmType: String?, onBackClicked: () -> Unit) {
    var rowMaxWidth by remember { mutableStateOf(0.dp) }
    TopAppBar(
        modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxWidth()
            .background(Color.White),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .onGloballyPositioned { layoutCoordinates ->
                        rowMaxWidth = layoutCoordinates.size.width.dp
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(modifier = Modifier.size(24.dp), onClick = {
                    onBackClicked()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "Icon_Arrow_Left"
                    )
                }
                Text(
                    text = filmType.toString(),
                    style = TextStyle(
                        color = Color(0xff272727),
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontSize = 16.sp,
                    )
                )
                Spacer(modifier = Modifier.width(rowMaxWidth / 20))
            }
        }
    )
}