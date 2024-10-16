package com.assignmentsmobile.assignment_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun OnboardingScreen(
    onSkipClicked: () ->  Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 26.dp, end = 26.dp, top = 38.dp, bottom = 63.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_skillcinema),
                contentDescription = ""
            )
            Text(
                text = "Пропустить",
                modifier = Modifier.clickable {
                    onSkipClicked()
                },
                style = TextStyle(
                    color = Color(0xffb5b5c9),
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontSize = 14.sp
                ),
            )
        }
        val pagerState = rememberPagerState(pageCount = { onboardingItems.size })

        HorizontalPager(state = pagerState) { page ->
            val item = onboardingItems[page]
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = item.imagePath),
                    contentDescription = item.imageText,
                    modifier = Modifier.height(height = 270.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(80.dp))
                Text(
                    text = item.text,
                    style = TextStyle(
                        color = Color(0xff272727),
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontSize = 32.sp
                    )
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            repeat(onboardingItems.size) { index ->
                val color =
                    if (pagerState.currentPage == index) Color(0xff121616) else Color(0xffd9d9d9)
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(color = color, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

data class Onboarding(
    val imagePath: Int,
    val imageText: String,
    val text: String
)

val onboardingItems = listOf(
    Onboarding(R.drawable.image_1, "Image_1", "Узнавай \nо премьерах"),
    Onboarding(R.drawable.image_2, "Image_2", "Создавай \nколлекции"),
    Onboarding(R.drawable.image_3, "Image_3", "Делись \nс друзьями")
)